import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength, numeric } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ToeicUserService from '@/entities/toeic-user/toeic-user.service';
import { IToeicUser } from '@/shared/model/toeic-user.model';

import PartsService from '@/entities/parts/parts.service';
import { IParts } from '@/shared/model/parts.model';

import { IToeics, Toeics } from '@/shared/model/toeics.model';
import ToeicsService from './toeics.service';

const validations: any = {
  toeics: {
    nameToeic: {
      required,
      maxLength: maxLength(50),
    },
    number: {
      required,
      numeric,
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
    linkDetail: {},
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
export default class ToeicsUpdate extends Vue {
  @Inject('toeicsService') private toeicsService: () => ToeicsService;
  @Inject('alertService') private alertService: () => AlertService;

  public toeics: IToeics = new Toeics();

  @Inject('toeicUserService') private toeicUserService: () => ToeicUserService;

  public toeicUsers: IToeicUser[] = [];

  @Inject('partsService') private partsService: () => PartsService;

  public parts: IParts[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.toeicsId) {
        vm.retrieveToeics(to.params.toeicsId);
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
    if (this.toeics.id) {
      this.toeicsService()
        .update(this.toeics)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.toeics.updated', { param: param.id });
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
      this.toeicsService()
        .create(this.toeics)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.toeics.created', { param: param.id });
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

  public retrieveToeics(toeicsId): void {
    this.toeicsService()
      .find(toeicsId)
      .then(res => {
        this.toeics = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.toeicUserService()
      .retrieve()
      .then(res => {
        this.toeicUsers = res.data;
      });
    this.partsService()
      .retrieve()
      .then(res => {
        this.parts = res.data;
      });
  }
}
