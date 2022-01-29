import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import GrammarAnswerService from '@/entities/grammar-answer/grammar-answer.service';
import { IGrammarAnswer } from '@/shared/model/grammar-answer.model';

import GrammarTopicService from '@/entities/grammar-topic/grammar-topic.service';
import { IGrammarTopic } from '@/shared/model/grammar-topic.model';

import { IGrammarQuestion, GrammarQuestion } from '@/shared/model/grammar-question.model';
import GrammarQuestionService from './grammar-question.service';

const validations: any = {
  grammarQuestion: {
    question: {
      required,
    },
    correct: {
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
export default class GrammarQuestionUpdate extends Vue {
  @Inject('grammarQuestionService') private grammarQuestionService: () => GrammarQuestionService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarQuestion: IGrammarQuestion = new GrammarQuestion();

  @Inject('grammarAnswerService') private grammarAnswerService: () => GrammarAnswerService;

  public grammarAnswers: IGrammarAnswer[] = [];

  @Inject('grammarTopicService') private grammarTopicService: () => GrammarTopicService;

  public grammarTopics: IGrammarTopic[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarQuestionId) {
        vm.retrieveGrammarQuestion(to.params.grammarQuestionId);
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
    if (this.grammarQuestion.id) {
      this.grammarQuestionService()
        .update(this.grammarQuestion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarQuestion.updated', { param: param.id });
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
      this.grammarQuestionService()
        .create(this.grammarQuestion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarQuestion.created', { param: param.id });
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

  public retrieveGrammarQuestion(grammarQuestionId): void {
    this.grammarQuestionService()
      .find(grammarQuestionId)
      .then(res => {
        this.grammarQuestion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.grammarAnswerService()
      .retrieve()
      .then(res => {
        this.grammarAnswers = res.data;
      });
    this.grammarTopicService()
      .retrieve()
      .then(res => {
        this.grammarTopics = res.data;
      });
  }
}
