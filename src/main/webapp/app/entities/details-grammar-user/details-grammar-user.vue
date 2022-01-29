<template>
  <div>
    <h2 id="page-heading" data-cy="DetailsGrammarUserHeading">
      <span v-text="$t('finalProjectApp.detailsGrammarUser.home.title')" id="details-grammar-user-heading">Details Grammar Users</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.detailsGrammarUser.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'DetailsGrammarUserCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-details-grammar-user"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.detailsGrammarUser.home.createLabel')"> Create a new Details Grammar User </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && detailsGrammarUsers && detailsGrammarUsers.length === 0">
      <span v-text="$t('finalProjectApp.detailsGrammarUser.home.notFound')">No detailsGrammarUsers found</span>
    </div>
    <div class="table-responsive" v-if="detailsGrammarUsers && detailsGrammarUsers.length > 0">
      <table class="table table-striped" aria-describedby="detailsGrammarUsers">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('grammarQuestionId')">
              <span v-text="$t('finalProjectApp.detailsGrammarUser.grammarQuestionId')">Grammar Question Id</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grammarQuestionId'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('status')">
              <span v-text="$t('finalProjectApp.detailsGrammarUser.status')">Status</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'status'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.detailsGrammarUser.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.detailsGrammarUser.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('grammarUser.id')">
              <span v-text="$t('finalProjectApp.detailsGrammarUser.grammarUser')">Grammar User</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grammarUser.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="detailsGrammarUser in detailsGrammarUsers" :key="detailsGrammarUser.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'DetailsGrammarUserView', params: { detailsGrammarUserId: detailsGrammarUser.id } }">{{
                detailsGrammarUser.id
              }}</router-link>
            </td>
            <td>{{ detailsGrammarUser.grammarQuestionId }}</td>
            <td>{{ detailsGrammarUser.status }}</td>
            <td>{{ detailsGrammarUser.createdAt }}</td>
            <td>{{ detailsGrammarUser.updatedAt }}</td>
            <td>
              <div v-if="detailsGrammarUser.grammarUser">
                <router-link :to="{ name: 'GrammarUserView', params: { grammarUserId: detailsGrammarUser.grammarUser.id } }">{{
                  detailsGrammarUser.grammarUser.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'DetailsGrammarUserView', params: { detailsGrammarUserId: detailsGrammarUser.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'DetailsGrammarUserEdit', params: { detailsGrammarUserId: detailsGrammarUser.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(detailsGrammarUser)"
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
          id="finalProjectApp.detailsGrammarUser.delete.question"
          data-cy="detailsGrammarUserDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-detailsGrammarUser-heading" v-text="$t('finalProjectApp.detailsGrammarUser.delete.question', { id: removeId })">
          Are you sure you want to delete this Details Grammar User?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-detailsGrammarUser"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeDetailsGrammarUser()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="detailsGrammarUsers && detailsGrammarUsers.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./details-grammar-user.component.ts"></script>
