/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ToeicsUpdateComponent from '@/entities/toeics/toeics-update.vue';
import ToeicsClass from '@/entities/toeics/toeics-update.component';
import ToeicsService from '@/entities/toeics/toeics.service';

import ToeicUserService from '@/entities/toeic-user/toeic-user.service';

import PartsService from '@/entities/parts/parts.service';
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
  describe('Toeics Management Update Component', () => {
    let wrapper: Wrapper<ToeicsClass>;
    let comp: ToeicsClass;
    let toeicsServiceStub: SinonStubbedInstance<ToeicsService>;

    beforeEach(() => {
      toeicsServiceStub = sinon.createStubInstance<ToeicsService>(ToeicsService);

      wrapper = shallowMount<ToeicsClass>(ToeicsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          toeicsService: () => toeicsServiceStub,
          alertService: () => new AlertService(),

          toeicUserService: () =>
            sinon.createStubInstance<ToeicUserService>(ToeicUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          partsService: () =>
            sinon.createStubInstance<PartsService>(PartsService, {
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
        comp.toeics = entity;
        toeicsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(toeicsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.toeics = entity;
        toeicsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(toeicsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundToeics = { id: 123 };
        toeicsServiceStub.find.resolves(foundToeics);
        toeicsServiceStub.retrieve.resolves([foundToeics]);

        // WHEN
        comp.beforeRouteEnter({ params: { toeicsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.toeics).toBe(foundToeics);
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
