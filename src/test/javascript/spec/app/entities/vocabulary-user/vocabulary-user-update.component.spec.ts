/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import VocabularyUserUpdateComponent from '@/entities/vocabulary-user/vocabulary-user-update.vue';
import VocabularyUserClass from '@/entities/vocabulary-user/vocabulary-user-update.component';
import VocabularyUserService from '@/entities/vocabulary-user/vocabulary-user.service';

import DetailsVocabularyUserService from '@/entities/details-vocabulary-user/details-vocabulary-user.service';

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
  describe('VocabularyUser Management Update Component', () => {
    let wrapper: Wrapper<VocabularyUserClass>;
    let comp: VocabularyUserClass;
    let vocabularyUserServiceStub: SinonStubbedInstance<VocabularyUserService>;

    beforeEach(() => {
      vocabularyUserServiceStub = sinon.createStubInstance<VocabularyUserService>(VocabularyUserService);

      wrapper = shallowMount<VocabularyUserClass>(VocabularyUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          vocabularyUserService: () => vocabularyUserServiceStub,
          alertService: () => new AlertService(),

          detailsVocabularyUserService: () =>
            sinon.createStubInstance<DetailsVocabularyUserService>(DetailsVocabularyUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

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
        comp.vocabularyUser = entity;
        vocabularyUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(vocabularyUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.vocabularyUser = entity;
        vocabularyUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(vocabularyUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVocabularyUser = { id: 123 };
        vocabularyUserServiceStub.find.resolves(foundVocabularyUser);
        vocabularyUserServiceStub.retrieve.resolves([foundVocabularyUser]);

        // WHEN
        comp.beforeRouteEnter({ params: { vocabularyUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.vocabularyUser).toBe(foundVocabularyUser);
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
