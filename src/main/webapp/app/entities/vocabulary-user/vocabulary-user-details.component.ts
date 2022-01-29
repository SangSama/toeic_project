import { Component, Vue, Inject } from 'vue-property-decorator';

import { IVocabularyUser } from '@/shared/model/vocabulary-user.model';
import VocabularyUserService from './vocabulary-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class VocabularyUserDetails extends Vue {
  @Inject('vocabularyUserService') private vocabularyUserService: () => VocabularyUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public vocabularyUser: IVocabularyUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vocabularyUserId) {
        vm.retrieveVocabularyUser(to.params.vocabularyUserId);
      }
    });
  }

  public retrieveVocabularyUser(vocabularyUserId) {
    this.vocabularyUserService()
      .find(vocabularyUserId)
      .then(res => {
        this.vocabularyUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
