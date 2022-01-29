import { Authority } from '@/shared/security/authority';
/* tslint:disable */
// prettier-ignore
const Entities = () => import('@/entities/entities.vue');

// prettier-ignore
const AnswerQuestion = () => import('@/entities/answer-question/answer-question.vue');
// prettier-ignore
const AnswerQuestionUpdate = () => import('@/entities/answer-question/answer-question-update.vue');
// prettier-ignore
const AnswerQuestionDetails = () => import('@/entities/answer-question/answer-question-details.vue');
// prettier-ignore
const Parts = () => import('@/entities/parts/parts.vue');
// prettier-ignore
const PartsUpdate = () => import('@/entities/parts/parts-update.vue');
// prettier-ignore
const PartsDetails = () => import('@/entities/parts/parts-details.vue');
// prettier-ignore
const ToeicUser = () => import('@/entities/toeic-user/toeic-user.vue');
// prettier-ignore
const ToeicUserUpdate = () => import('@/entities/toeic-user/toeic-user-update.vue');
// prettier-ignore
const ToeicUserDetails = () => import('@/entities/toeic-user/toeic-user-details.vue');
// prettier-ignore
const DetailsVocabularyUser = () => import('@/entities/details-vocabulary-user/details-vocabulary-user.vue');
// prettier-ignore
const DetailsVocabularyUserUpdate = () => import('@/entities/details-vocabulary-user/details-vocabulary-user-update.vue');
// prettier-ignore
const DetailsVocabularyUserDetails = () => import('@/entities/details-vocabulary-user/details-vocabulary-user-details.vue');
// prettier-ignore
const ExtendQuestion = () => import('@/entities/extend-question/extend-question.vue');
// prettier-ignore
const ExtendQuestionUpdate = () => import('@/entities/extend-question/extend-question-update.vue');
// prettier-ignore
const ExtendQuestionDetails = () => import('@/entities/extend-question/extend-question-details.vue');
// prettier-ignore
const GrammarQuestion = () => import('@/entities/grammar-question/grammar-question.vue');
// prettier-ignore
const GrammarQuestionUpdate = () => import('@/entities/grammar-question/grammar-question-update.vue');
// prettier-ignore
const GrammarQuestionDetails = () => import('@/entities/grammar-question/grammar-question-details.vue');
// prettier-ignore
const DetailsGrammarUser = () => import('@/entities/details-grammar-user/details-grammar-user.vue');
// prettier-ignore
const DetailsGrammarUserUpdate = () => import('@/entities/details-grammar-user/details-grammar-user-update.vue');
// prettier-ignore
const DetailsGrammarUserDetails = () => import('@/entities/details-grammar-user/details-grammar-user-details.vue');
// prettier-ignore
const VocabularyUser = () => import('@/entities/vocabulary-user/vocabulary-user.vue');
// prettier-ignore
const VocabularyUserUpdate = () => import('@/entities/vocabulary-user/vocabulary-user-update.vue');
// prettier-ignore
const VocabularyUserDetails = () => import('@/entities/vocabulary-user/vocabulary-user-details.vue');
// prettier-ignore
const GrammarUser = () => import('@/entities/grammar-user/grammar-user.vue');
// prettier-ignore
const GrammarUserUpdate = () => import('@/entities/grammar-user/grammar-user-update.vue');
// prettier-ignore
const GrammarUserDetails = () => import('@/entities/grammar-user/grammar-user-details.vue');
// prettier-ignore
const QuestionPart = () => import('@/entities/question-part/question-part.vue');
// prettier-ignore
const QuestionPartUpdate = () => import('@/entities/question-part/question-part-update.vue');
// prettier-ignore
const QuestionPartDetails = () => import('@/entities/question-part/question-part-details.vue');
// prettier-ignore
const Vocabulary = () => import('@/entities/vocabulary/vocabulary.vue');
// prettier-ignore
const VocabularyUpdate = () => import('@/entities/vocabulary/vocabulary-update.vue');
// prettier-ignore
const VocabularyDetails = () => import('@/entities/vocabulary/vocabulary-details.vue');
// prettier-ignore
const GrammarTopic = () => import('@/entities/grammar-topic/grammar-topic.vue');
// prettier-ignore
const GrammarTopicUpdate = () => import('@/entities/grammar-topic/grammar-topic-update.vue');
// prettier-ignore
const GrammarTopicDetails = () => import('@/entities/grammar-topic/grammar-topic-details.vue');
// prettier-ignore
const VocabularyTopic = () => import('@/entities/vocabulary-topic/vocabulary-topic.vue');
// prettier-ignore
const VocabularyTopicUpdate = () => import('@/entities/vocabulary-topic/vocabulary-topic-update.vue');
// prettier-ignore
const VocabularyTopicDetails = () => import('@/entities/vocabulary-topic/vocabulary-topic-details.vue');
// prettier-ignore
const QnA = () => import('@/entities/qn-a/qn-a.vue');
// prettier-ignore
const QnAUpdate = () => import('@/entities/qn-a/qn-a-update.vue');
// prettier-ignore
const QnADetails = () => import('@/entities/qn-a/qn-a-details.vue');
// prettier-ignore
const DetailsToeicUser = () => import('@/entities/details-toeic-user/details-toeic-user.vue');
// prettier-ignore
const DetailsToeicUserUpdate = () => import('@/entities/details-toeic-user/details-toeic-user-update.vue');
// prettier-ignore
const DetailsToeicUserDetails = () => import('@/entities/details-toeic-user/details-toeic-user-details.vue');
// prettier-ignore
const GrammarAnswer = () => import('@/entities/grammar-answer/grammar-answer.vue');
// prettier-ignore
const GrammarAnswerUpdate = () => import('@/entities/grammar-answer/grammar-answer-update.vue');
// prettier-ignore
const GrammarAnswerDetails = () => import('@/entities/grammar-answer/grammar-answer-details.vue');
// prettier-ignore
const Toeics = () => import('@/entities/toeics/toeics.vue');
// prettier-ignore
const ToeicsUpdate = () => import('@/entities/toeics/toeics-update.vue');
// prettier-ignore
const ToeicsDetails = () => import('@/entities/toeics/toeics-details.vue');
// jhipster-needle-add-entity-to-router-import - JHipster will import entities to the router here

