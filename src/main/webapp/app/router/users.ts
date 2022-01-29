import { Authority } from '@/shared/security/authority';

const JhiVocabularyComponent = () => import('@/users/vocabulary/vocabulary.vue');
const JhiGrammarComponent = () => import('@/users/grammar/grammar.vue');
const JhiToeicComponent = () => import('@/users/toeic/toeic.vue');

export default [
  {
    path: '/users/vocabulary',
    name: 'JhiVocabularyComponent',
    component: JhiVocabularyComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/grammar',
    name: 'JhiGrammarComponent',
    component: JhiGrammarComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/users/toeic',
    name: 'JhiToeicComponent',
    component: JhiToeicComponent,
    meta: { authorities: [Authority.USER] },
  },
];
