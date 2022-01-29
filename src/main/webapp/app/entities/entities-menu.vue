<template>
  <div>
    <b-dropdown-item to="/answer-question">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.answerQuestion')">Answer Question</span>
    </b-dropdown-item>
    <b-dropdown-item to="/parts">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.parts')">Parts</span>
    </b-dropdown-item>
    <b-dropdown-item to="/toeic-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.toeicUser')">Toeic User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/details-vocabulary-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.detailsVocabularyUser')">Details Vocabulary User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/extend-question">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.extendQuestion')">Extend Question</span>
    </b-dropdown-item>
    <b-dropdown-item to="/grammar-question">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.grammarQuestion')">Grammar Question</span>
    </b-dropdown-item>
    <b-dropdown-item to="/details-grammar-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.detailsGrammarUser')">Details Grammar User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/vocabulary-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.vocabularyUser')">Vocabulary User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/grammar-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.grammarUser')">Grammar User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/question-part">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.questionPart')">Question Part</span>
    </b-dropdown-item>
    <b-dropdown-item to="/vocabulary">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.vocabulary')">Vocabulary</span>
    </b-dropdown-item>
    <b-dropdown-item to="/grammar-topic">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.grammarTopic')">Grammar Topic</span>
    </b-dropdown-item>
    <b-dropdown-item to="/vocabulary-topic">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.vocabularyTopic')">Vocabulary Topic</span>
    </b-dropdown-item>
    <b-dropdown-item to="/qn-a">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.qnA')">Qn A</span>
    </b-dropdown-item>
    <b-dropdown-item to="/details-toeic-user">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.detailsToeicUser')">Details Toeic User</span>
    </b-dropdown-item>
    <b-dropdown-item to="/grammar-answer">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.grammarAnswer')">Grammar Answer</span>
    </b-dropdown-item>
    <b-dropdown-item to="/toeics">
      <font-awesome-icon icon="asterisk" />
      <span v-text="$t('global.menu.entities.toeics')">Toeics</span>
    </b-dropdown-item>
    <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
  </div>
</template>

<script lang="ts" src="./entities-menu.component.ts"></script>
