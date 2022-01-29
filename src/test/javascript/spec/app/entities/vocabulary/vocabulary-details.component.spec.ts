/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import VocabularyDetailComponent from '@/entities/vocabulary/vocabulary-details.vue';
import VocabularyClass from '@/entities/vocabulary/vocabulary-details.component';
import VocabularyService from '@/entities/vocabulary/vocabulary.service';
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
  describe('Vocabulary Management Detail Component', () => {
    let wrapper: Wrapper<VocabularyClass>;
    let comp: VocabularyClass;
    let vocabularyServiceStub: SinonStubbedInstance<VocabularyService>;

    beforeEach(() => {
      vocabularyServiceStub = sinon.createStubInstance<VocabularyService>(VocabularyService);

      wrapper = shallowMount<VocabularyClass>(VocabularyDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { vocabularyService: () => vocabularyServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVocabulary = { id: 123 };
        vocabularyServiceStub.find.resolves(foundVocabulary);

        // WHEN
        comp.retrieveVocabulary(123);
        await comp.$nextTick();

        // THEN
        expect(comp.vocabulary).toBe(foundVocabulary);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVocabulary = { id: 123 };
        vocabularyServiceStub.find.resolves(foundVocabulary);

        // WHEN
        comp.beforeRouteEnter({ params: { vocabularyId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.vocabulary).toBe(foundVocabulary);
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
