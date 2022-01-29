<template>
  <div>
    <h2 id="page-heading" data-cy="QuestionPartHeading">
      <span v-text="$t('finalProjectApp.questionPart.home.title')" id="question-part-heading">Question Parts</span>
      <div class="d-flex justify-content-end">
        <button class="btn btn-info mr-2" v-on:click="handleSyncList" :disabled="isFetching">
          <font-awesome-icon icon="sync" :spin="isFetching"></font-awesome-icon>
          <span v-text="$t('finalProjectApp.questionPart.home.refreshListLabel')">Refresh List</span>
        </button>
        <router-link :to="{ name: 'QuestionPartCreate' }" custom v-slot="{ navigate }">
          <button
            @click="navigate"
            id="jh-create-entity"
            data-cy="entityCreateButton"
            class="btn btn-primary jh-create-entity create-question-part"
          >
            <font-awesome-icon icon="plus"></font-awesome-icon>
            <span v-text="$t('finalProjectApp.questionPart.home.createLabel')"> Create a new Question Part </span>
          </button>
        </router-link>
      </div>
    </h2>
    <br />
    <div class="alert alert-warning" v-if="!isFetching && questionParts && questionParts.length === 0">
      <span v-text="$t('finalProjectApp.questionPart.home.notFound')">No questionParts found</span>
    </div>
    <div class="table-responsive" v-if="questionParts && questionParts.length > 0">
      <table class="table table-striped" aria-describedby="questionParts">
        <thead>
          <tr>
            <th scope="row" v-on:click="changeOrder('id')">
              <span v-text="$t('global.field.id')">ID</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('question')">
              <span v-text="$t('finalProjectApp.questionPart.question')">Question</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'question'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('correct')">
              <span v-text="$t('finalProjectApp.questionPart.correct')">Correct</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'correct'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('createdAt')">
              <span v-text="$t('finalProjectApp.questionPart.createdAt')">Created At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'createdAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('updatedAt')">
              <span v-text="$t('finalProjectApp.questionPart.updatedAt')">Updated At</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'updatedAt'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('answerQuestion.id')">
              <span v-text="$t('finalProjectApp.questionPart.answerQuestion')">Answer Question</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'answerQuestion.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('parts.id')">
              <span v-text="$t('finalProjectApp.questionPart.parts')">Parts</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'parts.id'"></jhi-sort-indicator>
            </th>
            <th scope="row" v-on:click="changeOrder('extendQuestion.id')">
              <span v-text="$t('finalProjectApp.questionPart.extendQuestion')">Extend Question</span>
              <jhi-sort-indicator :current-order="propOrder" :reverse="reverse" :field-name="'extendQuestion.id'"></jhi-sort-indicator>
            </th>
            <th scope="row"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="questionPart in questionParts" :key="questionPart.id" data-cy="entityTable">
            <td>
              <router-link :to="{ name: 'QuestionPartView', params: { questionPartId: questionPart.id } }">{{
                questionPart.id
              }}</router-link>
            </td>
            <td>{{ questionPart.question }}</td>
            <td>{{ questionPart.correct }}</td>
            <td>{{ questionPart.createdAt }}</td>
            <td>{{ questionPart.updatedAt }}</td>
            <td>
              <div v-if="questionPart.answerQuestion">
                <router-link :to="{ name: 'AnswerQuestionView', params: { answerQuestionId: questionPart.answerQuestion.id } }">{{
                  questionPart.answerQuestion.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="questionPart.parts">
                <router-link :to="{ name: 'PartsView', params: { partsId: questionPart.parts.id } }">{{
                  questionPart.parts.id
                }}</router-link>
              </div>
            </td>
            <td>
              <div v-if="questionPart.extendQuestion">
                <router-link :to="{ name: 'ExtendQuestionView', params: { extendQuestionId: questionPart.extendQuestion.id } }">{{
                  questionPart.extendQuestion.id
                }}</router-link>
              </div>
            </td>
            <td class="text-right">
              <div class="btn-group">
                <router-link :to="{ name: 'QuestionPartView', params: { questionPartId: questionPart.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-info btn-sm details" data-cy="entityDetailsButton">
                    <font-awesome-icon icon="eye"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.view')">View</span>
                  </button>
                </router-link>
                <router-link :to="{ name: 'QuestionPartEdit', params: { questionPartId: questionPart.id } }" custom v-slot="{ navigate }">
                  <button @click="navigate" class="btn btn-primary btn-sm edit" data-cy="entityEditButton">
                    <font-awesome-icon icon="pencil-alt"></font-awesome-icon>
                    <span class="d-none d-md-inline" v-text="$t('entity.action.edit')">Edit</span>
                  </button>
                </router-link>
                <b-button
                  v-on:click="prepareRemove(questionPart)"
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
          id="finalProjectApp.questionPart.delete.question"
          data-cy="questionPartDeleteDialogHeading"
          v-text="$t('entity.delete.title')"
          >Confirm delete operation</span
        ></span
      >
      <div class="modal-body">
        <p id="jhi-delete-questionPart-heading" v-text="$t('finalProjectApp.questionPart.delete.question', { id: removeId })">
          Are you sure you want to delete this Question Part?
        </p>
      </div>
      <div slot="modal-footer">
        <button type="button" class="btn btn-secondary" v-text="$t('entity.action.cancel')" v-on:click="closeDialog()">Cancel</button>
        <button
          type="button"
          class="btn btn-primary"
          id="jhi-confirm-delete-questionPart"
          data-cy="entityConfirmDeleteButton"
          v-text="$t('entity.action.delete')"
          v-on:click="removeQuestionPart()"
        >
          Delete
        </button>
      </div>
    </b-modal>
    <div v-show="questionParts && questionParts.length > 0">
      <div class="row justify-content-center">
        <jhi-item-count :page="page" :total="queryCount" :itemsPerPage="itemsPerPage"></jhi-item-count>
      </div>
      <div class="row justify-content-center">
        <b-pagination size="md" :total-rows="totalItems" v-model="page" :per-page="itemsPerPage" :change="loadPage(page)"></b-pagination>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./question-part.component.ts"></script>
