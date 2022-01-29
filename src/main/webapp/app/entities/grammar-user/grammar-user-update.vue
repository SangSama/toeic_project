<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.grammarUser.home.createOrEditLabel"
          data-cy="GrammarUserCreateUpdateHeading"
          v-text="$t('finalProjectApp.grammarUser.home.createOrEditLabel')"
        >
          Create or edit a GrammarUser
        </h2>
        <div>
          <div class="form-group" v-if="grammarUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="grammarUser.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarUser.userId')" for="grammar-user-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="grammar-user-userId"
              data-cy="userId"
              :class="{ valid: !$v.grammarUser.userId.$invalid, invalid: $v.grammarUser.userId.$invalid }"
              v-model.number="$v.grammarUser.userId.$model"
              required
            />
            <div v-if="$v.grammarUser.userId.$anyDirty && $v.grammarUser.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarUser.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.grammarUser.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarUser.score')" for="grammar-user-score">Score</label>
            <input
              type="number"
              class="form-control"
              name="score"
              id="grammar-user-score"
              data-cy="score"
              :class="{ valid: !$v.grammarUser.score.$invalid, invalid: $v.grammarUser.score.$invalid }"
              v-model.number="$v.grammarUser.score.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarUser.createdAt')" for="grammar-user-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-user-createdAt"
                  v-model="$v.grammarUser.createdAt.$model"
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
                id="grammar-user-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.grammarUser.createdAt.$invalid, invalid: $v.grammarUser.createdAt.$invalid }"
                v-model="$v.grammarUser.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarUser.createdAt.$anyDirty && $v.grammarUser.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarUser.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarUser.updatedAt')" for="grammar-user-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="grammar-user-updatedAt"
                  v-model="$v.grammarUser.updatedAt.$model"
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
                id="grammar-user-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.grammarUser.updatedAt.$invalid, invalid: $v.grammarUser.updatedAt.$invalid }"
                v-model="$v.grammarUser.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.grammarUser.updatedAt.$anyDirty && $v.grammarUser.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.grammarUser.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.grammarUser.grammarTopic')" for="grammar-user-grammarTopic"
              >Grammar Topic</label
            >
            <select
              class="form-control"
              id="grammar-user-grammarTopic"
              data-cy="grammarTopic"
              name="grammarTopic"
              v-model="grammarUser.grammarTopic"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  grammarUser.grammarTopic && grammarTopicOption.id === grammarUser.grammarTopic.id
                    ? grammarUser.grammarTopic
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
            :disabled="$v.grammarUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./grammar-user-update.component.ts"></script>
