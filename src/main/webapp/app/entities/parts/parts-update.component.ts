import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ToeicsService from '@/entities/toeics/toeics.service';
import { IToeics } from '@/shared/model/toeics.model';

import { IParts, Parts } from '@/shared/model/parts.model';
import PartsService from './parts.service';

const validations: any = {
  parts: {
    part: {
      required,
      numeric,
    },
    description: {
      required,
    },
    example: {
      required,
    },
    fileLC: {
      required,
    },
    view: {
      required,
      numeric,
    },
    test: {
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
export default class PartsUpdate extends Vue {
  @Inject('partsService') private partsService: () => PartsService;
  @Inject('alertService') private alertService: () => AlertService;

  public parts: IParts = new Parts();

  @Inject('toeicsService') private toeicsService: () => ToeicsService;

  public toeics: IToeics[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.partsId) {
        vm.retrieveParts(to.params.partsId);
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
    if (this.parts.id) {
      this.partsService()
        .update(this.parts)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.parts.updated', { param: param.id });
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
      this.partsService()
        .create(this.parts)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.parts.created', { param: param.id });
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

  public retrieveParts(partsId): void {
    this.partsService()
      .find(partsId)
      .then(res => {
        this.parts = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.toeicsService()
      .retrieve()
      .then(res => {
        this.toeics = res.data;
      });
  }
}
