/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import VocabularyTopicUpdateComponent from '@/entities/vocabulary-topic/vocabulary-topic-update.vue';
import VocabularyTopicClass from '@/entities/vocabulary-topic/vocabulary-topic-update.component';
import VocabularyTopicService from '@/entities/vocabulary-topic/vocabulary-topic.service';

import VocabularyUserService from '@/entities/vocabulary-user/vocabulary-user.service';

import VocabularyService from '@/entities/vocabulary/vocabulary.service';
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
  describe('VocabularyTopic Management Update Component', () => {
    let wrapper: Wrapper<VocabularyTopicClass>;
    let comp: VocabularyTopicClass;
    let vocabularyTopicServiceStub: SinonStubbedInstance<VocabularyTopicService>;

    beforeEach(() => {
      vocabularyTopicServiceStub = sinon.createStubInstance<VocabularyTopicService>(VocabularyTopicService);

      wrapper = shallowMount<VocabularyTopicClass>(VocabularyTopicUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          vocabularyTopicService: () => vocabularyTopicServiceStub,
          alertService: () => new AlertService(),

          vocabularyUserService: () =>
            sinon.createStubInstance<VocabularyUserService>(VocabularyUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          vocabularyService: () =>
            sinon.createStubInstance<VocabularyService>(VocabularyService, {
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
        comp.vocabularyTopic = entity;
        vocabularyTopicServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(vocabularyTopicServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.vocabularyTopic = entity;
        vocabularyTopicServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(vocabularyTopicServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVocabularyTopic = { id: 123 };
        vocabularyTopicServiceStub.find.resolves(foundVocabularyTopic);
        vocabularyTopicServiceStub.retrieve.resolves([foundVocabularyTopic]);

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
