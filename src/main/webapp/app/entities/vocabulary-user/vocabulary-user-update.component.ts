import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import DetailsVocabularyUserService from '@/entities/details-vocabulary-user/details-vocabulary-user.service';
import { IDetailsVocabularyUser } from '@/shared/model/details-vocabulary-user.model';

import VocabularyTopicService from '@/entities/vocabulary-topic/vocabulary-topic.service';
import { IVocabularyTopic } from '@/shared/model/vocabulary-topic.model';

import { IVocabularyUser, VocabularyUser } from '@/shared/model/vocabulary-user.model';
import VocabularyUserService from './vocabulary-user.service';

const validations: any = {
  vocabularyUser: {
    userId: {
      required,
      numeric,
    },
    score: {},
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
export default class VocabularyUserUpdate extends Vue {
  @Inject('vocabularyUserService') private vocabularyUserService: () => VocabularyUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public vocabularyUser: IVocabularyUser = new VocabularyUser();

  @Inject('detailsVocabularyUserService') private detailsVocabularyUserService: () => DetailsVocabularyUserService;

  public detailsVocabularyUsers: IDetailsVocabularyUser[] = [];

  @Inject('vocabularyTopicService') private vocabularyTopicService: () => VocabularyTopicService;

  public vocabularyTopics: IVocabularyTopic[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vocabularyUserId) {
        vm.retrieveVocabularyUser(to.params.vocabularyUserId);
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
    if (this.vocabularyUser.id) {
      this.vocabularyUserService()
        .update(this.vocabularyUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.vocabularyUser.updated', { param: param.id });
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
      this.vocabularyUserService()
        .create(this.vocabularyUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.vocabularyUser.created', { param: param.id });
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

  public retrieveVocabularyUser(vocabularyUserId): void {
    this.vocabularyUserService()
      .find(vocabularyUserId)
      .then(res => {
        this.vocabularyUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.detailsVocabularyUserService()
      .retrieve()
      .then(res => {
        this.detailsVocabularyUsers = res.data;
      });
    this.vocabularyTopicService()
      .retrieve()
      .then(res => {
        this.vocabularyTopics = res.data;
      });
  }
}
