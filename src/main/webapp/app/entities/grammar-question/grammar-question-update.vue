<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.grammarQuestion.home.createOrEditLabel"
          data-cy="GrammarQuestionCreateUpdateHeading"
          v-text="$t('finalProjectApp.grammarQuestion.home.createOrEditLabel')"
        >
          Create or edit a GrammarQuestion
        </h2>
        <div>
          <div class="form-group" v-if="grammarQuestion.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="grammarQuestion.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarQuestion.question')" for="grammar-question-question"
              >Question</label
            >
            <input
              type="text"
              class="form-control"
              name="question"
              id="grammar-question-question"
              data-cy="question"
              :class="{ valid: !$v.grammarQuestion.question.$invalid, invalid: $v.grammarQuestion.question.$invalid }"
              v-model="$v.grammarQuestion.question.$model"
              required
            />
            <div v-if="$v.grammarQuestion.question.$anyDirty && $v.grammarQuestion.question.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarQuestion.question.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarQuestion.correct')" for="grammar-question-correct"
              >Correct</label
            >
            <input
              type="text"
              class="form-control"
              name="correct"
              id="grammar-question-correct"
              data-cy="correct"
              :class="{ valid: !$v.grammarQuestion.correct.$invalid, invalid: $v.grammarQuestion.correct.$invalid }"
              v-model="$v.grammarQuestion.correct.$model"
              required
            />
            <div v-if="$v.grammarQuestion.correct.$anyDirty && $v.grammarQuestion.correct.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarQuestion.correct.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarQuestion.createdAt')" for="grammar-question-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-question-createdAt"
                  v-model="$v.grammarQuestion.createdAt.$model"
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
                id="grammar-question-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.grammarQuestion.createdAt.$invalid, invalid: $v.grammarQuestion.createdAt.$invalid }"
                v-model="$v.grammarQuestion.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarQuestion.createdAt.$anyDirty && $v.grammarQuestion.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarQuestion.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarQuestion.updatedAt')" for="grammar-question-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-question-updatedAt"
                  v-model="$v.grammarQuestion.updatedAt.$model"
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
                id="grammar-question-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.grammarQuestion.updatedAt.$invalid, invalid: $v.grammarQuestion.updatedAt.$invalid }"
                v-model="$v.grammarQuestion.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarQuestion.updatedAt.$anyDirty && $v.grammarQuestion.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarQuestion.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.grammarQuestion.grammarAnswer')"
              for="grammar-question-grammarAnswer"
              >Grammar Answer</label
            >
            <select
              class="form-control"
              id="grammar-question-grammarAnswer"
              data-cy="grammarAnswer"
              name="grammarAnswer"
              v-model="grammarQuestion.grammarAnswer"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  grammarQuestion.grammarAnswer && grammarAnswerOption.id === grammarQuestion.grammarAnswer.id
                    ? grammarQuestion.grammarAnswer
                    : grammarAnswerOption
                "
                v-for="grammarAnswerOption in grammarAnswers"
                :key="grammarAnswerOption.id"
              >
                {{ grammarAnswerOption.id }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.grammarQuestion.grammarTopic')"
              for="grammar-question-grammarTopic"
              >Grammar Topic</label
            >
            <select
              class="form-control"
              id="grammar-question-grammarTopic"
              data-cy="grammarTopic"
              name="grammarTopic"
              v-model="grammarQuestion.grammarTopic"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  grammarQuestion.grammarTopic && grammarTopicOption.id === grammarQuestion.grammarTopic.id
                    ? grammarQuestion.grammarTopic
                    : grammarTopicOption
                "
                v-for="grammarTopicOption in grammarTopics"
                :key="grammarTopicOption.id"
              >
                {{ grammarTopicOption.id }}
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
            :disabled="$v.grammarQuestion.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./grammar-question-update.component.ts"></script>
