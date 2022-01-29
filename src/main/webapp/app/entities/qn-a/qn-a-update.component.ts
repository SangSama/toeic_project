import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required, maxLength } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import { IQnA, QnA } from '@/shared/model/qn-a.model';
import QnAService from './qn-a.service';

const validations: any = {
  qnA: {
    userId: {
      required,
      numeric,
    },
    email: {
      required,
      maxLength: maxLength(50),
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
export default class QnAUpdate extends Vue {
  @Inject('qnAService') private qnAService: () => QnAService;
  @Inject('alertService') private alertService: () => AlertService;

  public qnA: IQnA = new QnA();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.qnAId) {
        vm.retrieveQnA(to.params.qnAId);
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
    if (this.qnA.id) {
      this.qnAService()
        .update(this.qnA)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.qnA.updated', { param: param.id });
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
      this.qnAService()
        .create(this.qnA)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.qnA.created', { param: param.id });
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

  public retrieveQnA(qnAId): void {
    this.qnAService()
      .find(qnAId)
      .then(res => {
        this.qnA = res;
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
