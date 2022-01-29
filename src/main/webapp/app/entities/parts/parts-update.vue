<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.parts.home.createOrEditLabel"
          data-cy="PartsCreateUpdateHeading"
          v-text="$t('finalProjectApp.parts.home.createOrEditLabel')"
        >
          Create or edit a Parts
        </h2>
        <div>
          <div class="form-group" v-if="parts.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="parts.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.part')" for="parts-part">Part</label>
            <input
              type="number"
              class="form-control"
              name="part"
              id="parts-part"
              data-cy="part"
              :class="{ valid: !$v.parts.part.$invalid, invalid: $v.parts.part.$invalid }"
              v-model.number="$v.parts.part.$model"
              required
            />
            <div v-if="$v.parts.part.$anyDirty && $v.parts.part.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.part.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.parts.part.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.description')" for="parts-description">Description</label>
            <input
              type="text"
              class="form-control"
              name="description"
              id="parts-description"
              data-cy="description"
              :class="{ valid: !$v.parts.description.$invalid, invalid: $v.parts.description.$invalid }"
              v-model="$v.parts.description.$model"
              required
            />
            <div v-if="$v.parts.description.$anyDirty && $v.parts.description.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.description.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.example')" for="parts-example">Example</label>
            <input
              type="text"
              class="form-control"
              name="example"
              id="parts-example"
              data-cy="example"
              :class="{ valid: !$v.parts.example.$invalid, invalid: $v.parts.example.$invalid }"
              v-model="$v.parts.example.$model"
              required
            />
            <div v-if="$v.parts.example.$anyDirty && $v.parts.example.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.example.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.fileLC')" for="parts-fileLC">File LC</label>
            <input
              type="text"
              class="form-control"
              name="fileLC"
              id="parts-fileLC"
              data-cy="fileLC"
              :class="{ valid: !$v.parts.fileLC.$invalid, invalid: $v.parts.fileLC.$invalid }"
              v-model="$v.parts.fileLC.$model"
              required
            />
            <div v-if="$v.parts.fileLC.$anyDirty && $v.parts.fileLC.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.fileLC.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.view')" for="parts-view">View</label>
            <input
              type="number"
              class="form-control"
              name="view"
              id="parts-view"
              data-cy="view"
              :class="{ valid: !$v.parts.view.$invalid, invalid: $v.parts.view.$invalid }"
              v-model.number="$v.parts.view.$model"
              required
            />
            <div v-if="$v.parts.view.$anyDirty && $v.parts.view.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.view.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.parts.view.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.test')" for="parts-test">Test</label>
            <input
              type="number"
              class="form-control"
              name="test"
              id="parts-test"
              data-cy="test"
              :class="{ valid: !$v.parts.test.$invalid, invalid: $v.parts.test.$invalid }"
              v-model.number="$v.parts.test.$model"
              required
            />
            <div v-if="$v.parts.test.$anyDirty && $v.parts.test.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.test.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.parts.test.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.createdAt')" for="parts-createdAt">Created At</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="parts-createdAt"
                  v-model="$v.parts.createdAt.$model"
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
                id="parts-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.parts.createdAt.$invalid, invalid: $v.parts.createdAt.$invalid }"
                v-model="$v.parts.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.parts.createdAt.$anyDirty && $v.parts.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.updatedAt')" for="parts-updatedAt">Updated At</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="parts-updatedAt"
                  v-model="$v.parts.updatedAt.$model"
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
                id="parts-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.parts.updatedAt.$invalid, invalid: $v.parts.updatedAt.$invalid }"
                v-model="$v.parts.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.parts.updatedAt.$anyDirty && $v.parts.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.parts.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.parts.toeics')" for="parts-toeics">Toeics</label>
            <select class="form-control" id="parts-toeics" data-cy="toeics" name="toeics" v-model="parts.toeics">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="parts.toeics && toeicsOption.id === parts.toeics.id ? parts.toeics : toeicsOption"
                v-for="toeicsOption in toeics"
                :key="toeicsOption.id"
              >
                {{ toeicsOption.id }}
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
            :disabled="$v.parts.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./parts-update.component.ts"></script>
