import { Component, Vue, Inject } from 'vue-property-decorator';

import { required, maxLength, numeric } from 'vuelidate/lib/validators';

import AlertService from '@/shared/alert/alert.service';

import GrammarUserService from '@/entities/grammar-user/grammar-user.service';
import { IGrammarUser } from '@/shared/model/grammar-user.model';

import GrammarQuestionService from '@/entities/grammar-question/grammar-question.service';
import { IGrammarQuestion } from '@/shared/model/grammar-question.model';

import { IGrammarTopic, GrammarTopic } from '@/shared/model/grammar-topic.model';
import GrammarTopicService from './grammar-topic.service';

const validations: any = {
  grammarTopic: {
    nameTopic: {
      required,
      maxLength: maxLength(100),
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
    level: {},
    filePractice: {},
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
export default class GrammarTopicUpdate extends Vue {
  @Inject('grammarTopicService') private grammarTopicService: () => GrammarTopicService;
  @Inject('alertService') private alertService: () => AlertService;

  public grammarTopic: IGrammarTopic = new GrammarTopic();

  @Inject('grammarUserService') private grammarUserService: () => GrammarUserService;

  public grammarUsers: IGrammarUser[] = [];

  @Inject('grammarQuestionService') private grammarQuestionService: () => GrammarQuestionService;

  public grammarQuestions: IGrammarQuestion[] = [];
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.grammarTopicId) {
        vm.retrieveGrammarTopic(to.params.grammarTopicId);
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
    if (this.grammarTopic.id) {
      this.grammarTopicService()
        .update(this.grammarTopic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarTopic.updated', { param: param.id });
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
      this.grammarTopicService()
        .create(this.grammarTopic)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('finalProjectApp.grammarTopic.created', { param: param.id });
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

  public retrieveGrammarTopic(grammarTopicId): void {
    this.grammarTopicService()
      .find(grammarTopicId)
      .then(res => {
        this.grammarTopic = res;
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
    this.grammarQuestionService()
      .retrieve()
      .then(res => {
        this.grammarQuestions = res.data;
      });
  }
}
