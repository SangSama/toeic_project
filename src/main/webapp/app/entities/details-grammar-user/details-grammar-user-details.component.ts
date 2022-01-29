import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDetailsGrammarUser } from '@/shared/model/details-grammar-user.model';
import DetailsGrammarUserService from './details-grammar-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DetailsGrammarUserDetails extends Vue {
  @Inject('detailsGrammarUserService') private detailsGrammarUserService: () => DetailsGrammarUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public detailsGrammarUser: IDetailsGrammarUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.detailsGrammarUserId) {
        vm.retrieveDetailsGrammarUser(to.params.detailsGrammarUserId);
      }
    });
  }

  public retrieveDetailsGrammarUser(detailsGrammarUserId) {
    this.detailsGrammarUserService()
      .find(detailsGrammarUserId)
      .then(res => {
        this.detailsGrammarUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
