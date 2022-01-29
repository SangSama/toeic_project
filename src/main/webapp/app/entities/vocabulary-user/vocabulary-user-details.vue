<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="vocabularyUser">
        <h2 class="jh-entity-heading" data-cy="vocabularyUserDetailsHeading">
          <span v-text="$t('finalProjectApp.vocabularyUser.detail.title')">VocabularyUser</span> {{ vocabularyUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyUser.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ vocabularyUser.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyUser.score')">Score</span>
          </dt>
          <dd>
            <span>{{ vocabularyUser.score }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyUser.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ vocabularyUser.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyUser.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ vocabularyUser.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyUser.vocabularyTopic')">Vocabulary Topic</span>
          </dt>
          <dd>
            <div v-if="vocabularyUser.vocabularyTopic">
              <router-link :to="{ name: 'VocabularyTopicView', params: { vocabularyTopicId: vocabularyUser.vocabularyTopic.id } }">{{
                vocabularyUser.vocabularyTopic.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="vocabularyUser.id"
          :to="{ name: 'VocabularyUserEdit', params: { vocabularyUserId: vocabularyUser.id } }"
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

<script lang="ts" src="./vocabulary-user-details.component.ts"></script>
