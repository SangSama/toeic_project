<template>
  <div>
    <h2 id="page-heading" data-cy="ExtendQuestionHeading">
      <span v-text="$t('finalProjectApp.extendQuestion.home.title')" id="extend-question-heading">Extend Questions</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.extendQuestion.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'ExtendQuestionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-extend-question"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.extendQuestion.home.createLabel')"> Create a new Extend Question </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && extendQuestions && extendQuestions.length === 0">
      <span v-text="$t('finalProjectApp.extendQuestion.home.notFound')">No extendQuestions found</span>
    </div>
    <div class="table-responsive" v-if="extendQuestions && extendQuestions.length > 0">
      <table class="table table-striped" aria-describedby="extendQuestions">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('paragraph')">
              <span v-text="$t('finalProjectApp.extendQuestion.paragraph')">Paragraph</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'paragraph'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('image')">
              <span v-text="$t('finalProjectApp.extendQuestion.image')">Image</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'image'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.extendQuestion.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.extendQuestion.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="extendQuestion in extendQuestions" :key="extendQuestion.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'ExtendQuestionView', params: { extendQuestionId: extendQuestion.id } }">{{
                extendQuestion.id
              }}</router-link>
            </td>
            <td>{{ extendQuestion.paragraph }}</td>
            <td>
              <a v-if="extendQuestion.image" v-on:click="openFile(extendQuestion.imageContentType, extendQuestion.image)">
                <img
                  v-bind:src="'data:' + extendQuestion.imageContentType + ';base64,' + extendQuestion.image"
                  style="max-height: 30px"
                  alt="extendQuestion image"
                />
              </a>
              <span v-if="extendQuestion.image">{{ extendQuestion.imageContentType }}, {{ byteSize(extendQuestion.image) }}</span>
            </td>
            <td>{{ extendQuestion.createdAt }}</td>
            <td>{{ extendQuestion.updatedAt }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'ExtendQuestionView', params: { extendQuestionId: extendQuestion.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'ExtendQuestionEdit', params: { extendQuestionId: extendQuestion.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(extendQuestion)"
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
          id="finalProjectApp.extendQuestion.delete.question"
          data-cy="extendQuestionDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-extendQuestion-heading" v-text="$t('finalProjectApp.extendQuestion.delete.question', { id: removeId })">
          Are you sure you want to delete this Extend Question?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-extendQuestion"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeExtendQuestion()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="extendQuestions && extendQuestions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./extend-question.component.ts"></script>
