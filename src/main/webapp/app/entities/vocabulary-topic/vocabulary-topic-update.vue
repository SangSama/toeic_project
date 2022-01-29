<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.vocabularyTopic.home.createOrEditLabel"
          data-cy="VocabularyTopicCreateUpdateHeading"
          v-text="$t('finalProjectApp.vocabularyTopic.home.createOrEditLabel')"
        >
          Create or edit a VocabularyTopic
        </h2>
        <div>
          <div class="form-group" v-if="vocabularyTopic.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="vocabularyTopic.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.nameTopic')" for="vocabulary-topic-nameTopic"
              >Name Topic</label
            >
            <input
              type="text"
              class="form-control"
              name="nameTopic"
              id="vocabulary-topic-nameTopic"
              data-cy="nameTopic"
              :class="{ valid: !$v.vocabularyTopic.nameTopic.$invalid, invalid: $v.vocabularyTopic.nameTopic.$invalid }"
              v-model="$v.vocabularyTopic.nameTopic.$model"
              required
            />
            <div v-if="$v.vocabularyTopic.nameTopic.$anyDirty && $v.vocabularyTopic.nameTopic.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.nameTopic.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.vocabularyTopic.nameTopic.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.description')" for="vocabulary-topic-description"
              >Description</label
            >
            <input
              type="text"
              class="form-control"
              name="description"
              id="vocabulary-topic-description"
              data-cy="description"
              :class="{ valid: !$v.vocabularyTopic.description.$invalid, invalid: $v.vocabularyTopic.description.$invalid }"
              v-model="$v.vocabularyTopic.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.view')" for="vocabulary-topic-view">View</label>
            <input
              type="number"
              class="form-control"
              name="view"
              id="vocabulary-topic-view"
              data-cy="view"
              :class="{ valid: !$v.vocabularyTopic.view.$invalid, invalid: $v.vocabularyTopic.view.$invalid }"
              v-model.number="$v.vocabularyTopic.view.$model"
              required
            />
            <div v-if="$v.vocabularyTopic.view.$anyDirty && $v.vocabularyTopic.view.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.view.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.view.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.test')" for="vocabulary-topic-test">Test</label>
            <input
              type="number"
              class="form-control"
              name="test"
              id="vocabulary-topic-test"
              data-cy="test"
              :class="{ valid: !$v.vocabularyTopic.test.$invalid, invalid: $v.vocabularyTopic.test.$invalid }"
              v-model.number="$v.vocabularyTopic.test.$model"
              required
            />
            <div v-if="$v.vocabularyTopic.test.$anyDirty && $v.vocabularyTopic.test.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.test.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.test.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.level')" for="vocabulary-topic-level"
              >Level</label
            >
            <input
              type="number"
              class="form-control"
              name="level"
              id="vocabulary-topic-level"
              data-cy="level"
              :class="{ valid: !$v.vocabularyTopic.level.$invalid, invalid: $v.vocabularyTopic.level.$invalid }"
              v-model.number="$v.vocabularyTopic.level.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.createdAt')" for="vocabulary-topic-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="vocabulary-topic-createdAt"
                  v-model="$v.vocabularyTopic.createdAt.$model"
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
                id="vocabulary-topic-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.vocabularyTopic.createdAt.$invalid, invalid: $v.vocabularyTopic.createdAt.$invalid }"
                v-model="$v.vocabularyTopic.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.vocabularyTopic.createdAt.$anyDirty && $v.vocabularyTopic.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyTopic.updatedAt')" for="vocabulary-topic-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="vocabulary-topic-updatedAt"
                  v-model="$v.vocabularyTopic.updatedAt.$model"
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
                id="vocabulary-topic-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.vocabularyTopic.updatedAt.$invalid, invalid: $v.vocabularyTopic.updatedAt.$invalid }"
                v-model="$v.vocabularyTopic.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.vocabularyTopic.updatedAt.$anyDirty && $v.vocabularyTopic.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyTopic.updatedAt.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.vocabularyTopic.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./vocabulary-topic-update.component.ts"></script>
