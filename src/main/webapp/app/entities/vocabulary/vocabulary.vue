<template>
  <div>
    <h2 id="page-heading" data-cy="VocabularyHeading">
      <span v-text="$t('finalProjectApp.vocabulary.home.title')" id="vocabulary-heading">Vocabularies</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.vocabulary.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'VocabularyCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-vocabulary"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.vocabulary.home.createLabel')"> Create a new Vocabulary </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && vocabularies && vocabularies.length === 0">
      <span v-text="$t('finalProjectApp.vocabulary.home.notFound')">No vocabularies found</span>
    </div>
    <div class="table-responsive" v-if="vocabularies && vocabularies.length > 0">
      <table class="table table-striped" aria-describedby="vocabularies">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('word')">
              <span v-text="$t('finalProjectApp.vocabulary.word')">Word</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'word'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('mean')">
              <span v-text="$t('finalProjectApp.vocabulary.mean')">Mean</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'mean'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.vocabulary.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.vocabulary.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vocabularyTopic.id')">
              <span v-text="$t('finalProjectApp.vocabulary.vocabularyTopic')">Vocabulary Topic</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vocabularyTopic.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="vocabulary in vocabularies" :key="vocabulary.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VocabularyView', params: { vocabularyId: vocabulary.id } }">{{ vocabulary.id }}</router-link>
            </td>
            <td>{{ vocabulary.word }}</td>
            <td>{{ vocabulary.mean }}</td>
            <td>{{ vocabulary.createdAt }}</td>
            <td>{{ vocabulary.updatedAt }}</td>
            <td>
              <div v-if="vocabulary.vocabularyTopic">
                <router-link :to="{ name: 'VocabularyTopicView', params: { vocabularyTopicId: vocabulary.vocabularyTopic.id } }">{{
                  vocabulary.vocabularyTopic.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'VocabularyView', params: { vocabularyId: vocabulary.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'VocabularyEdit', params: { vocabularyId: vocabulary.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(vocabulary)"
                  variant="danger"
                  class="btn btn-sm"
                  data-cy="entityDeleteButton"
                  v-b-modal.removeEntity
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                  <span class="d-none d-md-inline" v-text="$t('entity.action.delete')">Delete</span>
                </b-button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <b-modal ref="removeEntity" id="removeEntity">
      <span slot="modal-title"
        ><span id="finalProjectApp.vocabulary.delete.question" data-cy="vocabularyDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-vocabulary-heading" v-text="$t('finalProjectApp.vocabulary.delete.question', { id: removeId })">
          Are you sure you want to delete this Vocabulary?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-vocabulary"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeVocabulary()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="vocabularies && vocabularies.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./vocabulary.component.ts"></script>
