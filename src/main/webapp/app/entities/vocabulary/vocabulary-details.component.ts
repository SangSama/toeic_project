import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVocabulary } from '@/shared/model/vocabulary.model';
import VocabularyService from './vocabulary.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class VocabularyDetails extends Vue {
  @Inject('vocabularyService') private vocabularyService: () => VocabularyService;
  @Inject('alertService') private alertService: () => AlertService;

  public vocabulary: IVocabulary = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vocabularyId) {
        vm.retrieveVocabulary(to.params.vocabularyId);
      }
    });
  }

  public retrieveVocabulary(vocabularyId) {
    this.vocabularyService()
      .find(vocabularyId)
      .then(res => {
        this.vocabulary = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