export default {
  path: '/',
  component: Entities,
  children: [
    {
      path: 'answer-question',
      name: 'AnswerQuestion',
      component: AnswerQuestion,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'answer-question/new',
      name: 'AnswerQuestionCreate',
      component: AnswerQuestionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'answer-question/:answerQuestionId/edit',
      name: 'AnswerQuestionEdit',
      component: AnswerQuestionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'answer-question/:answerQuestionId/view',
      name: 'AnswerQuestionView',
      component: AnswerQuestionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'parts',
      name: 'Parts',
      component: Parts,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'parts/new',
      name: 'PartsCreate',
      component: PartsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'parts/:partsId/edit',
      name: 'PartsEdit',
      component: PartsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'parts/:partsId/view',
      name: 'PartsView',
      component: PartsDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeic-user',
      name: 'ToeicUser',
      component: ToeicUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeic-user/new',
      name: 'ToeicUserCreate',
      component: ToeicUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeic-user/:toeicUserId/edit',
      name: 'ToeicUserEdit',
      component: ToeicUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeic-user/:toeicUserId/view',
      name: 'ToeicUserView',
      component: ToeicUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-vocabulary-user',
      name: 'DetailsVocabularyUser',
      component: DetailsVocabularyUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-vocabulary-user/new',
      name: 'DetailsVocabularyUserCreate',
      component: DetailsVocabularyUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-vocabulary-user/:detailsVocabularyUserId/edit',
      name: 'DetailsVocabularyUserEdit',
      component: DetailsVocabularyUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-vocabulary-user/:detailsVocabularyUserId/view',
      name: 'DetailsVocabularyUserView',
      component: DetailsVocabularyUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'extend-question',
      name: 'ExtendQuestion',
      component: ExtendQuestion,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'extend-question/new',
      name: 'ExtendQuestionCreate',
      component: ExtendQuestionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'extend-question/:extendQuestionId/edit',
      name: 'ExtendQuestionEdit',
      component: ExtendQuestionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'extend-question/:extendQuestionId/view',
      name: 'ExtendQuestionView',
      component: ExtendQuestionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-question',
      name: 'GrammarQuestion',
      component: GrammarQuestion,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-question/new',
      name: 'GrammarQuestionCreate',
      component: GrammarQuestionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-question/:grammarQuestionId/edit',
      name: 'GrammarQuestionEdit',
      component: GrammarQuestionUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-question/:grammarQuestionId/view',
      name: 'GrammarQuestionView',
      component: GrammarQuestionDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-grammar-user',
      name: 'DetailsGrammarUser',
      component: DetailsGrammarUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-grammar-user/new',
      name: 'DetailsGrammarUserCreate',
      component: DetailsGrammarUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-grammar-user/:detailsGrammarUserId/edit',
      name: 'DetailsGrammarUserEdit',
      component: DetailsGrammarUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-grammar-user/:detailsGrammarUserId/view',
      name: 'DetailsGrammarUserView',
      component: DetailsGrammarUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-user',
      name: 'VocabularyUser',
      component: VocabularyUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-user/new',
      name: 'VocabularyUserCreate',
      component: VocabularyUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-user/:vocabularyUserId/edit',
      name: 'VocabularyUserEdit',
      component: VocabularyUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-user/:vocabularyUserId/view',
      name: 'VocabularyUserView',
      component: VocabularyUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-user',
      name: 'GrammarUser',
      component: GrammarUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-user/new',
      name: 'GrammarUserCreate',
      component: GrammarUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-user/:grammarUserId/edit',
      name: 'GrammarUserEdit',
      component: GrammarUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-user/:grammarUserId/view',
      name: 'GrammarUserView',
      component: GrammarUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'question-part',
      name: 'QuestionPart',
      component: QuestionPart,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'question-part/new',
      name: 'QuestionPartCreate',
      component: QuestionPartUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'question-part/:questionPartId/edit',
      name: 'QuestionPartEdit',
      component: QuestionPartUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'question-part/:questionPartId/view',
      name: 'QuestionPartView',
      component: QuestionPartDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary',
      name: 'Vocabulary',
      component: Vocabulary,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary/new',
      name: 'VocabularyCreate',
      component: VocabularyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary/:vocabularyId/edit',
      name: 'VocabularyEdit',
      component: VocabularyUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary/:vocabularyId/view',
      name: 'VocabularyView',
      component: VocabularyDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-topic',
      name: 'GrammarTopic',
      component: GrammarTopic,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-topic/new',
      name: 'GrammarTopicCreate',
      component: GrammarTopicUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-topic/:grammarTopicId/edit',
      name: 'GrammarTopicEdit',
      component: GrammarTopicUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-topic/:grammarTopicId/view',
      name: 'GrammarTopicView',
      component: GrammarTopicDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-topic',
      name: 'VocabularyTopic',
      component: VocabularyTopic,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-topic/new',
      name: 'VocabularyTopicCreate',
      component: VocabularyTopicUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-topic/:vocabularyTopicId/edit',
      name: 'VocabularyTopicEdit',
      component: VocabularyTopicUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'vocabulary-topic/:vocabularyTopicId/view',
      name: 'VocabularyTopicView',
      component: VocabularyTopicDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qn-a',
      name: 'QnA',
      component: QnA,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qn-a/new',
      name: 'QnACreate',
      component: QnAUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qn-a/:qnAId/edit',
      name: 'QnAEdit',
      component: QnAUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'qn-a/:qnAId/view',
      name: 'QnAView',
      component: QnADetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-toeic-user',
      name: 'DetailsToeicUser',
      component: DetailsToeicUser,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-toeic-user/new',
      name: 'DetailsToeicUserCreate',
      component: DetailsToeicUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-toeic-user/:detailsToeicUserId/edit',
      name: 'DetailsToeicUserEdit',
      component: DetailsToeicUserUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'details-toeic-user/:detailsToeicUserId/view',
      name: 'DetailsToeicUserView',
      component: DetailsToeicUserDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-answer',
      name: 'GrammarAnswer',
      component: GrammarAnswer,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-answer/new',
      name: 'GrammarAnswerCreate',
      component: GrammarAnswerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-answer/:grammarAnswerId/edit',
      name: 'GrammarAnswerEdit',
      component: GrammarAnswerUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'grammar-answer/:grammarAnswerId/view',
      name: 'GrammarAnswerView',
      component: GrammarAnswerDetails,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeics',
      name: 'Toeics',
      component: Toeics,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeics/new',
      name: 'ToeicsCreate',
      component: ToeicsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeics/:toeicsId/edit',
      name: 'ToeicsEdit',
      component: ToeicsUpdate,
      meta: { authorities: [Authority.USER] },
    },
    {
      path: 'toeics/:toeicsId/view',
      name: 'ToeicsView',
      component: ToeicsDetails,
      meta: { authorities: [Authority.USER] },
    },
    // jhipster-needle-add-entity-to-router - JHipster will add entities to the router here
  ],
};
