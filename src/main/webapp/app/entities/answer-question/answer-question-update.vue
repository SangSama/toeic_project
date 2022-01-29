<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.answerQuestion.home.createOrEditLabel"
          data-cy="AnswerQuestionCreateUpdateHeading"
          v-text="$t('finalProjectApp.answerQuestion.home.createOrEditLabel')"
        >
          Create or edit a AnswerQuestion
        </h2>
        <div>
          <div class="form-group" v-if="answerQuestion.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="answerQuestion.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.answerQuestion.answerA')" for="answer-question-answerA"
              >Answer A</label
            >
            <input
              type="text"
              class="form-control"
              name="answerA"
              id="answer-question-answerA"
              data-cy="answerA"
              :class="{ valid: !$v.answerQuestion.answerA.$invalid, invalid: $v.answerQuestion.answerA.$invalid }"
              v-model="$v.answerQuestion.answerA.$model"
              required
            />
            <div v-if="$v.answerQuestion.answerA.$anyDirty && $v.answerQuestion.answerA.$invalid">
              <small class="form-text text-danger" v-if="!$v.answerQuestion.answerA.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.answerQuestion.answerB')" for="answer-question-answerB"
              >Answer B</label
            >
            <input
              type="text"
              class="form-control"
              name="answerB"
              id="answer-question-answerB"
              data-cy="answerB"
              :class="{ valid: !$v.answerQuestion.answerB.$invalid, invalid: $v.answerQuestion.answerB.$invalid }"
              v-model="$v.answerQuestion.answerB.$model"
              required
            />
            <div v-if="$v.answerQuestion.answerB.$anyDirty && $v.answerQuestion.answerB.$invalid">
              <small class="form-text text-danger" v-if="!$v.answerQuestion.answerB.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.answerQuestion.answerC')" for="answer-question-answerC"
              >Answer C</label
            >
            <input
              type="text"
              class="form-control"
              name="answerC"
              id="answer-question-answerC"
              data-cy="answerC"
              :class="{ valid: !$v.answerQuestion.answerC.$invalid, invalid: $v.answerQuestion.answerC.$invalid }"
              v-model="$v.answerQuestion.answerC.$model"
              required
            />
            <div v-if="$v.answerQuestion.answerC.$anyDirty && $v.answerQuestion.answerC.$invalid">
              <small class="form-text text-danger" v-if="!$v.answerQuestion.answerC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.answerQuestion.answerD')" for="answer-question-answerD"
              >Answer D</label
            >
            <input
              type="text"
              class="form-control"
              name="answerD"
              id="answer-question-answerD"
              data-cy="answerD"
              :class="{ valid: !$v.answerQuestion.answerD.$invalid, invalid: $v.answerQuestion.answerD.$invalid }"
              v-model="$v.answerQuestion.answerD.$model"
              required
            />
            <div v-if="$v.answerQuestion.answerD.$anyDirty && $v.answerQuestion.answerD.$invalid">
              <small class="form-text text-danger" v-if="!$v.answerQuestion.answerD.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.answerQuestion.createdAt')" for="answer-question-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="answer-question-createdAt"
                  v-model="$v.answerQuestion.createdAt.$model"
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
                id="answer-question-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.answerQuestion.createdAt.$invalid, invalid: $v.answerQuestion.createdAt.$invalid }"
                v-model="$v.answerQuestion.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.answerQuestion.createdAt.$anyDirty && $v.answerQuestion.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.answerQuestion.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.answerQuestion.updatedAt')" for="answer-question-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="answer-question-updatedAt"
                  v-model="$v.answerQuestion.updatedAt.$model"
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
                id="answer-question-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.answerQuestion.updatedAt.$invalid, invalid: $v.answerQuestion.updatedAt.$invalid }"
                v-model="$v.answerQuestion.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.answerQuestion.updatedAt.$anyDirty && $v.answerQuestion.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.answerQuestion.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
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
            :disabled="$v.answerQuestion.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./answer-question-update.component.ts"></script>
