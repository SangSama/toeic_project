import { Component, Vue, Inject } from 'vue-property-decorator';

import { IToeics } from '@/shared/model/toeics.model';
import ToeicsService from './toeics.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ToeicsDetails extends Vue {
  @Inject('toeicsService') private toeicsService: () => ToeicsService;
  @Inject('alertService') private alertService: () => AlertService;

  public toeics: IToeics = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.toeicsId) {
        vm.retrieveToeics(to.params.toeicsId);
      }
    });
  }

  public retrieveToeics(toeicsId) {
    this.toeicsService()
      .find(toeicsId)
      .then(res => {
        this.toeics = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
