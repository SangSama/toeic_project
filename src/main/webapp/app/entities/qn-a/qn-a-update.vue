<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.qnA.home.createOrEditLabel"
          data-cy="QnACreateUpdateHeading"
          v-text="$t('finalProjectApp.qnA.home.createOrEditLabel')"
        >
          Create or edit a QnA
        </h2>
        <div>
          <div class="form-group" v-if="qnA.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="qnA.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.qnA.userId')" for="qn-a-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="qn-a-userId"
              data-cy="userId"
              :class="{ valid: !$v.qnA.userId.$invalid, invalid: $v.qnA.userId.$invalid }"
              v-model.number="$v.qnA.userId.$model"
              required
            />
            <div v-if="$v.qnA.userId.$anyDirty && $v.qnA.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.qnA.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.qnA.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.qnA.email')" for="qn-a-email">Email</label>
            <input
              type="text"
              class="form-control"
              name="email"
              id="qn-a-email"
              data-cy="email"
              :class="{ valid: !$v.qnA.email.$invalid, invalid: $v.qnA.email.$invalid }"
              v-model="$v.qnA.email.$model"
              required
            />
            <div v-if="$v.qnA.email.$anyDirty && $v.qnA.email.$invalid">
              <small class="form-text text-danger" v-if="!$v.qnA.email.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.qnA.email.maxLength" v-text="$t('entity.validation.maxlength', { max: 50 })">
                This field cannot be longer than 50 characters.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.qnA.status')" for="qn-a-status">Status</label>
            <input
              type="number"
              class="form-control"
              name="status"
              id="qn-a-status"
              data-cy="status"
              :class="{ valid: !$v.qnA.status.$invalid, invalid: $v.qnA.status.$invalid }"
              v-model.number="$v.qnA.status.$model"
              required
            />
            <div v-if="$v.qnA.status.$anyDirty && $v.qnA.status.$invalid">
              <small class="form-text text-danger" v-if="!$v.qnA.status.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.qnA.status.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.qnA.createdAt')" for="qn-a-createdAt">Created At</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qn-a-createdAt"
                  v-model="$v.qnA.createdAt.$model"
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
                id="qn-a-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.qnA.createdAt.$invalid, invalid: $v.qnA.createdAt.$invalid }"
                v-model="$v.qnA.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.qnA.createdAt.$anyDirty && $v.qnA.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.qnA.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.qnA.updatedAt')" for="qn-a-updatedAt">Updated At</label>
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="qn-a-updatedAt"
                  v-model="$v.qnA.updatedAt.$model"
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
                id="qn-a-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.qnA.updatedAt.$invalid, invalid: $v.qnA.updatedAt.$invalid }"
                v-model="$v.qnA.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.qnA.updatedAt.$anyDirty && $v.qnA.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.qnA.updatedAt.required" v-text="$t('entity.validation.required')">
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
            :disabled="$v.qnA.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./qn-a-update.component.ts"></script>
