<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="vocabulary">
        <h2 class="jh-entity-heading" data-cy="vocabularyDetailsHeading">
          <span v-text="$t('finalProjectApp.vocabulary.detail.title')">Vocabulary</span> {{ vocabulary.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.vocabulary.word')">Word</span>
          </dt>
          <dd>
            <span>{{ vocabulary.word }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabulary.mean')">Mean</span>
          </dt>
          <dd>
            <span>{{ vocabulary.mean }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabulary.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ vocabulary.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabulary.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ vocabulary.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabulary.vocabularyTopic')">Vocabulary Topic</span>
          </dt>
          <dd>
            <div v-if="vocabulary.vocabularyTopic">
              <router-link :to="{ name: 'VocabularyTopicView', params: { vocabularyTopicId: vocabulary.vocabularyTopic.id } }">{{
                vocabulary.vocabularyTopic.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="vocabulary.id"
          :to="{ name: 'VocabularyEdit', params: { vocabularyId: vocabulary.id } }"
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

<script lang="ts" src="./vocabulary-details.component.ts"></script>
