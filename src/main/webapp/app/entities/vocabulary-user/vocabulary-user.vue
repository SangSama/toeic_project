<template>
  <div>
    <h2 id="page-heading" data-cy="VocabularyUserHeading">
      <span v-text="$t('finalProjectApp.vocabularyUser.home.title')" id="vocabulary-user-heading">Vocabulary Users</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.vocabularyUser.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'VocabularyUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-vocabulary-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.vocabularyUser.home.createLabel')"> Create a new Vocabulary User </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && vocabularyUsers && vocabularyUsers.length === 0">
      <span v-text="$t('finalProjectApp.vocabularyUser.home.notFound')">No vocabularyUsers found</span>
    </div>
    <div class="table-responsive" v-if="vocabularyUsers && vocabularyUsers.length > 0">
      <table class="table table-striped" aria-describedby="vocabularyUsers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userId')">
              <span v-text="$t('finalProjectApp.vocabularyUser.userId')">User Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('score')">
              <span v-text="$t('finalProjectApp.vocabularyUser.score')">Score</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'score'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.vocabularyUser.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.vocabularyUser.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vocabularyTopic.id')">
              <span v-text="$t('finalProjectApp.vocabularyUser.vocabularyTopic')">Vocabulary Topic</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vocabularyTopic.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="vocabularyUser in vocabularyUsers" :key="vocabularyUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'VocabularyUserView', params: { vocabularyUserId: vocabularyUser.id } }">{{
                vocabularyUser.id
              }}</router-link>
            </td>
            <td>{{ vocabularyUser.userId }}</td>
            <td>{{ vocabularyUser.score }}</td>
            <td>{{ vocabularyUser.createdAt }}</td>
            <td>{{ vocabularyUser.updatedAt }}</td>
            <td>
              <div v-if="vocabularyUser.vocabularyTopic">
                <router-link :to="{ name: 'VocabularyTopicView', params: { vocabularyTopicId: vocabularyUser.vocabularyTopic.id } }">{{
                  vocabularyUser.vocabularyTopic.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'VocabularyUserView', params: { vocabularyUserId: vocabularyUser.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'VocabularyUserEdit', params: { vocabularyUserId: vocabularyUser.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(vocabularyUser)"
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
          id="finalProjectApp.vocabularyUser.delete.question"
          data-cy="vocabularyUserDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-vocabularyUser-heading" v-text="$t('finalProjectApp.vocabularyUser.delete.question', { id: removeId })">
          Are you sure you want to delete this Vocabulary User?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-vocabularyUser"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeVocabularyUser()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="vocabularyUsers && vocabularyUsers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./vocabulary-user.component.ts"></script>
