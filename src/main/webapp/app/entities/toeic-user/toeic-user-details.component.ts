import { Component, Vue, Inject } from 'vue-property-decorator';

import { IToeicUser } from '@/shared/model/toeic-user.model';
import ToeicUserService from './toeic-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class ToeicUserDetails extends Vue {
  @Inject('toeicUserService') private toeicUserService: () => ToeicUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public toeicUser: IToeicUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.toeicUserId) {
        vm.retrieveToeicUser(to.params.toeicUserId);
      }
    });
  }

  public retrieveToeicUser(toeicUserId) {
    this.toeicUserService()
      .find(toeicUserId)
      .then(res => {
        this.toeicUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
