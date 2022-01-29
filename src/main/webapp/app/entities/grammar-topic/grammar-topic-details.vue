<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="grammarTopic">
        <h2 class="jh-entity-heading" data-cy="grammarTopicDetailsHeading">
          <span v-text="$t('finalProjectApp.grammarTopic.detail.title')">GrammarTopic</span> {{ grammarTopic.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.nameTopic')">Name Topic</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.nameTopic }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.description')">Description</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.view')">View</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.view }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.test')">Test</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.test }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.level')">Level</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.level }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.filePractice')">File Practice</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.filePractice }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarTopic.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ grammarTopic.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="grammarTopic.id"
          :to="{ name: 'GrammarTopicEdit', params: { grammarTopicId: grammarTopic.id } }"
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

<script lang="ts" src="./grammar-topic-details.component.ts"></script>
