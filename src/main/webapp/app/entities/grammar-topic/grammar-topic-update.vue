<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.grammarTopic.home.createOrEditLabel"
          data-cy="GrammarTopicCreateUpdateHeading"
          v-text="$t('finalProjectApp.grammarTopic.home.createOrEditLabel')"
        >
          Create or edit a GrammarTopic
        </h2>
        <div>
          <div class="form-group" v-if="grammarTopic.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="grammarTopic.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.nameTopic')" for="grammar-topic-nameTopic"
              >Name Topic</label
            >
            <input
              type="text"
              class="form-control"
              name="nameTopic"
              id="grammar-topic-nameTopic"
              data-cy="nameTopic"
              :class="{ valid: !$v.grammarTopic.nameTopic.$invalid, invalid: $v.grammarTopic.nameTopic.$invalid }"
              v-model="$v.grammarTopic.nameTopic.$model"
              required
            />
            <div v-if="$v.grammarTopic.nameTopic.$anyDirty && $v.grammarTopic.nameTopic.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarTopic.nameTopic.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.grammarTopic.nameTopic.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 100 })"
              >
                This field cannot be longer than 100 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.description')" for="grammar-topic-description"
              >Description</label
            >
            <input
              type="text"
              class="form-control"
              name="description"
              id="grammar-topic-description"
              data-cy="description"
              :class="{ valid: !$v.grammarTopic.description.$invalid, invalid: $v.grammarTopic.description.$invalid }"
              v-model="$v.grammarTopic.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.view')" for="grammar-topic-view">View</label>
            <input
              type="number"
              class="form-control"
              name="view"
              id="grammar-topic-view"
              data-cy="view"
              :class="{ valid: !$v.grammarTopic.view.$invalid, invalid: $v.grammarTopic.view.$invalid }"
              v-model.number="$v.grammarTopic.view.$model"
              required
            />
            <div v-if="$v.grammarTopic.view.$anyDirty && $v.grammarTopic.view.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarTopic.view.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.grammarTopic.view.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.test')" for="grammar-topic-test">Test</label>
            <input
              type="number"
              class="form-control"
              name="test"
              id="grammar-topic-test"
              data-cy="test"
              :class="{ valid: !$v.grammarTopic.test.$invalid, invalid: $v.grammarTopic.test.$invalid }"
              v-model.number="$v.grammarTopic.test.$model"
              required
            />
            <div v-if="$v.grammarTopic.test.$anyDirty && $v.grammarTopic.test.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarTopic.test.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.grammarTopic.test.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.level')" for="grammar-topic-level">Level</label>
            <input
              type="number"
              class="form-control"
              name="level"
              id="grammar-topic-level"
              data-cy="level"
              :class="{ valid: !$v.grammarTopic.level.$invalid, invalid: $v.grammarTopic.level.$invalid }"
              v-model.number="$v.grammarTopic.level.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.filePractice')" for="grammar-topic-filePractice"
              >File Practice</label
            >
            <input
              type="text"
              class="form-control"
              name="filePractice"
              id="grammar-topic-filePractice"
              data-cy="filePractice"
              :class="{ valid: !$v.grammarTopic.filePractice.$invalid, invalid: $v.grammarTopic.filePractice.$invalid }"
              v-model="$v.grammarTopic.filePractice.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.createdAt')" for="grammar-topic-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-topic-createdAt"
                  v-model="$v.grammarTopic.createdAt.$model"
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
                id="grammar-topic-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.grammarTopic.createdAt.$invalid, invalid: $v.grammarTopic.createdAt.$invalid }"
                v-model="$v.grammarTopic.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarTopic.createdAt.$anyDirty && $v.grammarTopic.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarTopic.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarTopic.updatedAt')" for="grammar-topic-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-topic-updatedAt"
                  v-model="$v.grammarTopic.updatedAt.$model"
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
                id="grammar-topic-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.grammarTopic.updatedAt.$invalid, invalid: $v.grammarTopic.updatedAt.$invalid }"
                v-model="$v.grammarTopic.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarTopic.updatedAt.$anyDirty && $v.grammarTopic.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarTopic.updatedAt.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.grammarTopic.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./grammar-topic-update.component.ts"></script>
