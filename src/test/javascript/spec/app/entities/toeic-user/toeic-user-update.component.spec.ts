/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ToeicUserUpdateComponent from '@/entities/toeic-user/toeic-user-update.vue';
import ToeicUserClass from '@/entities/toeic-user/toeic-user-update.component';
import ToeicUserService from '@/entities/toeic-user/toeic-user.service';

import DetailsToeicUserService from '@/entities/details-toeic-user/details-toeic-user.service';

import ToeicsService from '@/entities/toeics/toeics.service';
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
  describe('ToeicUser Management Update Component', () => {
    let wrapper: Wrapper<ToeicUserClass>;
    let comp: ToeicUserClass;
    let toeicUserServiceStub: SinonStubbedInstance<ToeicUserService>;

    beforeEach(() => {
      toeicUserServiceStub = sinon.createStubInstance<ToeicUserService>(ToeicUserService);

      wrapper = shallowMount<ToeicUserClass>(ToeicUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          toeicUserService: () => toeicUserServiceStub,
          alertService: () => new AlertService(),

          detailsToeicUserService: () =>
            sinon.createStubInstance<DetailsToeicUserService>(DetailsToeicUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          toeicsService: () =>
            sinon.createStubInstance<ToeicsService>(ToeicsService, {
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
        comp.toeicUser = entity;
        toeicUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(toeicUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.toeicUser = entity;
        toeicUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(toeicUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundToeicUser = { id: 123 };
        toeicUserServiceStub.find.resolves(foundToeicUser);
        toeicUserServiceStub.retrieve.resolves([foundToeicUser]);

        // WHEN
        comp.beforeRouteEnter({ params: { toeicUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.toeicUser).toBe(foundToeicUser);
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
