/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ExtendQuestionDetailComponent from '@/entities/extend-question/extend-question-details.vue';
import ExtendQuestionClass from '@/entities/extend-question/extend-question-details.component';
import ExtendQuestionService from '@/entities/extend-question/extend-question.service';
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
  describe('ExtendQuestion Management Detail Component', () => {
    let wrapper: Wrapper<ExtendQuestionClass>;
    let comp: ExtendQuestionClass;
    let extendQuestionServiceStub: SinonStubbedInstance<ExtendQuestionService>;

    beforeEach(() => {
      extendQuestionServiceStub = sinon.createStubInstance<ExtendQuestionService>(ExtendQuestionService);

      wrapper = shallowMount<ExtendQuestionClass>(ExtendQuestionDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { extendQuestionService: () => extendQuestionServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundExtendQuestion = { id: 123 };
        extendQuestionServiceStub.find.resolves(foundExtendQuestion);

        // WHEN
        comp.retrieveExtendQuestion(123);
        await comp.$nextTick();

        // THEN
        expect(comp.extendQuestion).toBe(foundExtendQuestion);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundExtendQuestion = { id: 123 };
        extendQuestionServiceStub.find.resolves(foundExtendQuestion);

        // WHEN
        comp.beforeRouteEnter({ params: { extendQuestionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.extendQuestion).toBe(foundExtendQuestion);
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
