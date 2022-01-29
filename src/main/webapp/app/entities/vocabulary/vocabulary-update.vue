<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.vocabulary.home.createOrEditLabel"
          data-cy="VocabularyCreateUpdateHeading"
          v-text="$t('finalProjectApp.vocabulary.home.createOrEditLabel')"
        >
          Create or edit a Vocabulary
        </h2>
        <div>
          <div class="form-group" v-if="vocabulary.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="vocabulary.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabulary.word')" for="vocabulary-word">Word</label>
            <input
              type="text"
              class="form-control"
              name="word"
              id="vocabulary-word"
              data-cy="word"
              :class="{ valid: !$v.vocabulary.word.$invalid, invalid: $v.vocabulary.word.$invalid }"
              v-model="$v.vocabulary.word.$model"
              required
            />
            <div v-if="$v.vocabulary.word.$anyDirty && $v.vocabulary.word.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabulary.word.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabulary.mean')" for="vocabulary-mean">Mean</label>
            <input
              type="text"
              class="form-control"
              name="mean"
              id="vocabulary-mean"
              data-cy="mean"
              :class="{ valid: !$v.vocabulary.mean.$invalid, invalid: $v.vocabulary.mean.$invalid }"
              v-model="$v.vocabulary.mean.$model"
              required
            />
            <div v-if="$v.vocabulary.mean.$anyDirty && $v.vocabulary.mean.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabulary.mean.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabulary.createdAt')" for="vocabulary-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="vocabulary-createdAt"
                  v-model="$v.vocabulary.createdAt.$model"
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
                id="vocabulary-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.vocabulary.createdAt.$invalid, invalid: $v.vocabulary.createdAt.$invalid }"
                v-model="$v.vocabulary.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.vocabulary.createdAt.$anyDirty && $v.vocabulary.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabulary.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabulary.updatedAt')" for="vocabulary-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="vocabulary-updatedAt"
                  v-model="$v.vocabulary.updatedAt.$model"
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
                id="vocabulary-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.vocabulary.updatedAt.$invalid, invalid: $v.vocabulary.updatedAt.$invalid }"
                v-model="$v.vocabulary.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.vocabulary.updatedAt.$anyDirty && $v.vocabulary.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabulary.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabulary.vocabularyTopic')" for="vocabulary-vocabularyTopic"
              >Vocabulary Topic</label
            >
            <select
              class="form-control"
              id="vocabulary-vocabularyTopic"
              data-cy="vocabularyTopic"
              name="vocabularyTopic"
              v-model="vocabulary.vocabularyTopic"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  vocabulary.vocabularyTopic && vocabularyTopicOption.id === vocabulary.vocabularyTopic.id
                    ? vocabulary.vocabularyTopic
                    : vocabularyTopicOption
                "
                v-for="vocabularyTopicOption in vocabularyTopics"
                :key="vocabularyTopicOption.id"
              >
                {{ vocabularyTopicOption.id }}
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
            :disabled="$v.vocabulary.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./vocabulary-update.component.ts"></script>
