<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.grammarAnswer.home.createOrEditLabel"
          data-cy="GrammarAnswerCreateUpdateHeading"
          v-text="$t('finalProjectApp.grammarAnswer.home.createOrEditLabel')"
        >
          Create or edit a GrammarAnswer
        </h2>
        <div>
          <div class="form-group" v-if="grammarAnswer.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="grammarAnswer.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarAnswer.answerA')" for="grammar-answer-answerA"
              >Answer A</label
            >
            <input
              type="text"
              class="form-control"
              name="answerA"
              id="grammar-answer-answerA"
              data-cy="answerA"
              :class="{ valid: !$v.grammarAnswer.answerA.$invalid, invalid: $v.grammarAnswer.answerA.$invalid }"
              v-model="$v.grammarAnswer.answerA.$model"
              required
            />
            <div v-if="$v.grammarAnswer.answerA.$anyDirty && $v.grammarAnswer.answerA.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarAnswer.answerA.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarAnswer.answerB')" for="grammar-answer-answerB"
              >Answer B</label
            >
            <input
              type="text"
              class="form-control"
              name="answerB"
              id="grammar-answer-answerB"
              data-cy="answerB"
              :class="{ valid: !$v.grammarAnswer.answerB.$invalid, invalid: $v.grammarAnswer.answerB.$invalid }"
              v-model="$v.grammarAnswer.answerB.$model"
              required
            />
            <div v-if="$v.grammarAnswer.answerB.$anyDirty && $v.grammarAnswer.answerB.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarAnswer.answerB.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarAnswer.answerC')" for="grammar-answer-answerC"
              >Answer C</label
            >
            <input
              type="text"
              class="form-control"
              name="answerC"
              id="grammar-answer-answerC"
              data-cy="answerC"
              :class="{ valid: !$v.grammarAnswer.answerC.$invalid, invalid: $v.grammarAnswer.answerC.$invalid }"
              v-model="$v.grammarAnswer.answerC.$model"
              required
            />
            <div v-if="$v.grammarAnswer.answerC.$anyDirty && $v.grammarAnswer.answerC.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarAnswer.answerC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarAnswer.answerD')" for="grammar-answer-answerD"
              >Answer D</label
            >
            <input
              type="text"
              class="form-control"
              name="answerD"
              id="grammar-answer-answerD"
              data-cy="answerD"
              :class="{ valid: !$v.grammarAnswer.answerD.$invalid, invalid: $v.grammarAnswer.answerD.$invalid }"
              v-model="$v.grammarAnswer.answerD.$model"
              required
            />
            <div v-if="$v.grammarAnswer.answerD.$anyDirty && $v.grammarAnswer.answerD.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarAnswer.answerD.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarAnswer.createdAt')" for="grammar-answer-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-answer-createdAt"
                  v-model="$v.grammarAnswer.createdAt.$model"
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
                id="grammar-answer-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.grammarAnswer.createdAt.$invalid, invalid: $v.grammarAnswer.createdAt.$invalid }"
                v-model="$v.grammarAnswer.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarAnswer.createdAt.$anyDirty && $v.grammarAnswer.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarAnswer.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarAnswer.updatedAt')" for="grammar-answer-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-answer-updatedAt"
                  v-model="$v.grammarAnswer.updatedAt.$model"
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
                id="grammar-answer-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.grammarAnswer.updatedAt.$invalid, invalid: $v.grammarAnswer.updatedAt.$invalid }"
                v-model="$v.grammarAnswer.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarAnswer.updatedAt.$anyDirty && $v.grammarAnswer.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarAnswer.updatedAt.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.grammarAnswer.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./grammar-answer-update.component.ts"></script>
