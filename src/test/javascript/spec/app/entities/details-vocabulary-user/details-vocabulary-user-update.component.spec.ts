/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DetailsVocabularyUserUpdateComponent from '@/entities/details-vocabulary-user/details-vocabulary-user-update.vue';
import DetailsVocabularyUserClass from '@/entities/details-vocabulary-user/details-vocabulary-user-update.component';
import DetailsVocabularyUserService from '@/entities/details-vocabulary-user/details-vocabulary-user.service';

import VocabularyUserService from '@/entities/vocabulary-user/vocabulary-user.service';
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
  describe('DetailsVocabularyUser Management Update Component', () => {
    let wrapper: Wrapper<DetailsVocabularyUserClass>;
    let comp: DetailsVocabularyUserClass;
    let detailsVocabularyUserServiceStub: SinonStubbedInstance<DetailsVocabularyUserService>;

    beforeEach(() => {
      detailsVocabularyUserServiceStub = sinon.createStubInstance<DetailsVocabularyUserService>(DetailsVocabularyUserService);

      wrapper = shallowMount<DetailsVocabularyUserClass>(DetailsVocabularyUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          detailsVocabularyUserService: () => detailsVocabularyUserServiceStub,
          alertService: () => new AlertService(),

          vocabularyUserService: () =>
            sinon.createStubInstance<VocabularyUserService>(VocabularyUserService, {
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
        comp.detailsVocabularyUser = entity;
        detailsVocabularyUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(detailsVocabularyUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.detailsVocabularyUser = entity;
        detailsVocabularyUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(detailsVocabularyUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDetailsVocabularyUser = { id: 123 };
        detailsVocabularyUserServiceStub.find.resolves(foundDetailsVocabularyUser);
        detailsVocabularyUserServiceStub.retrieve.resolves([foundDetailsVocabularyUser]);

        // WHEN
        comp.beforeRouteEnter({ params: { detailsVocabularyUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.detailsVocabularyUser).toBe(foundDetailsVocabularyUser);
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
