import { Component, Vue, Inject } from 'vue-property-decorator';

import { IParts } from '@/shared/model/parts.model';
import PartsService from './parts.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class PartsDetails extends Vue {
  @Inject('partsService') private partsService: () => PartsService;
  @Inject('alertService') private alertService: () => AlertService;

  public parts: IParts = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.partsId) {
        vm.retrieveParts(to.params.partsId);
      }
    });
  }

  public retrieveParts(partsId) {
    this.partsService()
      .find(partsId)
      .then(res => {
        this.parts = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
