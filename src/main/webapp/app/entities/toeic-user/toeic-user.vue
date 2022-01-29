<template>
  <div>
    <h2 id="page-heading" data-cy="ToeicUserHeading">
      <span v-text="$t('finalProjectApp.toeicUser.home.title')" id="toeic-user-heading">Toeic Users</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.toeicUser.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ToeicUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-toeic-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.toeicUser.home.createLabel')"> Create a new Toeic User </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && toeicUsers && toeicUsers.length === 0">
      <span v-text="$t('finalProjectApp.toeicUser.home.notFound')">No toeicUsers found</span>
    </div>
    <div class="table-responsive" v-if="toeicUsers && toeicUsers.length > 0">
      <table class="table table-striped" aria-describedby="toeicUsers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userId')">
              <span v-text="$t('finalProjectApp.toeicUser.userId')">User Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('score')">
              <span v-text="$t('finalProjectApp.toeicUser.score')">Score</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'score'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('reading')">
              <span v-text="$t('finalProjectApp.toeicUser.reading')">Reading</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'reading'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('listening')">
              <span v-text="$t('finalProjectApp.toeicUser.listening')">Listening</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'listening'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.toeicUser.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.toeicUser.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('toeics.id')">
              <span v-text="$t('finalProjectApp.toeicUser.toeics')">Toeics</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'toeics.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="toeicUser in toeicUsers" :key="toeicUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ToeicUserView', params: { toeicUserId: toeicUser.id } }">{{ toeicUser.id }}</router-link>
            </td>
            <td>{{ toeicUser.userId }}</td>
            <td>{{ toeicUser.score }}</td>
            <td>{{ toeicUser.reading }}</td>
            <td>{{ toeicUser.listening }}</td>
            <td>{{ toeicUser.createdAt }}</td>
            <td>{{ toeicUser.updatedAt }}</td>
            <td>
              <div v-if="toeicUser.toeics">
                <router-link :to="{ name: 'ToeicsView', params: { toeicsId: toeicUser.toeics.id } }">{{ toeicUser.toeics.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ToeicUserView', params: { toeicUserId: toeicUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ToeicUserEdit', params: { toeicUserId: toeicUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(toeicUser)"
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
        ><span id="finalProjectApp.toeicUser.delete.question" data-cy="toeicUserDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-toeicUser-heading" v-text="$t('finalProjectApp.toeicUser.delete.question', { id: removeId })">
          Are you sure you want to delete this Toeic User?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-toeicUser"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeToeicUser()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="toeicUsers && toeicUsers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./toeic-user.component.ts"></script>
