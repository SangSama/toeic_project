<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.detailsVocabularyUser.home.createOrEditLabel"
          data-cy="DetailsVocabularyUserCreateUpdateHeading"
          v-text="$t('finalProjectApp.detailsVocabularyUser.home.createOrEditLabel')"
        >
          Create or edit a DetailsVocabularyUser
        </h2>
        <div>
          <div class="form-group" v-if="detailsVocabularyUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="detailsVocabularyUser.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsVocabularyUser.vocabularyId')"
              for="details-vocabulary-user-vocabularyId"
              >Vocabulary Id</label
            >
            <input
              type="number"
              class="form-control"
              name="vocabularyId"
              id="details-vocabulary-user-vocabularyId"
              data-cy="vocabularyId"
              :class="{ valid: !$v.detailsVocabularyUser.vocabularyId.$invalid, invalid: $v.detailsVocabularyUser.vocabularyId.$invalid }"
              v-model.number="$v.detailsVocabularyUser.vocabularyId.$model"
              required
            />
            <div v-if="$v.detailsVocabularyUser.vocabularyId.$anyDirty && $v.detailsVocabularyUser.vocabularyId.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsVocabularyUser.vocabularyId.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.detailsVocabularyUser.vocabularyId.numeric"
                v-text="$t('entity.validation.number')"
              >
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsVocabularyUser.status')"
              for="details-vocabulary-user-status"
              >Status</label
            >
            <input
              type="number"
              class="form-control"
              name="status"
              id="details-vocabulary-user-status"
              data-cy="status"
              :class="{ valid: !$v.detailsVocabularyUser.status.$invalid, invalid: $v.detailsVocabularyUser.status.$invalid }"
              v-model.number="$v.detailsVocabularyUser.status.$model"
              required
            />
            <div v-if="$v.detailsVocabularyUser.status.$anyDirty && $v.detailsVocabularyUser.status.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsVocabularyUser.status.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.detailsVocabularyUser.status.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsVocabularyUser.createdAt')"
              for="details-vocabulary-user-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="details-vocabulary-user-createdAt"
                  v-model="$v.detailsVocabularyUser.createdAt.$model"
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
                id="details-vocabulary-user-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.detailsVocabularyUser.createdAt.$invalid, invalid: $v.detailsVocabularyUser.createdAt.$invalid }"
                v-model="$v.detailsVocabularyUser.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.detailsVocabularyUser.createdAt.$anyDirty && $v.detailsVocabularyUser.createdAt.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsVocabularyUser.createdAt.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsVocabularyUser.updatedAt')"
              for="details-vocabulary-user-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="details-vocabulary-user-updatedAt"
                  v-model="$v.detailsVocabularyUser.updatedAt.$model"
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
                id="details-vocabulary-user-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.detailsVocabularyUser.updatedAt.$invalid, invalid: $v.detailsVocabularyUser.updatedAt.$invalid }"
                v-model="$v.detailsVocabularyUser.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.detailsVocabularyUser.updatedAt.$anyDirty && $v.detailsVocabularyUser.updatedAt.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsVocabularyUser.updatedAt.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsVocabularyUser.vocabularyUser')"
              for="details-vocabulary-user-vocabularyUser"
              >Vocabulary User</label
            >
            <select
              class="form-control"
              id="details-vocabulary-user-vocabularyUser"
              data-cy="vocabularyUser"
              name="vocabularyUser"
              v-model="detailsVocabularyUser.vocabularyUser"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  detailsVocabularyUser.vocabularyUser && vocabularyUserOption.id === detailsVocabularyUser.vocabularyUser.id
                    ? detailsVocabularyUser.vocabularyUser
                    : vocabularyUserOption
                "
                v-for="vocabularyUserOption in vocabularyUsers"
                :key="vocabularyUserOption.id"
              >
                {{ vocabularyUserOption.id }}
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
            :disabled="$v.detailsVocabularyUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./details-vocabulary-user-update.component.ts"></script>
