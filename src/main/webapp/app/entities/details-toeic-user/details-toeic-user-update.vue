<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.detailsToeicUser.home.createOrEditLabel"
          data-cy="DetailsToeicUserCreateUpdateHeading"
          v-text="$t('finalProjectApp.detailsToeicUser.home.createOrEditLabel')"
        >
          Create or edit a DetailsToeicUser
        </h2>
        <div>
          <div class="form-group" v-if="detailsToeicUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="detailsToeicUser.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.toeicId')" for="details-toeic-user-toeicId"
              >Toeic Id</label
            >
            <input
              type="number"
              class="form-control"
              name="toeicId"
              id="details-toeic-user-toeicId"
              data-cy="toeicId"
              :class="{ valid: !$v.detailsToeicUser.toeicId.$invalid, invalid: $v.detailsToeicUser.toeicId.$invalid }"
              v-model.number="$v.detailsToeicUser.toeicId.$model"
              required
            />
            <div v-if="$v.detailsToeicUser.toeicId.$anyDirty && $v.detailsToeicUser.toeicId.$invalid">
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.toeicId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.toeicId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.partId')" for="details-toeic-user-partId"
              >Part Id</label
            >
            <input
              type="number"
              class="form-control"
              name="partId"
              id="details-toeic-user-partId"
              data-cy="partId"
              :class="{ valid: !$v.detailsToeicUser.partId.$invalid, invalid: $v.detailsToeicUser.partId.$invalid }"
              v-model.number="$v.detailsToeicUser.partId.$model"
              required
            />
            <div v-if="$v.detailsToeicUser.partId.$anyDirty && $v.detailsToeicUser.partId.$invalid">
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.partId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.partId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.questionId')" for="details-toeic-user-questionId"
              >Question Id</label
            >
            <input
              type="number"
              class="form-control"
              name="questionId"
              id="details-toeic-user-questionId"
              data-cy="questionId"
              :class="{ valid: !$v.detailsToeicUser.questionId.$invalid, invalid: $v.detailsToeicUser.questionId.$invalid }"
              v-model.number="$v.detailsToeicUser.questionId.$model"
              required
            />
            <div v-if="$v.detailsToeicUser.questionId.$anyDirty && $v.detailsToeicUser.questionId.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsToeicUser.questionId.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.questionId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.status')" for="details-toeic-user-status"
              >Status</label
            >
            <input
              type="number"
              class="form-control"
              name="status"
              id="details-toeic-user-status"
              data-cy="status"
              :class="{ valid: !$v.detailsToeicUser.status.$invalid, invalid: $v.detailsToeicUser.status.$invalid }"
              v-model.number="$v.detailsToeicUser.status.$model"
              required
            />
            <div v-if="$v.detailsToeicUser.status.$anyDirty && $v.detailsToeicUser.status.$invalid">
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.status.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.status.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.createdAt')" for="details-toeic-user-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="details-toeic-user-createdAt"
                  v-model="$v.detailsToeicUser.createdAt.$model"
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
                id="details-toeic-user-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.detailsToeicUser.createdAt.$invalid, invalid: $v.detailsToeicUser.createdAt.$invalid }"
                v-model="$v.detailsToeicUser.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.detailsToeicUser.createdAt.$anyDirty && $v.detailsToeicUser.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.updatedAt')" for="details-toeic-user-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="details-toeic-user-updatedAt"
                  v-model="$v.detailsToeicUser.updatedAt.$model"
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
                id="details-toeic-user-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.detailsToeicUser.updatedAt.$invalid, invalid: $v.detailsToeicUser.updatedAt.$invalid }"
                v-model="$v.detailsToeicUser.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.detailsToeicUser.updatedAt.$anyDirty && $v.detailsToeicUser.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.detailsToeicUser.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsToeicUser.toeicUser')" for="details-toeic-user-toeicUser"
              >Toeic User</label
            >
            <select
              class="form-control"
              id="details-toeic-user-toeicUser"
              data-cy="toeicUser"
              name="toeicUser"
              v-model="detailsToeicUser.toeicUser"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  detailsToeicUser.toeicUser && toeicUserOption.id === detailsToeicUser.toeicUser.id
                    ? detailsToeicUser.toeicUser
                    : toeicUserOption
                "
                v-for="toeicUserOption in toeicUsers"
                :key="toeicUserOption.id"
              >
                {{ toeicUserOption.id }}
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
            :disabled="$v.detailsToeicUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./details-toeic-user-update.component.ts"></script>
