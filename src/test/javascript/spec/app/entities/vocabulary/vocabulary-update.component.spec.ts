/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import VocabularyUpdateComponent from '@/entities/vocabulary/vocabulary-update.vue';
import VocabularyClass from '@/entities/vocabulary/vocabulary-update.component';
import VocabularyService from '@/entities/vocabulary/vocabulary.service';

import VocabularyTopicService from '@/entities/vocabulary-topic/vocabulary-topic.service';
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
  describe('Vocabulary Management Update Component', () => {
    let wrapper: Wrapper<VocabularyClass>;
    let comp: VocabularyClass;
    let vocabularyServiceStub: SinonStubbedInstance<VocabularyService>;

    beforeEach(() => {
      vocabularyServiceStub = sinon.createStubInstance<VocabularyService>(VocabularyService);

      wrapper = shallowMount<VocabularyClass>(VocabularyUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          vocabularyService: () => vocabularyServiceStub,
          alertService: () => new AlertService(),

          vocabularyTopicService: () =>
            sinon.createStubInstance<VocabularyTopicService>(VocabularyTopicService, {
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
        comp.vocabulary = entity;
        vocabularyServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(vocabularyServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.vocabulary = entity;
        vocabularyServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(vocabularyServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVocabulary = { id: 123 };
        vocabularyServiceStub.find.resolves(foundVocabulary);
        vocabularyServiceStub.retrieve.resolves([foundVocabulary]);

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
