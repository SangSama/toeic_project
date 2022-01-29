<template>
  <div>
    <h2 id="page-heading" data-cy="AnswerQuestionHeading">
      <span v-text="$t('finalProjectApp.answerQuestion.home.title')" id="answer-question-heading">Answer Questions</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.answerQuestion.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'AnswerQuestionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-answer-question"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.answerQuestion.home.createLabel')"> Create a new Answer Question </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && answerQuestions && answerQuestions.length === 0">
      <span v-text="$t('finalProjectApp.answerQuestion.home.notFound')">No answerQuestions found</span>
    </div>
    <div class="table-responsive" v-if="answerQuestions && answerQuestions.length > 0">
      <table class="table table-striped" aria-describedby="answerQuestions">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('answerA')">
              <span v-text="$t('finalProjectApp.answerQuestion.answerA')">Answer A</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'answerA'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('answerB')">
              <span v-text="$t('finalProjectApp.answerQuestion.answerB')">Answer B</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'answerB'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('answerC')">
              <span v-text="$t('finalProjectApp.answerQuestion.answerC')">Answer C</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'answerC'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('answerD')">
              <span v-text="$t('finalProjectApp.answerQuestion.answerD')">Answer D</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'answerD'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.answerQuestion.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.answerQuestion.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="answerQuestion in answerQuestions" :key="answerQuestion.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'AnswerQuestionView', params: { answerQuestionId: answerQuestion.id } }">{{
                answerQuestion.id
              }}</router-link>
            </td>
            <td>{{ answerQuestion.answerA }}</td>
            <td>{{ answerQuestion.answerB }}</td>
            <td>{{ answerQuestion.answerC }}</td>
            <td>{{ answerQuestion.answerD }}</td>
            <td>{{ answerQuestion.createdAt }}</td>
            <td>{{ answerQuestion.updatedAt }}</td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'AnswerQuestionView', params: { answerQuestionId: answerQuestion.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'AnswerQuestionEdit', params: { answerQuestionId: answerQuestion.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(answerQuestion)"
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
          id="finalProjectApp.answerQuestion.delete.question"
          data-cy="answerQuestionDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-answerQuestion-heading" v-text="$t('finalProjectApp.answerQuestion.delete.question', { id: removeId })">
          Are you sure you want to delete this Answer Question?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-answerQuestion"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeAnswerQuestion()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="answerQuestions && answerQuestions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./answer-question.component.ts"></script>
