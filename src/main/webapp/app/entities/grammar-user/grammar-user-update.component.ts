import { Component, Vue, Inject } from 'vue-property-decorator';

import { numeric, required } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import DetailsGrammarUserService from '@/entities/details-grammar-user/details-grammar-user.service';
import { IDetailsGrammarUser } from '@/shared/model/details-grammar-user.model';

import GrammarTopicService from '@/entities/grammar-topic/grammar-topic.service';
import { IGrammarTopic } from '@/shared/model/grammar-topic.model';

import { IGrammarUser, GrammarUser } from '@/shared/model/grammar-user.model';
import GrammarUserService from './grammar-user.service';

const validations: any = {
  grammarUser: {
    userId: {
      required,
      numeric,
    },
    score: {},
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
export default class GrammarUserUpdate extends Vue {
  @Inject('grammarUserService') private grammarUserService: () => GrammarUserService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarUser: IGrammarUser = new GrammarUser();

  @Inject('detailsGrammarUserService') private detailsGrammarUserService: () => DetailsGrammarUserService;

  public detailsGrammarUsers: IDetailsGrammarUser[] = [];

  @Inject('grammarTopicService') private grammarTopicService: () => GrammarTopicService;

  public grammarTopics: IGrammarTopic[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarUserId) {
        vm.retrieveGrammarUser(to.params.grammarUserId);
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
    if (this.grammarUser.id) {
      this.grammarUserService()
        .update(this.grammarUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarUser.updated', { param: param.id });
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
      this.grammarUserService()
        .create(this.grammarUser)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarUser.created', { param: param.id });
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

  public retrieveGrammarUser(grammarUserId): void {
    this.grammarUserService()
      .find(grammarUserId)
      .then(res => {
        this.grammarUser = res;
      })
      .catch(error => {
        this.alertService().showHttpError(this, error.response);
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {
    this.detailsGrammarUserService()
      .retrieve()
      .then(res => {
        this.detailsGrammarUsers = res.data;
      });
    this.grammarTopicService()
      .retrieve()
      .then(res => {
        this.grammarTopics = res.data;
      });
  }
}
