<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.toeicUser.home.createOrEditLabel"
          data-cy="ToeicUserCreateUpdateHeading"
          v-text="$t('finalProjectApp.toeicUser.home.createOrEditLabel')"
        >
          Create or edit a ToeicUser
        </h2>
        <div>
          <div class="form-group" v-if="toeicUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="toeicUser.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.userId')" for="toeic-user-userId">User Id</label>
            <input
              type="number"
              class="form-control"
              name="userId"
              id="toeic-user-userId"
              data-cy="userId"
              :class="{ valid: !$v.toeicUser.userId.$invalid, invalid: $v.toeicUser.userId.$invalid }"
              v-model.number="$v.toeicUser.userId.$model"
              required
            />
            <div v-if="$v.toeicUser.userId.$anyDirty && $v.toeicUser.userId.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeicUser.userId.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.toeicUser.userId.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.score')" for="toeic-user-score">Score</label>
            <input
              type="number"
              class="form-control"
              name="score"
              id="toeic-user-score"
              data-cy="score"
              :class="{ valid: !$v.toeicUser.score.$invalid, invalid: $v.toeicUser.score.$invalid }"
              v-model.number="$v.toeicUser.score.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.reading')" for="toeic-user-reading">Reading</label>
            <input
              type="text"
              class="form-control"
              name="reading"
              id="toeic-user-reading"
              data-cy="reading"
              :class="{ valid: !$v.toeicUser.reading.$invalid, invalid: $v.toeicUser.reading.$invalid }"
              v-model="$v.toeicUser.reading.$model"
              required
            />
            <div v-if="$v.toeicUser.reading.$anyDirty && $v.toeicUser.reading.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeicUser.reading.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.listening')" for="toeic-user-listening"
              >Listening</label
            >
            <input
              type="text"
              class="form-control"
              name="listening"
              id="toeic-user-listening"
              data-cy="listening"
              :class="{ valid: !$v.toeicUser.listening.$invalid, invalid: $v.toeicUser.listening.$invalid }"
              v-model="$v.toeicUser.listening.$model"
              required
            />
            <div v-if="$v.toeicUser.listening.$anyDirty && $v.toeicUser.listening.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeicUser.listening.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.createdAt')" for="toeic-user-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="toeic-user-createdAt"
                  v-model="$v.toeicUser.createdAt.$model"
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
                id="toeic-user-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.toeicUser.createdAt.$invalid, invalid: $v.toeicUser.createdAt.$invalid }"
                v-model="$v.toeicUser.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.toeicUser.createdAt.$anyDirty && $v.toeicUser.createdAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeicUser.createdAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.updatedAt')" for="toeic-user-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="toeic-user-updatedAt"
                  v-model="$v.toeicUser.updatedAt.$model"
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
                id="toeic-user-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.toeicUser.updatedAt.$invalid, invalid: $v.toeicUser.updatedAt.$invalid }"
                v-model="$v.toeicUser.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.toeicUser.updatedAt.$anyDirty && $v.toeicUser.updatedAt.$invalid">
              <small class="form-text text-danger" v-if="!$v.toeicUser.updatedAt.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.toeicUser.toeics')" for="toeic-user-toeics">Toeics</label>
            <select class="form-control" id="toeic-user-toeics" data-cy="toeics" name="toeics" v-model="toeicUser.toeics">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="toeicUser.toeics && toeicsOption.id === toeicUser.toeics.id ? toeicUser.toeics : toeicsOption"
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
            :disabled="$v.toeicUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./toeic-user-update.component.ts"></script>
