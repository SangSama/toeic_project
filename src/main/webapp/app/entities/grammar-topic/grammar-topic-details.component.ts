import { Component, Vue, Inject } from 'vue-property-decorator';

import { IGrammarTopic } from '@/shared/model/grammar-topic.model';
import GrammarTopicService from './grammar-topic.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class GrammarTopicDetails extends Vue {
  @Inject('grammarTopicService') private grammarTopicService: () => GrammarTopicService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarTopic: IGrammarTopic = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarTopicId) {
        vm.retrieveGrammarTopic(to.params.grammarTopicId);
      }
    });
  }

  public retrieveGrammarTopic(grammarTopicId) {
    this.grammarTopicService()
      .find(grammarTopicId)
      .then(res => {
        this.grammarTopic = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
