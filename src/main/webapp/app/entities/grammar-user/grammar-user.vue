<template>
  <div>
    <h2 id="page-heading" data-cy="GrammarUserHeading">
      <span v-text="$t('finalProjectApp.grammarUser.home.title')" id="grammar-user-heading">Grammar Users</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.grammarUser.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'GrammarUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-grammar-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.grammarUser.home.createLabel')"> Create a new Grammar User </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && grammarUsers && grammarUsers.length === 0">
      <span v-text="$t('finalProjectApp.grammarUser.home.notFound')">No grammarUsers found</span>
    </div>
    <div class="table-responsive" v-if="grammarUsers && grammarUsers.length > 0">
      <table class="table table-striped" aria-describedby="grammarUsers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('userId')">
              <span v-text="$t('finalProjectApp.grammarUser.userId')">User Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'userId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('score')">
              <span v-text="$t('finalProjectApp.grammarUser.score')">Score</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'score'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.grammarUser.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.grammarUser.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('grammarTopic.id')">
              <span v-text="$t('finalProjectApp.grammarUser.grammarTopic')">Grammar Topic</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grammarTopic.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="grammarUser in grammarUsers" :key="grammarUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'GrammarUserView', params: { grammarUserId: grammarUser.id } }">{{ grammarUser.id }}</router-link>
            </td>
            <td>{{ grammarUser.userId }}</td>
            <td>{{ grammarUser.score }}</td>
            <td>{{ grammarUser.createdAt }}</td>
            <td>{{ grammarUser.updatedAt }}</td>
            <td>
              <div v-if="grammarUser.grammarTopic">
                <router-link :to="{ name: 'GrammarTopicView', params: { grammarTopicId: grammarUser.grammarTopic.id } }">{{
                  grammarUser.grammarTopic.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'GrammarUserView', params: { grammarUserId: grammarUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'GrammarUserEdit', params: { grammarUserId: grammarUser.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(grammarUser)"
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
        ><span id="finalProjectApp.grammarUser.delete.question" data-cy="grammarUserDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-grammarUser-heading" v-text="$t('finalProjectApp.grammarUser.delete.question', { id: removeId })">
          Are you sure you want to delete this Grammar User?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-grammarUser"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeGrammarUser()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="grammarUsers && grammarUsers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./grammar-user.component.ts"></script>
