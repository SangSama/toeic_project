/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import VocabularyTopicDetailComponent from '@/entities/vocabulary-topic/vocabulary-topic-details.vue';
import VocabularyTopicClass from '@/entities/vocabulary-topic/vocabulary-topic-details.component';
import VocabularyTopicService from '@/entities/vocabulary-topic/vocabulary-topic.service';
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
  describe('VocabularyTopic Management Detail Component', () => {
    let wrapper: Wrapper<VocabularyTopicClass>;
    let comp: VocabularyTopicClass;
    let vocabularyTopicServiceStub: SinonStubbedInstance<VocabularyTopicService>;

    beforeEach(() => {
      vocabularyTopicServiceStub = sinon.createStubInstance<VocabularyTopicService>(VocabularyTopicService);

      wrapper = shallowMount<VocabularyTopicClass>(VocabularyTopicDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { vocabularyTopicService: () => vocabularyTopicServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVocabularyTopic = { id: 123 };
        vocabularyTopicServiceStub.find.resolves(foundVocabularyTopic);

        // WHEN
        comp.retrieveVocabularyTopic(123);
        await comp.$nextTick();

        // THEN
        expect(comp.vocabularyTopic).toBe(foundVocabularyTopic);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVocabularyTopic = { id: 123 };
        vocabularyTopicServiceStub.find.resolves(foundVocabularyTopic);

        // WHEN
        comp.beforeRouteEnter({ params: { vocabularyTopicId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.vocabularyTopic).toBe(foundVocabularyTopic);
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
