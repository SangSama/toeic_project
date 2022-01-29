import { Component, Vue, Inject } from 'vue-property-decorator';

import { IAnswerQuestion } from '@/shared/model/answer-question.model';
import AnswerQuestionService from './answer-question.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class AnswerQuestionDetails extends Vue {
  @Inject('answerQuestionService') private answerQuestionService: () => AnswerQuestionService;
  @Inject('alertService') private alertService: () => AlertService;

  public answerQuestion: IAnswerQuestion = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.answerQuestionId) {
        vm.retrieveAnswerQuestion(to.params.answerQuestionId);
      }
    });
  }

  public retrieveAnswerQuestion(answerQuestionId) {
    this.answerQuestionService()
      .find(answerQuestionId)
      .then(res => {
        this.answerQuestion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
