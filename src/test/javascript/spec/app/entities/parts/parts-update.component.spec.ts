/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import PartsUpdateComponent from '@/entities/parts/parts-update.vue';
import PartsClass from '@/entities/parts/parts-update.component';
import PartsService from '@/entities/parts/parts.service';

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
  describe('Parts Management Update Component', () => {
    let wrapper: Wrapper<PartsClass>;
    let comp: PartsClass;
    let partsServiceStub: SinonStubbedInstance<PartsService>;

    beforeEach(() => {
      partsServiceStub = sinon.createStubInstance<PartsService>(PartsService);

      wrapper = shallowMount<PartsClass>(PartsUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          partsService: () => partsServiceStub,
          alertService: () => new AlertService(),

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
        comp.parts = entity;
        partsServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(partsServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.parts = entity;
        partsServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(partsServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundParts = { id: 123 };
        partsServiceStub.find.resolves(foundParts);
        partsServiceStub.retrieve.resolves([foundParts]);

        // WHEN
        comp.beforeRouteEnter({ params: { partsId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.parts).toBe(foundParts);
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
