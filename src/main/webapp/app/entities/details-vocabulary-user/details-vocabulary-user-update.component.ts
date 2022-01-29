import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import VocabularyUserService from '@/entities/vocabulary-user/vocabulary-user.service';
import { IVocabularyUser } from '@/shared/model/vocabulary-user.model';

import { IDetailsVocabularyUser, DetailsVocabularyUser } from '@/shared/model/details-vocabulary-user.model';
import DetailsVocabularyUserService from './details-vocabulary-user.service';

const validations: any = {
  detailsVocabularyUser: {
    vocabularyId: {
      required,
      numeric,
    },
    status: {
      required,
      numeric,
    },
    createdAt: {
      required,
    },
    updatedAt: {
      required,
    },
  },
};

@Component({
  validations,
})
export default class DetailsVocabularyUserUpdate extends Vue {
  @Inject('detailsVocabularyUserService') private detailsVocabularyUserService: () => DetailsVocabularyUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public detailsVocabularyUser: IDetailsVocabularyUser = new DetailsVocabularyUser();

  @Inject('vocabularyUserService') private vocabularyUserService: () => VocabularyUserService;

  public vocabularyUsers: IVocabularyUser[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.detailsVocabularyUserId) {
        vm.retrieveDetailsVocabularyUser(to.params.detailsVocabularyUserId);
      }
      vm.initRelationships();
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.detailsVocabularyUser.id) {
      this.detailsVocabularyUserService()
        .update(this.detailsVocabularyUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.detailsVocabularyUser.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    } else {
      this.detailsVocabularyUserService()
        .create(this.detailsVocabularyUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.detailsVocabularyUser.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        })
        .catch(error => {
          this.isSaving = false;
          this.alertService().showHttpError(this, error.response);
        });
    }
  }

  public retrieveDetailsVocabularyUser(detailsVocabularyUserId): void {
    this.detailsVocabularyUserService()
      .find(detailsVocabularyUserId)
      .then(res => {
        this.detailsVocabularyUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.vocabularyUserService()
      .retrieve()
      .then(res => {
        this.vocabularyUsers = res.data;
      });
  }
}
