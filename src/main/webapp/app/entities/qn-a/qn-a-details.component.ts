import { Component, Vue, Inject } from 'vue-property-decorator';

import { IQnA } from '@/shared/model/qn-a.model';
import QnAService from './qn-a.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class QnADetails extends Vue {
  @Inject('qnAService') private qnAService: () => QnAService;
  @Inject('alertService') private alertService: () => AlertService;

  public qnA: IQnA = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.qnAId) {
        vm.retrieveQnA(to.params.qnAId);
      }
    });
  }

  public retrieveQnA(qnAId) {
    this.qnAService()
      .find(qnAId)
      .then(res => {
        this.qnA = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
