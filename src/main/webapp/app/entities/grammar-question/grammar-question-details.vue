<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="grammarQuestion">
        <h2 class="jh-entity-heading" data-cy="grammarQuestionDetailsHeading">
          <span v-text="$t('finalProjectApp.grammarQuestion.detail.title')">GrammarQuestion</span> {{ grammarQuestion.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.grammarQuestion.question')">Question</span>
          </dt>
          <dd>
            <span>{{ grammarQuestion.question }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarQuestion.correct')">Correct</span>
          </dt>
          <dd>
            <span>{{ grammarQuestion.correct }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarQuestion.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ grammarQuestion.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarQuestion.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ grammarQuestion.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarQuestion.grammarAnswer')">Grammar Answer</span>
          </dt>
          <dd>
            <div v-if="grammarQuestion.grammarAnswer">
              <router-link :to="{ name: 'GrammarAnswerView', params: { grammarAnswerId: grammarQuestion.grammarAnswer.id } }">{{
                grammarQuestion.grammarAnswer.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarQuestion.grammarTopic')">Grammar Topic</span>
          </dt>
          <dd>
            <div v-if="grammarQuestion.grammarTopic">
              <router-link :to="{ name: 'GrammarTopicView', params: { grammarTopicId: grammarQuestion.grammarTopic.id } }">{{
                grammarQuestion.grammarTopic.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="grammarQuestion.id"
          :to="{ name: 'GrammarQuestionEdit', params: { grammarQuestionId: grammarQuestion.id } }"
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

<script lang="ts" src="./grammar-question-details.component.ts"></script>
