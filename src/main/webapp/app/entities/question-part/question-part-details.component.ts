import { Component, Vue, Inject } from 'vue-property-decorator';

import { IQuestionPart } from '@/shared/model/question-part.model';
import QuestionPartService from './question-part.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class QuestionPartDetails extends Vue {
  @Inject('questionPartService') private questionPartService: () => QuestionPartService;
  @Inject('alertService') private alertService: () => AlertService;

  public questionPart: IQuestionPart = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.questionPartId) {
        vm.retrieveQuestionPart(to.params.questionPartId);
      }
    });
  }

  public retrieveQuestionPart(questionPartId) {
    this.questionPartService()
      .find(questionPartId)
      .then(res => {
        this.questionPart = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
