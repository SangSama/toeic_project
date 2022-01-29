/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import QuestionPartDetailComponent from '@/entities/question-part/question-part-details.vue';
import QuestionPartClass from '@/entities/question-part/question-part-details.component';
import QuestionPartService from '@/entities/question-part/question-part.service';
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
  describe('QuestionPart Management Detail Component', () => {
    let wrapper: Wrapper<QuestionPartClass>;
    let comp: QuestionPartClass;
    let questionPartServiceStub: SinonStubbedInstance<QuestionPartService>;

    beforeEach(() => {
      questionPartServiceStub = sinon.createStubInstance<QuestionPartService>(QuestionPartService);

      wrapper = shallowMount<QuestionPartClass>(QuestionPartDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { questionPartService: () => questionPartServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundQuestionPart = { id: 123 };
        questionPartServiceStub.find.resolves(foundQuestionPart);

        // WHEN
        comp.retrieveQuestionPart(123);
        await comp.$nextTick();

        // THEN
        expect(comp.questionPart).toBe(foundQuestionPart);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundQuestionPart = { id: 123 };
        questionPartServiceStub.find.resolves(foundQuestionPart);

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
