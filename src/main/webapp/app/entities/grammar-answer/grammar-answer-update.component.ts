import { Component, Vue, Inject } from 'vue-property-decorator';

import { required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IGrammarAnswer, GrammarAnswer } from '@/shared/model/grammar-answer.model';
import GrammarAnswerService from './grammar-answer.service';

const validations: any = {
  grammarAnswer: {
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
export default class GrammarAnswerUpdate extends Vue {
  @Inject('grammarAnswerService') private grammarAnswerService: () => GrammarAnswerService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarAnswer: IGrammarAnswer = new GrammarAnswer();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarAnswerId) {
        vm.retrieveGrammarAnswer(to.params.grammarAnswerId);
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
    if (this.grammarAnswer.id) {
      this.grammarAnswerService()
        .update(this.grammarAnswer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarAnswer.updated', { param: param.id });
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
      this.grammarAnswerService()
        .create(this.grammarAnswer)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarAnswer.created', { param: param.id });
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

  public retrieveGrammarAnswer(grammarAnswerId): void {
    this.grammarAnswerService()
      .find(grammarAnswerId)
      .then(res => {
        this.grammarAnswer = res;
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
