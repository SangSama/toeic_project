<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="detailsVocabularyUser">
        <h2 class="jh-entity-heading" data-cy="detailsVocabularyUserDetailsHeading">
          <span v-text="$t('finalProjectApp.detailsVocabularyUser.detail.title')">DetailsVocabularyUser</span>
          {{ detailsVocabularyUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.detailsVocabularyUser.vocabularyId')">Vocabulary Id</span>
          </dt>
          <dd>
            <span>{{ detailsVocabularyUser.vocabularyId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsVocabularyUser.status')">Status</span>
          </dt>
          <dd>
            <span>{{ detailsVocabularyUser.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsVocabularyUser.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ detailsVocabularyUser.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsVocabularyUser.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ detailsVocabularyUser.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsVocabularyUser.vocabularyUser')">Vocabulary User</span>
          </dt>
          <dd>
            <div v-if="detailsVocabularyUser.vocabularyUser">
              <router-link :to="{ name: 'VocabularyUserView', params: { vocabularyUserId: detailsVocabularyUser.vocabularyUser.id } }">{{
                detailsVocabularyUser.vocabularyUser.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="detailsVocabularyUser.id"
          :to="{ name: 'DetailsVocabularyUserEdit', params: { detailsVocabularyUserId: detailsVocabularyUser.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./details-vocabulary-user-details.component.ts"></script>
