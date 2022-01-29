<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="questionPart">
        <h2 class="jh-entity-heading" data-cy="questionPartDetailsHeading">
          <span v-text="$t('finalProjectApp.questionPart.detail.title')">QuestionPart</span> {{ questionPart.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.question')">Question</span>
          </dt>
          <dd>
            <span>{{ questionPart.question }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.correct')">Correct</span>
          </dt>
          <dd>
            <span>{{ questionPart.correct }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ questionPart.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ questionPart.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.answerQuestion')">Answer Question</span>
          </dt>
          <dd>
            <div v-if="questionPart.answerQuestion">
              <router-link :to="{ name: 'AnswerQuestionView', params: { answerQuestionId: questionPart.answerQuestion.id } }">{{
                questionPart.answerQuestion.id
              }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.parts')">Parts</span>
          </dt>
          <dd>
            <div v-if="questionPart.parts">
              <router-link :to="{ name: 'PartsView', params: { partsId: questionPart.parts.id } }">{{ questionPart.parts.id }}</router-link>
            </div>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.questionPart.extendQuestion')">Extend Question</span>
          </dt>
          <dd>
            <div v-if="questionPart.extendQuestion">
              <router-link :to="{ name: 'ExtendQuestionView', params: { extendQuestionId: questionPart.extendQuestion.id } }">{{
                questionPart.extendQuestion.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="questionPart.id"
          :to="{ name: 'QuestionPartEdit', params: { questionPartId: questionPart.id } }"
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

<script lang="ts" src="./question-part-details.component.ts"></script>
