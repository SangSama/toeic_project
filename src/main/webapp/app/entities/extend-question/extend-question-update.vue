<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.extendQuestion.home.createOrEditLabel"
          data-cy="ExtendQuestionCreateUpdateHeading"
          v-text="$t('finalProjectApp.extendQuestion.home.createOrEditLabel')"
        >
          Create or edit a ExtendQuestion
        </h2>
        <div>
          <div class="form-group" v-if="extendQuestion.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="extendQuestion.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.extendQuestion.paragraph')" for="extend-question-paragraph"
              >Paragraph</label
            >
            <input
              type="text"
              class="form-control"
              name="paragraph"
              id="extend-question-paragraph"
              data-cy="paragraph"
              :class="{ valid: !$v.extendQuestion.paragraph.$invalid, invalid: $v.extendQuestion.paragraph.$invalid }"
              v-model="$v.extendQuestion.paragraph.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.extendQuestion.image')" for="extend-question-image">Image</label>
            <div>
              <img
                v-bind:src="'data:' + extendQuestion.imageContentType + ';base64,' + extendQuestion.image"
                style="max-height: 100px"
                v-if="extendQuestion.image"
                alt="extendQuestion image"
              />
              <div v-if="extendQuestion.image" class="form-text text-danger clearfix">
                <span class="pull-left">{{ extendQuestion.imageContentType }}, {{ byteSize(extendQuestion.image) }}</span>
                <button
                  type="button"
                  v-on:click="clearInputImage('image', 'imageContentType', 'file_image')"
                  class="btn btn-secondary btn-xs pull-right"
                >
                  <font-awesome-icon icon="times"></font-awesome-icon>
                </button>
              </div>
              <input
                type="file"
                ref="file_image"
                id="file_image"
                data-cy="image"
                v-on:change="setFileData($event, extendQuestion, 'image', true)"
                accept="image/*"
                v-text="$t('entity.action.addimage')"
              />
            </div>
            <input
              type="hidden"
              class="form-control"
              name="image"
              id="extend-question-image"
              data-cy="image"
              :class="{ valid: !$v.extendQuestion.image.$invalid, invalid: $v.extendQuestion.image.$invalid }"
              v-model="$v.extendQuestion.image.$model"
            />
            <input
              type="hidden"
              class="form-control"
              name="imageContentType"
              id="extend-question-imageContentType"
              v-model="extendQuestion.imageContentType"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.extendQuestion.createdAt')" for="extend-question-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="extend-question-createdAt"
                  v-model="$v.extendQuestion.createdAt.$model"
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
                id="extend-question-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.extendQuestion.createdAt.$invalid, invalid: $v.extendQuestion.createdAt.$invalid }"
                v-model="$v.extendQuestion.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.extendQuestion.createdAt.$anyDirty && $v.extendQuestion.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.extendQuestion.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.extendQuestion.updatedAt')" for="extend-question-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="extend-question-updatedAt"
                  v-model="$v.extendQuestion.updatedAt.$model"
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
                id="extend-question-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.extendQuestion.updatedAt.$invalid, invalid: $v.extendQuestion.updatedAt.$invalid }"
                v-model="$v.extendQuestion.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.extendQuestion.updatedAt.$anyDirty && $v.extendQuestion.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.extendQuestion.updatedAt.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.extendQuestion.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./extend-question-update.component.ts"></script>
