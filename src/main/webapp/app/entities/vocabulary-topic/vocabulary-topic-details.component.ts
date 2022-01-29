import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVocabularyTopic } from '@/shared/model/vocabulary-topic.model';
import VocabularyTopicService from './vocabulary-topic.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class VocabularyTopicDetails extends Vue {
  @Inject('vocabularyTopicService') private vocabularyTopicService: () => VocabularyTopicService;
  @Inject('alertService') private alertService: () => AlertService;

  public vocabularyTopic: IVocabularyTopic = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vocabularyTopicId) {
        vm.retrieveVocabularyTopic(to.params.vocabularyTopicId);
      }
    });
  }

  public retrieveVocabularyTopic(vocabularyTopicId) {
    this.vocabularyTopicService()
      .find(vocabularyTopicId)
      .then(res => {
        this.vocabularyTopic = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
