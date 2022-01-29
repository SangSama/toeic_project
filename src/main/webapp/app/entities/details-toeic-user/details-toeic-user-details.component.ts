import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDetailsToeicUser } from '@/shared/model/details-toeic-user.model';
import DetailsToeicUserService from './details-toeic-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DetailsToeicUserDetails extends Vue {
  @Inject('detailsToeicUserService') private detailsToeicUserService: () => DetailsToeicUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public detailsToeicUser: IDetailsToeicUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.detailsToeicUserId) {
        vm.retrieveDetailsToeicUser(to.params.detailsToeicUserId);
      }
    });
  }

  public retrieveDetailsToeicUser(detailsToeicUserId) {
    this.detailsToeicUserService()
      .find(detailsToeicUserId)
      .then(res => {
        this.detailsToeicUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
