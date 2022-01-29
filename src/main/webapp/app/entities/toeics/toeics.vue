<template>
  <div>
    <h2 id="page-heading" data-cy="ToeicsHeading">
      <span v-text="$t('finalProjectApp.toeics.home.title')" id="toeics-heading">Toeics</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.toeics.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ToeicsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-toeics"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.toeics.home.createLabel')"> Create a new Toeics </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && toeics && toeics.length === 0">
      <span v-text="$t('finalProjectApp.toeics.home.notFound')">No toeics found</span>
    </div>
    <div class="table-responsive" v-if="toeics && toeics.length > 0">
      <table class="table table-striped" aria-describedby="toeics">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('nameToeic')">
              <span v-text="$t('finalProjectApp.toeics.nameToeic')">Name Toeic</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'nameToeic'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('number')">
              <span v-text="$t('finalProjectApp.toeics.number')">Number</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'number'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('finalProjectApp.toeics.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('view')">
              <span v-text="$t('finalProjectApp.toeics.view')">View</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'view'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('test')">
              <span v-text="$t('finalProjectApp.toeics.test')">Test</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'test'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('linkDetail')">
              <span v-text="$t('finalProjectApp.toeics.linkDetail')">Link Detail</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'linkDetail'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.toeics.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.toeics.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="toeics in toeics" :key="toeics.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ToeicsView', params: { toeicsId: toeics.id } }">{{ toeics.id }}</router-link>
            </td>
            <td>{{ toeics.nameToeic }}</td>
            <td>{{ toeics.number }}</td>
            <td>{{ toeics.description }}</td>
            <td>{{ toeics.view }}</td>
            <td>{{ toeics.test }}</td>
            <td>{{ toeics.linkDetail }}</td>
            <td>{{ toeics.createdAt }}</td>
            <td>{{ toeics.updatedAt }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'ToeicsView', params: { toeicsId: toeics.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'ToeicsEdit', params: { toeicsId: toeics.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(toeics)"
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
        ><span id="finalProjectApp.toeics.delete.question" data-cy="toeicsDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-toeics-heading" v-text="$t('finalProjectApp.toeics.delete.question', { id: removeId })">
          Are you sure you want to delete this Toeics?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-toeics"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeToeics()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="toeics && toeics.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./toeics.component.ts"></script>
