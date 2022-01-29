/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import GrammarTopicDetailComponent from '@/entities/grammar-topic/grammar-topic-details.vue';
import GrammarTopicClass from '@/entities/grammar-topic/grammar-topic-details.component';
import GrammarTopicService from '@/entities/grammar-topic/grammar-topic.service';
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
  describe('GrammarTopic Management Detail Component', () => {
    let wrapper: Wrapper<GrammarTopicClass>;
    let comp: GrammarTopicClass;
    let grammarTopicServiceStub: SinonStubbedInstance<GrammarTopicService>;

    beforeEach(() => {
      grammarTopicServiceStub = sinon.createStubInstance<GrammarTopicService>(GrammarTopicService);

      wrapper = shallowMount<GrammarTopicClass>(GrammarTopicDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { grammarTopicService: () => grammarTopicServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGrammarTopic = { id: 123 };
        grammarTopicServiceStub.find.resolves(foundGrammarTopic);

        // WHEN
        comp.retrieveGrammarTopic(123);
        await comp.$nextTick();

        // THEN
        expect(comp.grammarTopic).toBe(foundGrammarTopic);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarTopic = { id: 123 };
        grammarTopicServiceStub.find.resolves(foundGrammarTopic);

        // WHEN
        comp.beforeRouteEnter({ params: { grammarTopicId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.grammarTopic).toBe(foundGrammarTopic);
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
