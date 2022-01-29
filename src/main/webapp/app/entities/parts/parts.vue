<template>
  <div>
    <h2 id="page-heading" data-cy="PartsHeading">
      <span v-text="$t('finalProjectApp.parts.home.title')" id="parts-heading">Parts</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.parts.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'PartsCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-parts"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.parts.home.createLabel')"> Create a new Parts </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && parts && parts.length === 0">
      <span v-text="$t('finalProjectApp.parts.home.notFound')">No parts found</span>
    </div>
    <div class="table-responsive" v-if="parts && parts.length > 0">
      <table class="table table-striped" aria-describedby="parts">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('part')">
              <span v-text="$t('finalProjectApp.parts.part')">Part</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'part'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('description')">
              <span v-text="$t('finalProjectApp.parts.description')">Description</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'description'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('example')">
              <span v-text="$t('finalProjectApp.parts.example')">Example</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'example'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('fileLC')">
              <span v-text="$t('finalProjectApp.parts.fileLC')">File LC</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'fileLC'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('view')">
              <span v-text="$t('finalProjectApp.parts.view')">View</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'view'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('test')">
              <span v-text="$t('finalProjectApp.parts.test')">Test</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'test'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.parts.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.parts.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('toeics.id')">
              <span v-text="$t('finalProjectApp.parts.toeics')">Toeics</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'toeics.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="parts in parts" :key="parts.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'PartsView', params: { partsId: parts.id } }">{{ parts.id }}</router-link>
            </td>
            <td>{{ parts.part }}</td>
            <td>{{ parts.description }}</td>
            <td>{{ parts.example }}</td>
            <td>{{ parts.fileLC }}</td>
            <td>{{ parts.view }}</td>
            <td>{{ parts.test }}</td>
            <td>{{ parts.createdAt }}</td>
            <td>{{ parts.updatedAt }}</td>
            <td>
              <div v-if="parts.toeics">
                <router-link :to="{ name: 'ToeicsView', params: { toeicsId: parts.toeics.id } }">{{ parts.toeics.id }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'PartsView', params: { partsId: parts.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'PartsEdit', params: { partsId: parts.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(parts)"
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
        ><span id="finalProjectApp.parts.delete.question" data-cy="partsDeleteDialogHeading" v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-parts-heading" v-text="$t('finalProjectApp.parts.delete.question', { id: removeId })">
          Are you sure you want to delete this Parts?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-parts"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeParts()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="parts && parts.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./parts.component.ts"></script>
