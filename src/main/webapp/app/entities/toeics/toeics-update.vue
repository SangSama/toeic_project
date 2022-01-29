<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.toeics.home.createOrEditLabel"
          data-cy="ToeicsCreateUpdateHeading"
          v-text="$t('finalProjectApp.toeics.home.createOrEditLabel')"
        >
          Create or edit a Toeics
        </h2>
        <div>
          <div class="form-group" v-if="toeics.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="toeics.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.nameToeic')" for="toeics-nameToeic">Name Toeic</label>
            <input
              type="text"
              class="form-control"
              name="nameToeic"
              id="toeics-nameToeic"
              data-cy="nameToeic"
              :class="{ valid: !$v.toeics.nameToeic.$invalid, invalid: $v.toeics.nameToeic.$invalid }"
              v-model="$v.toeics.nameToeic.$model"
              required
            />
            <div v-if="$v.toeics.nameToeic.$anyDirty && $v.toeics.nameToeic.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeics.nameToeic.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.toeics.nameToeic.maxLength"
                v-text="$t('entity.validation.maxlength', { max: 50 })"
              >
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.number')" for="toeics-number">Number</label>
            <input
              type="number"
              class="form-control"
              name="number"
              id="toeics-number"
              data-cy="number"
              :class="{ valid: !$v.toeics.number.$invalid, invalid: $v.toeics.number.$invalid }"
              v-model.number="$v.toeics.number.$model"
              required
            />
            <div v-if="$v.toeics.number.$anyDirty && $v.toeics.number.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeics.number.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.toeics.number.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.description')" for="toeics-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="toeics-description"
              data-cy="description"
              :class="{ valid: !$v.toeics.description.$invalid, invalid: $v.toeics.description.$invalid }"
              v-model="$v.toeics.description.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.view')" for="toeics-view">View</label>
            <input
              type="number"
              class="form-control"
              name="view"
              id="toeics-view"
              data-cy="view"
              :class="{ valid: !$v.toeics.view.$invalid, invalid: $v.toeics.view.$invalid }"
              v-model.number="$v.toeics.view.$model"
              required
            />
            <div v-if="$v.toeics.view.$anyDirty && $v.toeics.view.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeics.view.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.toeics.view.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.test')" for="toeics-test">Test</label>
            <input
              type="number"
              class="form-control"
              name="test"
              id="toeics-test"
              data-cy="test"
              :class="{ valid: !$v.toeics.test.$invalid, invalid: $v.toeics.test.$invalid }"
              v-model.number="$v.toeics.test.$model"
              required
            />
            <div v-if="$v.toeics.test.$anyDirty && $v.toeics.test.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeics.test.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.toeics.test.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.linkDetail')" for="toeics-linkDetail">Link Detail</label>
            <input
              type="text"
              class="form-control"
              name="linkDetail"
              id="toeics-linkDetail"
              data-cy="linkDetail"
              :class="{ valid: !$v.toeics.linkDetail.$invalid, invalid: $v.toeics.linkDetail.$invalid }"
              v-model="$v.toeics.linkDetail.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.createdAt')" for="toeics-createdAt">Created At</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="toeics-createdAt"
                  v-model="$v.toeics.createdAt.$model"
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
                id="toeics-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.toeics.createdAt.$invalid, invalid: $v.toeics.createdAt.$invalid }"
                v-model="$v.toeics.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.toeics.createdAt.$anyDirty && $v.toeics.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeics.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeics.updatedAt')" for="toeics-updatedAt">Updated At</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="toeics-updatedAt"
                  v-model="$v.toeics.updatedAt.$model"
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
                id="toeics-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.toeics.updatedAt.$invalid, invalid: $v.toeics.updatedAt.$invalid }"
                v-model="$v.toeics.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.toeics.updatedAt.$anyDirty && $v.toeics.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeics.updatedAt.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.toeics.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./toeics-update.component.ts"></script>
