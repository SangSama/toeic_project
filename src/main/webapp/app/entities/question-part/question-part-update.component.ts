import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import AnswerQuestionService from '@/entities/answer-question/answer-question.service';
import { IAnswerQuestion } from '@/shared/model/answer-question.model';

import PartsService from '@/entities/parts/parts.service';
import { IParts } from '@/shared/model/parts.model';

import ExtendQuestionService from '@/entities/extend-question/extend-question.service';
import { IExtendQuestion } from '@/shared/model/extend-question.model';

import { IQuestionPart, QuestionPart } from '@/shared/model/question-part.model';
import QuestionPartService from './question-part.service';

const validations: any = {
  questionPart: {
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
export default class QuestionPartUpdate extends Vue {
  @Inject('questionPartService') private questionPartService: () => QuestionPartService;
  @Inject('alertService') private alertService: () => AlertService;

  public questionPart: IQuestionPart = new QuestionPart();

  @Inject('answerQuestionService') private answerQuestionService: () => AnswerQuestionService;

  public answerQuestions: IAnswerQuestion[] = [];

  @Inject('partsService') private partsService: () => PartsService;

  public parts: IParts[] = [];

  @Inject('extendQuestionService') private extendQuestionService: () => ExtendQuestionService;

  public extendQuestions: IExtendQuestion[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.questionPartId) {
        vm.retrieveQuestionPart(to.params.questionPartId);
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
    if (this.questionPart.id) {
      this.questionPartService()
        .update(this.questionPart)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.questionPart.updated', { param: param.id });
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
      this.questionPartService()
        .create(this.questionPart)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.questionPart.created', { param: param.id });
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

  public retrieveQuestionPart(questionPartId): void {
    this.questionPartService()
      .find(questionPartId)
      .then(res => {
        this.questionPart = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.answerQuestionService()
      .retrieve()
      .then(res => {
        this.answerQuestions = res.data;
      });
    this.partsService()
      .retrieve()
      .then(res => {
        this.parts = res.data;
      });
    this.extendQuestionService()
      .retrieve()
      .then(res => {
        this.extendQuestions = res.data;
      });
  }
}
