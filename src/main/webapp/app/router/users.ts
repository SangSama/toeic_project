import { Authority } from '@/shared/security/authority';

const JhiVocabularyComponent = () => import('@/users/vocabulary/vocabulary.vue');
const JhiVocaListComponent = () => import('@/users/vocabulary/vocaList.vue');
const JhiVocaTopicComponent = () => import('@/users/vocabulary/vocaTopic.vue');
const JhiVocaQuizComponent = () => import('@/users/vocabulary/vocaQuiz.vue');
const JhiVocaResultComponent = () => import('@/users/vocabulary/vocaResult.vue');

const JhiGrammarComponent = () => import('@/users/grammar/grammar.vue');
const JhiGramTopicComponent = () => import('@/users/grammar/gramTopic.vue');
const JhiGramQuizComponent = () => import('@/users/grammar/gramQuiz.vue');
const JhiGramResultComponent = () => import('@/users/grammar/gramResult.vue');

const JhiToeicComponent = () => import('@/users/toeic/toeic.vue');
const JhiToeicStartComponent = () => import('@/users/toeic/toeicStart.vue');
const JhiToeicExamComponent = () => import('@/users/toeic/toeicExam.vue');
const JhiToeicResultComponent = () => import('@/users/toeic/toeicResult.vue');

export default [
  {
    path: '/users/vocabulary',
    name: 'JhiVocabularyComponent',
    component: JhiVocabularyComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/vocabulary/vocaList',
    name: 'JhiVocaListComponent',
    component: JhiVocaListComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/vocabulary/vocaList/vocaTopic',
    name: 'JhiVocaTopicComponent',
    component: JhiVocaTopicComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/vocabulary/vocaList/vocaTopic/vocaQuiz',
    name: 'JhiVocaQuizComponent',
    component: JhiVocaQuizComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/vocabulary/vocaList/vocaTopic/vocaQuiz/vocaResult',
    name: 'JhiVocaResultComponent',
    component: JhiVocaResultComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/grammar',
    name: 'JhiGrammarComponent',
    component: JhiGrammarComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/grammar/gramTopic',
    name: 'JhiGramTopicComponent',
    component: JhiGramTopicComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/grammar/gramTopic/gramQuiz',
    name: 'JhiGramQuizComponent',
    component: JhiGramQuizComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/grammar/gramTopic/gramQuiz/gramResult',
    name: 'JhiGramResultComponent',
    component: JhiGramResultComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/toeic',
    name: 'JhiToeicComponent',
    component: JhiToeicComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/toeic/toeicStart',
    name: 'JhiToeicStartComponent',
    component: JhiToeicStartComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/toeic/toeicStart/toeicExam',
    name: 'JhiToeicExamComponent',
    component: JhiToeicExamComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/toeic/toeicStart/toeicExam/toeicResult',
    name: 'JhiToeicResultComponent',
    component: JhiToeicResultComponent,
    meta: { authorities: [Authority.USER] },
  },
];
