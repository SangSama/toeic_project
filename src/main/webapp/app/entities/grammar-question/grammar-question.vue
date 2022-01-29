<template>
  <div>
    <h2 id="page-heading" data-cy="GrammarQuestionHeading">
      <span v-text="$t('finalProjectApp.grammarQuestion.home.title')" id="grammar-question-heading">Grammar Questions</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.grammarQuestion.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'GrammarQuestionCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-grammar-question"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.grammarQuestion.home.createLabel')"> Create a new Grammar Question </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && grammarQuestions && grammarQuestions.length === 0">
      <span v-text="$t('finalProjectApp.grammarQuestion.home.notFound')">No grammarQuestions found</span>
    </div>
    <div class="table-responsive" v-if="grammarQuestions && grammarQuestions.length > 0">
      <table class="table table-striped" aria-describedby="grammarQuestions">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('question')">
              <span v-text="$t('finalProjectApp.grammarQuestion.question')">Question</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'question'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('correct')">
              <span v-text="$t('finalProjectApp.grammarQuestion.correct')">Correct</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'correct'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.grammarQuestion.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.grammarQuestion.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('grammarAnswer.id')">
              <span v-text="$t('finalProjectApp.grammarQuestion.grammarAnswer')">Grammar Answer</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grammarAnswer.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('grammarTopic.id')">
              <span v-text="$t('finalProjectApp.grammarQuestion.grammarTopic')">Grammar Topic</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'grammarTopic.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="grammarQuestion in grammarQuestions" :key="grammarQuestion.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'GrammarQuestionView', params: { grammarQuestionId: grammarQuestion.id } }">{{
                grammarQuestion.id
              }}</router-link>
            </td>
            <td>{{ grammarQuestion.question }}</td>
            <td>{{ grammarQuestion.correct }}</td>
            <td>{{ grammarQuestion.createdAt }}</td>
            <td>{{ grammarQuestion.updatedAt }}</td>
            <td>
              <div v-if="grammarQuestion.grammarAnswer">
                <router-link :to="{ name: 'GrammarAnswerView', params: { grammarAnswerId: grammarQuestion.grammarAnswer.id } }">{{
                  grammarQuestion.grammarAnswer.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="grammarQuestion.grammarTopic">
                <router-link :to="{ name: 'GrammarTopicView', params: { grammarTopicId: grammarQuestion.grammarTopic.id } }">{{
                  grammarQuestion.grammarTopic.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link
                  :to="{ name: 'GrammarQuestionView', params: { grammarQuestionId: grammarQuestion.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link
                  :to="{ name: 'GrammarQuestionEdit', params: { grammarQuestionId: grammarQuestion.id } }"
                  custom
                  v-slot="{ navigate }"
                >
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(grammarQuestion)"
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
          id="finalProjectApp.grammarQuestion.delete.question"
          data-cy="grammarQuestionDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-grammarQuestion-heading" v-text="$t('finalProjectApp.grammarQuestion.delete.question', { id: removeId })">
          Are you sure you want to delete this Grammar Question?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-grammarQuestion"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeGrammarQuestion()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="grammarQuestions && grammarQuestions.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./grammar-question.component.ts"></script>
