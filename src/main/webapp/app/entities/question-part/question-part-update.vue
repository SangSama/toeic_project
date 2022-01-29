<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.questionPart.home.createOrEditLabel"
          data-cy="QuestionPartCreateUpdateHeading"
          v-text="$t('finalProjectApp.questionPart.home.createOrEditLabel')"
        >
          Create or edit a QuestionPart
        </h2>
        <div>
          <div class="form-group" v-if="questionPart.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="questionPart.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.question')" for="question-part-question"
              >Question</label
            >
            <input
              type="text"
              class="form-control"
              name="question"
              id="question-part-question"
              data-cy="question"
              :class="{ valid: !$v.questionPart.question.$invalid, invalid: $v.questionPart.question.$invalid }"
              v-model="$v.questionPart.question.$model"
              required
            />
            <div v-if="$v.questionPart.question.$anyDirty && $v.questionPart.question.$invalid">
              <small class="form-text text-danger" v-if="!$v.questionPart.question.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.correct')" for="question-part-correct"
              >Correct</label
            >
            <input
              type="text"
              class="form-control"
              name="correct"
              id="question-part-correct"
              data-cy="correct"
              :class="{ valid: !$v.questionPart.correct.$invalid, invalid: $v.questionPart.correct.$invalid }"
              v-model="$v.questionPart.correct.$model"
              required
            />
            <div v-if="$v.questionPart.correct.$anyDirty && $v.questionPart.correct.$invalid">
              <small class="form-text text-danger" v-if="!$v.questionPart.correct.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.createdAt')" for="question-part-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="question-part-createdAt"
                  v-model="$v.questionPart.createdAt.$model"
                  name="createdAt"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="question-part-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.questionPart.createdAt.$invalid, invalid: $v.questionPart.createdAt.$invalid }"
                v-model="$v.questionPart.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.questionPart.createdAt.$anyDirty && $v.questionPart.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.questionPart.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.updatedAt')" for="question-part-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="question-part-updatedAt"
                  v-model="$v.questionPart.updatedAt.$model"
                  name="updatedAt"
                  class="form-control"
                  :locale="currentLanguage"
                  button-only
                  today-button
                  reset-button
                  close-button
                >
                </b-form-datepicker>
              </b-input-group-prepend>
              <b-form-input
                id="question-part-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.questionPart.updatedAt.$invalid, invalid: $v.questionPart.updatedAt.$invalid }"
                v-model="$v.questionPart.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.questionPart.updatedAt.$anyDirty && $v.questionPart.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.questionPart.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.answerQuestion')" for="question-part-answerQuestion"
              >Answer Question</label
            >
            <select
              class="form-control"
              id="question-part-answerQuestion"
              data-cy="answerQuestion"
              name="answerQuestion"
              v-model="questionPart.answerQuestion"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  questionPart.answerQuestion && answerQuestionOption.id === questionPart.answerQuestion.id
                    ? questionPart.answerQuestion
                    : answerQuestionOption
                "
                v-for="answerQuestionOption in answerQuestions"
                :key="answerQuestionOption.id"
              >
                {{ answerQuestionOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.parts')" for="question-part-parts">Parts</label>
            <select class="form-control" id="question-part-parts" data-cy="parts" name="parts" v-model="questionPart.parts">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="questionPart.parts && partsOption.id === questionPart.parts.id ? questionPart.parts : partsOption"
                v-for="partsOption in parts"
                :key="partsOption.id"
              >
                {{ partsOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.questionPart.extendQuestion')" for="question-part-extendQuestion"
              >Extend Question</label
            >
            <select
              class="form-control"
              id="question-part-extendQuestion"
              data-cy="extendQuestion"
              name="extendQuestion"
              v-model="questionPart.extendQuestion"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  questionPart.extendQuestion && extendQuestionOption.id === questionPart.extendQuestion.id
                    ? questionPart.extendQuestion
                    : extendQuestionOption
                "
                v-for="extendQuestionOption in extendQuestions"
                :key="extendQuestionOption.id"
              >
                {{ extendQuestionOption.id }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.cancel')">Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.questionPart.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./question-part-update.component.ts"></script>
