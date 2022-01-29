import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength, numeric } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import VocabularyUserService from '@/entities/vocabulary-user/vocabulary-user.service';
import { IVocabularyUser } from '@/shared/model/vocabulary-user.model';

import VocabularyService from '@/entities/vocabulary/vocabulary.service';
import { IVocabulary } from '@/shared/model/vocabulary.model';

import { IVocabularyTopic, VocabularyTopic } from '@/shared/model/vocabulary-topic.model';
import VocabularyTopicService from './vocabulary-topic.service';

const validations: any = {
  vocabularyTopic: {
    nameTopic: {
      required,
      maxLength: maxLength(100),
    },
    description: {},
    view: {
      required,
      numeric,
    },
    test: {
      required,
      numeric,
    },
    level: {},
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
export default class VocabularyTopicUpdate extends Vue {
  @Inject('vocabularyTopicService') private vocabularyTopicService: () => VocabularyTopicService;
  @Inject('alertService') private alertService: () => AlertService;

  public vocabularyTopic: IVocabularyTopic = new VocabularyTopic();

  @Inject('vocabularyUserService') private vocabularyUserService: () => VocabularyUserService;

  public vocabularyUsers: IVocabularyUser[] = [];

  @Inject('vocabularyService') private vocabularyService: () => VocabularyService;

  public vocabularies: IVocabulary[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vocabularyTopicId) {
        vm.retrieveVocabularyTopic(to.params.vocabularyTopicId);
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
    if (this.vocabularyTopic.id) {
      this.vocabularyTopicService()
        .update(this.vocabularyTopic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.vocabularyTopic.updated', { param: param.id });
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
      this.vocabularyTopicService()
        .create(this.vocabularyTopic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.vocabularyTopic.created', { param: param.id });
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

  public retrieveVocabularyTopic(vocabularyTopicId): void {
    this.vocabularyTopicService()
      .find(vocabularyTopicId)
      .then(res => {
        this.vocabularyTopic = res;
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
    this.vocabularyService()
      .retrieve()
      .then(res => {
        this.vocabularies = res.data;
      });
  }
}
