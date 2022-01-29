/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import GrammarQuestionDetailComponent from '@/entities/grammar-question/grammar-question-details.vue';
import GrammarQuestionClass from '@/entities/grammar-question/grammar-question-details.component';
import GrammarQuestionService from '@/entities/grammar-question/grammar-question.service';
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
  describe('GrammarQuestion Management Detail Component', () => {
    let wrapper: Wrapper<GrammarQuestionClass>;
    let comp: GrammarQuestionClass;
    let grammarQuestionServiceStub: SinonStubbedInstance<GrammarQuestionService>;

    beforeEach(() => {
      grammarQuestionServiceStub = sinon.createStubInstance<GrammarQuestionService>(GrammarQuestionService);

      wrapper = shallowMount<GrammarQuestionClass>(GrammarQuestionDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { grammarQuestionService: () => grammarQuestionServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGrammarQuestion = { id: 123 };
        grammarQuestionServiceStub.find.resolves(foundGrammarQuestion);

        // WHEN
        comp.retrieveGrammarQuestion(123);
        await comp.$nextTick();

        // THEN
        expect(comp.grammarQuestion).toBe(foundGrammarQuestion);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarQuestion = { id: 123 };
        grammarQuestionServiceStub.find.resolves(foundGrammarQuestion);

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
