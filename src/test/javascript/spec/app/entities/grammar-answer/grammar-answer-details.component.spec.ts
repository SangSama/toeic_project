/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import GrammarAnswerDetailComponent from '@/entities/grammar-answer/grammar-answer-details.vue';
import GrammarAnswerClass from '@/entities/grammar-answer/grammar-answer-details.component';
import GrammarAnswerService from '@/entities/grammar-answer/grammar-answer.service';
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
  describe('GrammarAnswer Management Detail Component', () => {
    let wrapper: Wrapper<GrammarAnswerClass>;
    let comp: GrammarAnswerClass;
    let grammarAnswerServiceStub: SinonStubbedInstance<GrammarAnswerService>;

    beforeEach(() => {
      grammarAnswerServiceStub = sinon.createStubInstance<GrammarAnswerService>(GrammarAnswerService);

      wrapper = shallowMount<GrammarAnswerClass>(GrammarAnswerDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { grammarAnswerService: () => grammarAnswerServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGrammarAnswer = { id: 123 };
        grammarAnswerServiceStub.find.resolves(foundGrammarAnswer);

        // WHEN
        comp.retrieveGrammarAnswer(123);
        await comp.$nextTick();

        // THEN
        expect(comp.grammarAnswer).toBe(foundGrammarAnswer);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarAnswer = { id: 123 };
        grammarAnswerServiceStub.find.resolves(foundGrammarAnswer);

        // WHEN
        comp.beforeRouteEnter({ params: { grammarAnswerId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.grammarAnswer).toBe(foundGrammarAnswer);
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
