<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="grammarAnswer">
        <h2 class="jh-entity-heading" data-cy="grammarAnswerDetailsHeading">
          <span v-text="$t('finalProjectApp.grammarAnswer.detail.title')">GrammarAnswer</span> {{ grammarAnswer.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.grammarAnswer.answerA')">Answer A</span>
          </dt>
          <dd>
            <span>{{ grammarAnswer.answerA }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarAnswer.answerB')">Answer B</span>
          </dt>
          <dd>
            <span>{{ grammarAnswer.answerB }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarAnswer.answerC')">Answer C</span>
          </dt>
          <dd>
            <span>{{ grammarAnswer.answerC }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarAnswer.answerD')">Answer D</span>
          </dt>
          <dd>
            <span>{{ grammarAnswer.answerD }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarAnswer.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ grammarAnswer.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.grammarAnswer.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ grammarAnswer.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="grammarAnswer.id"
          :to="{ name: 'GrammarAnswerEdit', params: { grammarAnswerId: grammarAnswer.id } }"
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

<script lang="ts" src="./grammar-answer-details.component.ts"></script>
