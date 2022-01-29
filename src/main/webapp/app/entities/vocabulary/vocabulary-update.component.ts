import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import VocabularyTopicService from '@/entities/vocabulary-topic/vocabulary-topic.service';
import { IVocabularyTopic } from '@/shared/model/vocabulary-topic.model';

import { IVocabulary, Vocabulary } from '@/shared/model/vocabulary.model';
import VocabularyService from './vocabulary.service';

const validations: any = {
  vocabulary: {
    word: {
      required,
    },
    mean: {
      required,
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
export default class VocabularyUpdate extends Vue {
  @Inject('vocabularyService') private vocabularyService: () => VocabularyService;
  @Inject('alertService') private alertService: () => AlertService;

  public vocabulary: IVocabulary = new Vocabulary();

  @Inject('vocabularyTopicService') private vocabularyTopicService: () => VocabularyTopicService;

  public vocabularyTopics: IVocabularyTopic[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.vocabularyId) {
        vm.retrieveVocabulary(to.params.vocabularyId);
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
    if (this.vocabulary.id) {
      this.vocabularyService()
        .update(this.vocabulary)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.vocabulary.updated', { param: param.id });
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
      this.vocabularyService()
        .create(this.vocabulary)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.vocabulary.created', { param: param.id });
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

  public retrieveVocabulary(vocabularyId): void {
    this.vocabularyService()
      .find(vocabularyId)
      .then(res => {
        this.vocabulary = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.vocabularyTopicService()
      .retrieve()
      .then(res => {
        this.vocabularyTopics = res.data;
      });
  }
}
