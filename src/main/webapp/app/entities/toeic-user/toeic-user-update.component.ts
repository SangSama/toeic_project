import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import DetailsToeicUserService from '@/entities/details-toeic-user/details-toeic-user.service';
import { IDetailsToeicUser } from '@/shared/model/details-toeic-user.model';

import ToeicsService from '@/entities/toeics/toeics.service';
import { IToeics } from '@/shared/model/toeics.model';

import { IToeicUser, ToeicUser } from '@/shared/model/toeic-user.model';
import ToeicUserService from './toeic-user.service';

const validations: any = {
  toeicUser: {
    userId: {
      required,
      numeric,
    },
    score: {},
    reading: {
      required,
    },
    listening: {
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
export default class ToeicUserUpdate extends Vue {
  @Inject('toeicUserService') private toeicUserService: () => ToeicUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public toeicUser: IToeicUser = new ToeicUser();

  @Inject('detailsToeicUserService') private detailsToeicUserService: () => DetailsToeicUserService;

  public detailsToeicUsers: IDetailsToeicUser[] = [];

  @Inject('toeicsService') private toeicsService: () => ToeicsService;

  public toeics: IToeics[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.toeicUserId) {
        vm.retrieveToeicUser(to.params.toeicUserId);
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
    if (this.toeicUser.id) {
      this.toeicUserService()
        .update(this.toeicUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.toeicUser.updated', { param: param.id });
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
      this.toeicUserService()
        .create(this.toeicUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.toeicUser.created', { param: param.id });
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

  public retrieveToeicUser(toeicUserId): void {
    this.toeicUserService()
      .find(toeicUserId)
      .then(res => {
        this.toeicUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.detailsToeicUserService()
      .retrieve()
      .then(res => {
        this.detailsToeicUsers = res.data;
      });
    this.toeicsService()
      .retrieve()
      .then(res => {
        this.toeics = res.data;
      });
  }
}
