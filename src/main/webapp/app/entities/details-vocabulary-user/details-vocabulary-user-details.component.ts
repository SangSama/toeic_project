import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDetailsVocabularyUser } from '@/shared/model/details-vocabulary-user.model';
import DetailsVocabularyUserService from './details-vocabulary-user.service';
import AlertService from '@/shared/alert/alert.service';

@Component
export default class DetailsVocabularyUserDetails extends Vue {
  @Inject('detailsVocabularyUserService') private detailsVocabularyUserService: () => DetailsVocabularyUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public detailsVocabularyUser: IDetailsVocabularyUser = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.detailsVocabularyUserId) {
        vm.retrieveDetailsVocabularyUser(to.params.detailsVocabularyUserId);
      }
    });
  }

  public retrieveDetailsVocabularyUser(detailsVocabularyUserId) {
    this.detailsVocabularyUserService()
      .find(detailsVocabularyUserId)
      .then(res => {
        this.detailsVocabularyUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
