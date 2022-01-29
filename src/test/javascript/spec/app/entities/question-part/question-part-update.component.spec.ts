/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import QuestionPartUpdateComponent from '@/entities/question-part/question-part-update.vue';
import QuestionPartClass from '@/entities/question-part/question-part-update.component';
import QuestionPartService from '@/entities/question-part/question-part.service';

import AnswerQuestionService from '@/entities/answer-question/answer-question.service';

import PartsService from '@/entities/parts/parts.service';

import ExtendQuestionService from '@/entities/extend-question/extend-question.service';
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
  describe('QuestionPart Management Update Component', () => {
    let wrapper: Wrapper<QuestionPartClass>;
    let comp: QuestionPartClass;
    let questionPartServiceStub: SinonStubbedInstance<QuestionPartService>;

    beforeEach(() => {
      questionPartServiceStub = sinon.createStubInstance<QuestionPartService>(QuestionPartService);

      wrapper = shallowMount<QuestionPartClass>(QuestionPartUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          questionPartService: () => questionPartServiceStub,
          alertService: () => new AlertService(),

          answerQuestionService: () =>
            sinon.createStubInstance<AnswerQuestionService>(AnswerQuestionService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          partsService: () =>
            sinon.createStubInstance<PartsService>(PartsService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          extendQuestionService: () =>
            sinon.createStubInstance<ExtendQuestionService>(ExtendQuestionService, {
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
        comp.questionPart = entity;
        questionPartServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(questionPartServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.questionPart = entity;
        questionPartServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(questionPartServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundQuestionPart = { id: 123 };
        questionPartServiceStub.find.resolves(foundQuestionPart);
        questionPartServiceStub.retrieve.resolves([foundQuestionPart]);

        // WHEN
        comp.beforeRouteEnter({ params: { questionPartId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.questionPart).toBe(foundQuestionPart);
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
