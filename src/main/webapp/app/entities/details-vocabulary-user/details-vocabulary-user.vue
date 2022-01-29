<template>
  <div>
    <h2 id="page-heading" data-cy="DetailsVocabularyUserHeading">
      <span v-text="$t('finalProjectApp.detailsVocabularyUser.home.title')" id="details-vocabulary-user-heading"
        >Details Vocabulary Users</span
      >
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.detailsVocabularyUser.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'DetailsVocabularyUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-details-vocabulary-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.detailsVocabularyUser.home.createLabel')"> Create a new Details Vocabulary User </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && detailsVocabularyUsers && detailsVocabularyUsers.length === 0">
      <span v-text="$t('finalProjectApp.detailsVocabularyUser.home.notFound')">No detailsVocabularyUsers found</span>
    </div>
    <div class="table-responsive" v-if="detailsVocabularyUsers && detailsVocabularyUsers.length > 0">
      <table class="table table-striped" aria-describedby="detailsVocabularyUsers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vocabularyId')">
              <span v-text="$t('finalProjectApp.detailsVocabularyUser.vocabularyId')">Vocabulary Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vocabularyId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status')">
              <span v-text="$t('finalProjectApp.detailsVocabularyUser.status')">Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.detailsVocabularyUser.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.detailsVocabularyUser.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('vocabularyUser.id')">
              <span v-text="$t('finalProjectApp.detailsVocabularyUser.vocabularyUser')">Vocabulary User</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'vocabularyUser.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="detailsVocabularyUser in detailsVocabularyUsers" :key="detailsVocabularyUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DetailsVocabularyUserView', params: { detailsVocabularyUserId: detailsVocabularyUser.id } }">{{
                detailsVocabularyUser.id
              }}</router-link>
            </td>
            <td>{{ detailsVocabularyUser.vocabularyId }}</td>
            <td>{{ detailsVocabularyUser.status }}</td>
            <td>{{ detailsVocabularyUser.createdAt }}</td>
            <td>{{ detailsVocabularyUser.updatedAt }}</td>
            <td>
              <div v-if="detailsVocabularyUser.vocabularyUser">
                <router-link :to="{ name: 'VocabularyUserView', params: { vocabularyUserId: detailsVocabularyUser.vocabularyUser.id } }">{{
                  detailsVocabularyUser.vocabularyUser.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'DetailsVocabularyUserView', params: { detailsVocabularyUserId: detailsVocabularyUser.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DetailsVocabularyUserEdit', params: { detailsVocabularyUserId: detailsVocabularyUser.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(detailsVocabularyUser)"
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
          id="finalProjectApp.detailsVocabularyUser.delete.question"
          data-cy="detailsVocabularyUserDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p
          id="jhi-delete-detailsVocabularyUser-heading"
          v-text="$t('finalProjectApp.detailsVocabularyUser.delete.question', { id: removeId })"
        >
          Are you sure you want to delete this Details Vocabulary User?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-detailsVocabularyUser"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDetailsVocabularyUser()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="detailsVocabularyUsers && detailsVocabularyUsers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./details-vocabulary-user.component.ts"></script>
