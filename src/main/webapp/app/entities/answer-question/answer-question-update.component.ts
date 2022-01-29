import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IAnswerQuestion, AnswerQuestion } from '@/shared/model/answer-question.model';
import AnswerQuestionService from './answer-question.service';

const validations: any = {
  answerQuestion: {
    answerA: {
      required,
    },
    answerB: {
      required,
    },
    answerC: {
      required,
    },
    answerD: {
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
export default class AnswerQuestionUpdate extends Vue {
  @Inject('answerQuestionService') private answerQuestionService: () => AnswerQuestionService;
  @Inject('alertService') private alertService: () => AlertService;

  public answerQuestion: IAnswerQuestion = new AnswerQuestion();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.answerQuestionId) {
        vm.retrieveAnswerQuestion(to.params.answerQuestionId);
      }
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
    if (this.answerQuestion.id) {
      this.answerQuestionService()
        .update(this.answerQuestion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.answerQuestion.updated', { param: param.id });
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
      this.answerQuestionService()
        .create(this.answerQuestion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.answerQuestion.created', { param: param.id });
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

  public retrieveAnswerQuestion(answerQuestionId): void {
    this.answerQuestionService()
      .find(answerQuestionId)
      .then(res => {
        this.answerQuestion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
