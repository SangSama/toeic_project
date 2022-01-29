import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import GrammarUserService from '@/entities/grammar-user/grammar-user.service';
import { IGrammarUser } from '@/shared/model/grammar-user.model';

import { IDetailsGrammarUser, DetailsGrammarUser } from '@/shared/model/details-grammar-user.model';
import DetailsGrammarUserService from './details-grammar-user.service';

const validations: any = {
  detailsGrammarUser: {
    grammarQuestionId: {
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
export default class DetailsGrammarUserUpdate extends Vue {
  @Inject('detailsGrammarUserService') private detailsGrammarUserService: () => DetailsGrammarUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public detailsGrammarUser: IDetailsGrammarUser = new DetailsGrammarUser();

  @Inject('grammarUserService') private grammarUserService: () => GrammarUserService;

  public grammarUsers: IGrammarUser[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.detailsGrammarUserId) {
        vm.retrieveDetailsGrammarUser(to.params.detailsGrammarUserId);
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
    if (this.detailsGrammarUser.id) {
      this.detailsGrammarUserService()
        .update(this.detailsGrammarUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.detailsGrammarUser.updated', { param: param.id });
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
      this.detailsGrammarUserService()
        .create(this.detailsGrammarUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.detailsGrammarUser.created', { param: param.id });
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

  public retrieveDetailsGrammarUser(detailsGrammarUserId): void {
    this.detailsGrammarUserService()
      .find(detailsGrammarUserId)
      .then(res => {
        this.detailsGrammarUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.grammarUserService()
      .retrieve()
      .then(res => {
        this.grammarUsers = res.data;
      });
  }
}
