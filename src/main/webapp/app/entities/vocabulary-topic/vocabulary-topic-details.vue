<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="vocabularyTopic">
        <h2 class="jh-entity-heading" data-cy="vocabularyTopicDetailsHeading">
          <span v-text="$t('finalProjectApp.vocabularyTopic.detail.title')">VocabularyTopic</span> {{ vocabularyTopic.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.nameTopic')">Name Topic</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.nameTopic }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.description')">Description</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.view')">View</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.view }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.test')">Test</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.test }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.level')">Level</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.level }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.vocabularyTopic.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ vocabularyTopic.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="vocabularyTopic.id"
          :to="{ name: 'VocabularyTopicEdit', params: { vocabularyTopicId: vocabularyTopic.id } }"
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

<script lang="ts" src="./vocabulary-topic-details.component.ts"></script>
