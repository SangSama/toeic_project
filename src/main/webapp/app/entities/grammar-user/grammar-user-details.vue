<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="grammarUser">
        <h2 class="jh-entity-heading" data-cy="grammarUserDetailsHeading">
          <span v-text="$t('finalProjectApp.grammarUser.detail.title')">GrammarUser</span> {{ grammarUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.grammarUser.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ grammarUser.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarUser.score')">Score</span>
          </dt>
          <dd>
            <span>{{ grammarUser.score }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarUser.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ grammarUser.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarUser.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ grammarUser.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarUser.grammarTopic')">Grammar Topic</span>
          </dt>
          <dd>
            <div v-if="grammarUser.grammarTopic">
              <router-link :to="{ name: 'GrammarTopicView', params: { grammarTopicId: grammarUser.grammarTopic.id } }">{{
                grammarUser.grammarTopic.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="grammarUser.id"
          :to="{ name: 'GrammarUserEdit', params: { grammarUserId: grammarUser.id } }"
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

<script lang="ts" src="./grammar-user-details.component.ts"></script>
