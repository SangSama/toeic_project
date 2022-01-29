<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.vocabularyUser.home.createOrEditLabel"
          data-cy="VocabularyUserCreateUpdateHeading"
          v-text="$t('finalProjectApp.vocabularyUser.home.createOrEditLabel')"
        >
          Create or edit a VocabularyUser
        </h2>
        <div>
          <div class="form-group" v-if="vocabularyUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="vocabularyUser.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyUser.userId')" for="vocabulary-user-userId"
              >User Id</label
            >
            <input
              type="number"
              class="form-control"
              name="userId"
              id="vocabulary-user-userId"
              data-cy="userId"
              :class="{ valid: !$v.vocabularyUser.userId.$invalid, invalid: $v.vocabularyUser.userId.$invalid }"
              v-model.number="$v.vocabularyUser.userId.$model"
              required
            />
            <div v-if="$v.vocabularyUser.userId.$anyDirty && $v.vocabularyUser.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyUser.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.vocabularyUser.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyUser.score')" for="vocabulary-user-score">Score</label>
            <input
              type="number"
              class="form-control"
              name="score"
              id="vocabulary-user-score"
              data-cy="score"
              :class="{ valid: !$v.vocabularyUser.score.$invalid, invalid: $v.vocabularyUser.score.$invalid }"
              v-model.number="$v.vocabularyUser.score.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyUser.createdAt')" for="vocabulary-user-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="vocabulary-user-createdAt"
                  v-model="$v.vocabularyUser.createdAt.$model"
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
                id="vocabulary-user-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.vocabularyUser.createdAt.$invalid, invalid: $v.vocabularyUser.createdAt.$invalid }"
                v-model="$v.vocabularyUser.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.vocabularyUser.createdAt.$anyDirty && $v.vocabularyUser.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyUser.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.vocabularyUser.updatedAt')" for="vocabulary-user-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="vocabulary-user-updatedAt"
                  v-model="$v.vocabularyUser.updatedAt.$model"
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
                id="vocabulary-user-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.vocabularyUser.updatedAt.$invalid, invalid: $v.vocabularyUser.updatedAt.$invalid }"
                v-model="$v.vocabularyUser.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.vocabularyUser.updatedAt.$anyDirty && $v.vocabularyUser.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.vocabularyUser.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.vocabularyUser.vocabularyTopic')"
              for="vocabulary-user-vocabularyTopic"
              >Vocabulary Topic</label
            >
            <select
              class="form-control"
              id="vocabulary-user-vocabularyTopic"
              data-cy="vocabularyTopic"
              name="vocabularyTopic"
              v-model="vocabularyUser.vocabularyTopic"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  vocabularyUser.vocabularyTopic && vocabularyTopicOption.id === vocabularyUser.vocabularyTopic.id
                    ? vocabularyUser.vocabularyTopic
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
            :disabled="$v.vocabularyUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./vocabulary-user-update.component.ts"></script>
