import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import ToeicUserService from '@/entities/toeic-user/toeic-user.service';
import { IToeicUser } from '@/shared/model/toeic-user.model';

import { IDetailsToeicUser, DetailsToeicUser } from '@/shared/model/details-toeic-user.model';
import DetailsToeicUserService from './details-toeic-user.service';

const validations: any = {
  detailsToeicUser: {
    toeicId: {
      required,
      numeric,
    },
    partId: {
      required,
      numeric,
    },
    questionId: {
      required,
      numeric,
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
export default class DetailsToeicUserUpdate extends Vue {
  @Inject('detailsToeicUserService') private detailsToeicUserService: () => DetailsToeicUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public detailsToeicUser: IDetailsToeicUser = new DetailsToeicUser();

  @Inject('toeicUserService') private toeicUserService: () => ToeicUserService;

  public toeicUsers: IToeicUser[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.detailsToeicUserId) {
        vm.retrieveDetailsToeicUser(to.params.detailsToeicUserId);
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
    if (this.detailsToeicUser.id) {
      this.detailsToeicUserService()
        .update(this.detailsToeicUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.detailsToeicUser.updated', { param: param.id });
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
      this.detailsToeicUserService()
        .create(this.detailsToeicUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.detailsToeicUser.created', { param: param.id });
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

  public retrieveDetailsToeicUser(detailsToeicUserId): void {
    this.detailsToeicUserService()
      .find(detailsToeicUserId)
      .then(res => {
        this.detailsToeicUser = res;
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
  }
}
