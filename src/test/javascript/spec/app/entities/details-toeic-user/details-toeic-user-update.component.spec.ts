/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DetailsToeicUserUpdateComponent from '@/entities/details-toeic-user/details-toeic-user-update.vue';
import DetailsToeicUserClass from '@/entities/details-toeic-user/details-toeic-user-update.component';
import DetailsToeicUserService from '@/entities/details-toeic-user/details-toeic-user.service';

import ToeicUserService from '@/entities/toeic-user/toeic-user.service';
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
  describe('DetailsToeicUser Management Update Component', () => {
    let wrapper: Wrapper<DetailsToeicUserClass>;
    let comp: DetailsToeicUserClass;
    let detailsToeicUserServiceStub: SinonStubbedInstance<DetailsToeicUserService>;

    beforeEach(() => {
      detailsToeicUserServiceStub = sinon.createStubInstance<DetailsToeicUserService>(DetailsToeicUserService);

      wrapper = shallowMount<DetailsToeicUserClass>(DetailsToeicUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          detailsToeicUserService: () => detailsToeicUserServiceStub,
          alertService: () => new AlertService(),

          toeicUserService: () =>
            sinon.createStubInstance<ToeicUserService>(ToeicUserService, {
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
        comp.detailsToeicUser = entity;
        detailsToeicUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(detailsToeicUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.detailsToeicUser = entity;
        detailsToeicUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(detailsToeicUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDetailsToeicUser = { id: 123 };
        detailsToeicUserServiceStub.find.resolves(foundDetailsToeicUser);
        detailsToeicUserServiceStub.retrieve.resolves([foundDetailsToeicUser]);

        // WHEN
        comp.beforeRouteEnter({ params: { detailsToeicUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.detailsToeicUser).toBe(foundDetailsToeicUser);
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
