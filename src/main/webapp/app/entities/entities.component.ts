import { Component, Provide, Vue } from 'vue-property-decorator';

import UserService from '@/entities/user/user.service';
import AnswerQuestionService from './answer-question/answer-question.service';
import PartsService from './parts/parts.service';
import ToeicUserService from './toeic-user/toeic-user.service';
import DetailsVocabularyUserService from './details-vocabulary-user/details-vocabulary-user.service';
import ExtendQuestionService from './extend-question/extend-question.service';
import GrammarQuestionService from './grammar-question/grammar-question.service';
import DetailsGrammarUserService from './details-grammar-user/details-grammar-user.service';
import VocabularyUserService from './vocabulary-user/vocabulary-user.service';
import GrammarUserService from './grammar-user/grammar-user.service';
import QuestionPartService from './question-part/question-part.service';
import VocabularyService from './vocabulary/vocabulary.service';
import GrammarTopicService from './grammar-topic/grammar-topic.service';
import VocabularyTopicService from './vocabulary-topic/vocabulary-topic.service';
import QnAService from './qn-a/qn-a.service';
import DetailsToeicUserService from './details-toeic-user/details-toeic-user.service';
import GrammarAnswerService from './grammar-answer/grammar-answer.service';
import ToeicsService from './toeics/toeics.service';
// jhipster-needle-add-entity-service-to-entities-component-import - JHipster will import entities services here

@Component
export default class Entities extends Vue {
  @Provide('userService') private userService = () => new UserService();
  @Provide('answerQuestionService') private answerQuestionService = () => new AnswerQuestionService();
  @Provide('partsService') private partsService = () => new PartsService();
  @Provide('toeicUserService') private toeicUserService = () => new ToeicUserService();
  @Provide('detailsVocabularyUserService') private detailsVocabularyUserService = () => new DetailsVocabularyUserService();
  @Provide('extendQuestionService') private extendQuestionService = () => new ExtendQuestionService();
  @Provide('grammarQuestionService') private grammarQuestionService = () => new GrammarQuestionService();
  @Provide('detailsGrammarUserService') private detailsGrammarUserService = () => new DetailsGrammarUserService();
  @Provide('vocabularyUserService') private vocabularyUserService = () => new VocabularyUserService();
  @Provide('grammarUserService') private grammarUserService = () => new GrammarUserService();
  @Provide('questionPartService') private questionPartService = () => new QuestionPartService();
  @Provide('vocabularyService') private vocabularyService = () => new VocabularyService();
  @Provide('grammarTopicService') private grammarTopicService = () => new GrammarTopicService();
  @Provide('vocabularyTopicService') private vocabularyTopicService = () => new VocabularyTopicService();
  @Provide('qnAService') private qnAService = () => new QnAService();
  @Provide('detailsToeicUserService') private detailsToeicUserService = () => new DetailsToeicUserService();
  @Provide('grammarAnswerService') private grammarAnswerService = () => new GrammarAnswerService();
  @Provide('toeicsService') private toeicsService = () => new ToeicsService();
  // jhipster-needle-add-entity-service-to-entities-component - JHipster will import entities services here
}
