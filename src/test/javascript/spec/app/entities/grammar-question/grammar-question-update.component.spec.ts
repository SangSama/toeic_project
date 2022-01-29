/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import GrammarQuestionUpdateComponent from '@/entities/grammar-question/grammar-question-update.vue';
import GrammarQuestionClass from '@/entities/grammar-question/grammar-question-update.component';
import GrammarQuestionService from '@/entities/grammar-question/grammar-question.service';

import GrammarAnswerService from '@/entities/grammar-answer/grammar-answer.service';

import GrammarTopicService from '@/entities/grammar-topic/grammar-topic.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('GrammarQuestion Management Update Component', () => {
    let wrapper: Wrapper<GrammarQuestionClass>;
    let comp: GrammarQuestionClass;
    let grammarQuestionServiceStub: SinonStubbedInstance<GrammarQuestionService>;

    beforeEach(() => {
      grammarQuestionServiceStub = sinon.createStubInstance<GrammarQuestionService>(GrammarQuestionService);

      wrapper = shallowMount<GrammarQuestionClass>(GrammarQuestionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          grammarQuestionService: () => grammarQuestionServiceStub,
          alertService: () => new AlertService(),

          grammarAnswerService: () =>
            sinon.createStubInstance<GrammarAnswerService>(GrammarAnswerService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          grammarTopicService: () =>
            sinon.createStubInstance<GrammarTopicService>(GrammarTopicService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.grammarQuestion = entity;
        grammarQuestionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarQuestionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.grammarQuestion = entity;
        grammarQuestionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarQuestionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarQuestion = { id: 123 };
        grammarQuestionServiceStub.find.resolves(foundGrammarQuestion);
        grammarQuestionServiceStub.retrieve.resolves([foundGrammarQuestion]);

        // WHEN
        comp.beforeRouteEnter({ params: { grammarQuestionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.grammarQuestion).toBe(foundGrammarQuestion);
      });
    });

    describe('Previous state', () => {
      it('Should go previous state', async () => {
        comp.previousState();
        await comp.$nextTick();

        expect(comp.$router.currentRoute.fullPath).toContain('/');
      });
    });
  });
});
