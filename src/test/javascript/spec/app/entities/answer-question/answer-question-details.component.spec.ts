/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import AnswerQuestionDetailComponent from '@/entities/answer-question/answer-question-details.vue';
import AnswerQuestionClass from '@/entities/answer-question/answer-question-details.component';
import AnswerQuestionService from '@/entities/answer-question/answer-question.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('AnswerQuestion Management Detail Component', () => {
    let wrapper: Wrapper<AnswerQuestionClass>;
    let comp: AnswerQuestionClass;
    let answerQuestionServiceStub: SinonStubbedInstance<AnswerQuestionService>;

    beforeEach(() => {
      answerQuestionServiceStub = sinon.createStubInstance<AnswerQuestionService>(AnswerQuestionService);

      wrapper = shallowMount<AnswerQuestionClass>(AnswerQuestionDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { answerQuestionService: () => answerQuestionServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundAnswerQuestion = { id: 123 };
        answerQuestionServiceStub.find.resolves(foundAnswerQuestion);

        // WHEN
        comp.retrieveAnswerQuestion(123);
        await comp.$nextTick();

        // THEN
        expect(comp.answerQuestion).toBe(foundAnswerQuestion);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundAnswerQuestion = { id: 123 };
        answerQuestionServiceStub.find.resolves(foundAnswerQuestion);

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
