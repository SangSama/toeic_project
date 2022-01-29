import { Component, Vue, Inject } from 'vue-property-decorator';

import { IGrammarUser } from '@/shared/model/grammar-user.model';
import GrammarUserService from './grammar-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class GrammarUserDetails extends Vue {
  @Inject('grammarUserService') private grammarUserService: () => GrammarUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarUser: IGrammarUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarUserId) {
        vm.retrieveGrammarUser(to.params.grammarUserId);
      }
    });
  }

  public retrieveGrammarUser(grammarUserId) {
    this.grammarUserService()
      .find(grammarUserId)
      .then(res => {
        this.grammarUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
