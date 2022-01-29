import { Component, Inject } from 'vue-property-decorator';

import { mixins } from 'vue-class-component';
import JhiDataUtils from '@/shared/data/data-utils.service';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IExtendQuestion, ExtendQuestion } from '@/shared/model/extend-question.model';
import ExtendQuestionService from './extend-question.service';

const validations: any = {
  extendQuestion: {
    paragraph: {},
    image: {},
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
export default class ExtendQuestionUpdate extends mixins(JhiDataUtils) {
  @Inject('extendQuestionService') private extendQuestionService: () => ExtendQuestionService;
  @Inject('alertService') private alertService: () => AlertService;

  public extendQuestion: IExtendQuestion = new ExtendQuestion();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.extendQuestionId) {
        vm.retrieveExtendQuestion(to.params.extendQuestionId);
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
    if (this.extendQuestion.id) {
      this.extendQuestionService()
        .update(this.extendQuestion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.extendQuestion.updated', { param: param.id });
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
      this.extendQuestionService()
        .create(this.extendQuestion)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.extendQuestion.created', { param: param.id });
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

  public retrieveExtendQuestion(extendQuestionId): void {
    this.extendQuestionService()
      .find(extendQuestionId)
      .then(res => {
        this.extendQuestion = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public clearInputImage(field, fieldContentType, idInput): void {
    if (this.extendQuestion && field && fieldContentType) {
      if (Object.prototype.hasOwnProperty.call(this.extendQuestion, field)) {
        this.extendQuestion[field] = null;
      }
      if (Object.prototype.hasOwnProperty.call(this.extendQuestion, fieldContentType)) {
        this.extendQuestion[fieldContentType] = null;
      }
      if (idInput) {
        (<any>this).$refs[idInput] = null;
      }
    }
  }

  public initRelationships(): void {}
}
