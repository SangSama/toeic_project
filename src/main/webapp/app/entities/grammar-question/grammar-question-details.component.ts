import { Component, Vue, Inject } from 'vue-property-decorator';

import { IGrammarQuestion } from '@/shared/model/grammar-question.model';
import GrammarQuestionService from './grammar-question.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class GrammarQuestionDetails extends Vue {
  @Inject('grammarQuestionService') private grammarQuestionService: () => GrammarQuestionService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarQuestion: IGrammarQuestion = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarQuestionId) {
        vm.retrieveGrammarQuestion(to.params.grammarQuestionId);
      }
    });
  }

  public retrieveGrammarQuestion(grammarQuestionId) {
    this.grammarQuestionService()
      .find(grammarQuestionId)
      .then(res => {
        this.grammarQuestion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
