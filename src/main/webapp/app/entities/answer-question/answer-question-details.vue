<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="answerQuestion">
        <h2 class="jh-entity-heading" data-cy="answerQuestionDetailsHeading">
          <span v-text="$t('finalProjectApp.answerQuestion.detail.title')">AnswerQuestion</span> {{ answerQuestion.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.answerQuestion.answerA')">Answer A</span>
          </dt>
          <dd>
            <span>{{ answerQuestion.answerA }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.answerQuestion.answerB')">Answer B</span>
          </dt>
          <dd>
            <span>{{ answerQuestion.answerB }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.answerQuestion.answerC')">Answer C</span>
          </dt>
          <dd>
            <span>{{ answerQuestion.answerC }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.answerQuestion.answerD')">Answer D</span>
          </dt>
          <dd>
            <span>{{ answerQuestion.answerD }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.answerQuestion.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ answerQuestion.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.answerQuestion.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ answerQuestion.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="answerQuestion.id"
          :to="{ name: 'AnswerQuestionEdit', params: { answerQuestionId: answerQuestion.id } }"
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

<script lang="ts" src="./answer-question-details.component.ts"></script>
