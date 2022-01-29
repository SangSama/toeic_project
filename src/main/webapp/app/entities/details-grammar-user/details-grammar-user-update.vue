<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2
          id="finalProjectApp.detailsGrammarUser.home.createOrEditLabel"
          data-cy="DetailsGrammarUserCreateUpdateHeading"
          v-text="$t('finalProjectApp.detailsGrammarUser.home.createOrEditLabel')"
        >
          Create or edit a DetailsGrammarUser
        </h2>
        <div>
          <div class="form-group" v-if="detailsGrammarUser.id">
            <label for="id" v-text="$t('global.field.id')">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="detailsGrammarUser.id" readonly />
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsGrammarUser.grammarQuestionId')"
              for="details-grammar-user-grammarQuestionId"
              >Grammar Question Id</label
            >
            <input
              type="number"
              class="form-control"
              name="grammarQuestionId"
              id="details-grammar-user-grammarQuestionId"
              data-cy="grammarQuestionId"
              :class="{
                valid: !$v.detailsGrammarUser.grammarQuestionId.$invalid,
                invalid: $v.detailsGrammarUser.grammarQuestionId.$invalid,
              }"
              v-model.number="$v.detailsGrammarUser.grammarQuestionId.$model"
              required
            />
            <div v-if="$v.detailsGrammarUser.grammarQuestionId.$anyDirty && $v.detailsGrammarUser.grammarQuestionId.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsGrammarUser.grammarQuestionId.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
              <small
                class="form-text text-danger"
                v-if="!$v.detailsGrammarUser.grammarQuestionId.numeric"
                v-text="$t('entity.validation.number')"
              >
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label class="form-control-label" v-text="$t('finalProjectApp.detailsGrammarUser.status')" for="details-grammar-user-status"
              >Status</label
            >
            <input
              type="number"
              class="form-control"
              name="status"
              id="details-grammar-user-status"
              data-cy="status"
              :class="{ valid: !$v.detailsGrammarUser.status.$invalid, invalid: $v.detailsGrammarUser.status.$invalid }"
              v-model.number="$v.detailsGrammarUser.status.$model"
              required
            />
            <div v-if="$v.detailsGrammarUser.status.$anyDirty && $v.detailsGrammarUser.status.$invalid">
              <small class="form-text text-danger" v-if="!$v.detailsGrammarUser.status.required" v-text="$t('entity.validation.required')">
                This field is required.
              </small>
              <small class="form-text text-danger" v-if="!$v.detailsGrammarUser.status.numeric" v-text="$t('entity.validation.number')">
                This field should be a number.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsGrammarUser.createdAt')"
              for="details-grammar-user-createdAt"
              >Created At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="details-grammar-user-createdAt"
                  v-model="$v.detailsGrammarUser.createdAt.$model"
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
                id="details-grammar-user-createdAt"
                data-cy="createdAt"
                type="text"
                class="form-control"
                name="createdAt"
                :class="{ valid: !$v.detailsGrammarUser.createdAt.$invalid, invalid: $v.detailsGrammarUser.createdAt.$invalid }"
                v-model="$v.detailsGrammarUser.createdAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.detailsGrammarUser.createdAt.$anyDirty && $v.detailsGrammarUser.createdAt.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsGrammarUser.createdAt.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsGrammarUser.updatedAt')"
              for="details-grammar-user-updatedAt"
              >Updated At</label
            >
            <b-input-group class="mb-3">
              <b-input-group-prepend>
                <b-form-datepicker
                  aria-controls="details-grammar-user-updatedAt"
                  v-model="$v.detailsGrammarUser.updatedAt.$model"
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
                id="details-grammar-user-updatedAt"
                data-cy="updatedAt"
                type="text"
                class="form-control"
                name="updatedAt"
                :class="{ valid: !$v.detailsGrammarUser.updatedAt.$invalid, invalid: $v.detailsGrammarUser.updatedAt.$invalid }"
                v-model="$v.detailsGrammarUser.updatedAt.$model"
                required
              />
            </b-input-group>
            <div v-if="$v.detailsGrammarUser.updatedAt.$anyDirty && $v.detailsGrammarUser.updatedAt.$invalid">
              <small
                class="form-text text-danger"
                v-if="!$v.detailsGrammarUser.updatedAt.required"
                v-text="$t('entity.validation.required')"
              >
                This field is required.
              </small>
            </div>
          </div>
          <div class="form-group">
            <label
              class="form-control-label"
              v-text="$t('finalProjectApp.detailsGrammarUser.grammarUser')"
              for="details-grammar-user-grammarUser"
              >Grammar User</label
            >
            <select
              class="form-control"
              id="details-grammar-user-grammarUser"
              data-cy="grammarUser"
              name="grammarUser"
              v-model="detailsGrammarUser.grammarUser"
            >
              <option v-bind:value="null"></option>
              <option
                v-bind:value="
                  detailsGrammarUser.grammarUser && grammarUserOption.id === detailsGrammarUser.grammarUser.id
                    ? detailsGrammarUser.grammarUser
                    : grammarUserOption
                "
                v-for="grammarUserOption in grammarUsers"
                :key="grammarUserOption.id"
              >
                {{ grammarUserOption.id }}
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
            :disabled="$v.detailsGrammarUser.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.save')">Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./details-grammar-user-update.component.ts"></script>
