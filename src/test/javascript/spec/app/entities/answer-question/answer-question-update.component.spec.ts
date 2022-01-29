/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import AnswerQuestionUpdateComponent from '@/entities/answer-question/answer-question-update.vue';
import AnswerQuestionClass from '@/entities/answer-question/answer-question-update.component';
import AnswerQuestionService from '@/entities/answer-question/answer-question.service';

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
  describe('AnswerQuestion Management Update Component', () => {
    let wrapper: Wrapper<AnswerQuestionClass>;
    let comp: AnswerQuestionClass;
    let answerQuestionServiceStub: SinonStubbedInstance<AnswerQuestionService>;

    beforeEach(() => {
      answerQuestionServiceStub = sinon.createStubInstance<AnswerQuestionService>(AnswerQuestionService);

      wrapper = shallowMount<AnswerQuestionClass>(AnswerQuestionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          answerQuestionService: () => answerQuestionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.answerQuestion = entity;
        answerQuestionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(answerQuestionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.answerQuestion = entity;
        answerQuestionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(answerQuestionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAnswerQuestion = { id: 123 };
        answerQuestionServiceStub.find.resolves(foundAnswerQuestion);
        answerQuestionServiceStub.retrieve.resolves([foundAnswerQuestion]);

        // WHEN
        comp.beforeRouteEnter({ params: { answerQuestionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.answerQuestion).toBe(foundAnswerQuestion);
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
