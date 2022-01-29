<template>
  <div>
    <h2 id="page-heading" data-cy="VocabularyTopicHeading">
      <span v-text="$t('finalProjectApp.vocabularyTopic.home.title')" id="vocabulary-topic-heading">Vocabulary Topics</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.vocabularyTopic.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'VocabularyTopicCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-vocabulary-topic"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.vocabularyTopic.home.createLabel')"> Create a new Vocabulary Topic </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && vocabularyTopics && vocabularyTopics.length === 0">
      <span v-text="$t('finalProjectApp.vocabularyTopic.home.notFound')">No vocabularyTopics found</span>
    </div>
    <div class="table-responsive" v-if="vocabularyTopics && vocabularyTopics.length > 0">
      <table class="table table-striped" aria-describedby="vocabularyTopics">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameTopic')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.nameTopic')">Name Topic</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameTopic'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('view')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.view')">View</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'view'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('test')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.test')">Test</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'test'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('level')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.level')">Level</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'level'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.vocabularyTopic.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="vocabularyTopic in vocabularyTopics" :key="vocabularyTopic.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VocabularyTopicView', params: { vocabularyTopicId: vocabularyTopic.id } }">{{
                vocabularyTopic.id
              }}</router-link>
            </td>
            <td>{{ vocabularyTopic.nameTopic }}</td>
            <td>{{ vocabularyTopic.description }}</td>
            <td>{{ vocabularyTopic.view }}</td>
            <td>{{ vocabularyTopic.test }}</td>
            <td>{{ vocabularyTopic.level }}</td>
            <td>{{ vocabularyTopic.createdAt }}</td>
            <td>{{ vocabularyTopic.updatedAt }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'VocabularyTopicView', params: { vocabularyTopicId: vocabularyTopic.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'VocabularyTopicEdit', params: { vocabularyTopicId: vocabularyTopic.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(vocabularyTopic)"
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
        ><span
          id="finalProjectApp.vocabularyTopic.delete.question"
          data-cy="vocabularyTopicDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-vocabularyTopic-heading" v-text="$t('finalProjectApp.vocabularyTopic.delete.question', { id: removeId })">
          Are you sure you want to delete this Vocabulary Topic?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-vocabularyTopic"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeVocabularyTopic()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="vocabularyTopics && vocabularyTopics.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./vocabulary-topic.component.ts"></script>
