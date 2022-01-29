import { Component, Vue, Inject } from 'vue-property-decorator';

import { IGrammarAnswer } from '@/shared/model/grammar-answer.model';
import GrammarAnswerService from './grammar-answer.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class GrammarAnswerDetails extends Vue {
  @Inject('grammarAnswerService') private grammarAnswerService: () => GrammarAnswerService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarAnswer: IGrammarAnswer = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarAnswerId) {
        vm.retrieveGrammarAnswer(to.params.grammarAnswerId);
      }
    });
  }

  public retrieveGrammarAnswer(grammarAnswerId) {
    this.grammarAnswerService()
      .find(grammarAnswerId)
      .then(res => {
        this.grammarAnswer = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
