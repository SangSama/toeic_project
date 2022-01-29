import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { IExtendQuestion } from '@/shared/model/extend-question.model';
import ExtendQuestionService from './extend-question.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ExtendQuestionDetails extends mixins(JhiDataUtils) {
  @Inject('extendQuestionService') private extendQuestionService: () => ExtendQuestionService;
  @Inject('alertService') private alertService: () => AlertService;

  public extendQuestion: IExtendQuestion = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.extendQuestionId) {
        vm.retrieveExtendQuestion(to.params.extendQuestionId);
      }
    });
  }

  public retrieveExtendQuestion(extendQuestionId) {
    this.extendQuestionService()
      .find(extendQuestionId)
      .then(res => {
        this.extendQuestion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
