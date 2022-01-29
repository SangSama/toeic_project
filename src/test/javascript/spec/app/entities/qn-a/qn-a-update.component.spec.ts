/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import QnAUpdateComponent from '@/entities/qn-a/qn-a-update.vue';
import QnAClass from '@/entities/qn-a/qn-a-update.component';
import QnAService from '@/entities/qn-a/qn-a.service';

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
  describe('QnA Management Update Component', () => {
    let wrapper: Wrapper<QnAClass>;
    let comp: QnAClass;
    let qnAServiceStub: SinonStubbedInstance<QnAService>;

    beforeEach(() => {
      qnAServiceStub = sinon.createStubInstance<QnAService>(QnAService);

      wrapper = shallowMount<QnAClass>(QnAUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          qnAService: () => qnAServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.qnA = entity;
        qnAServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qnAServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.qnA = entity;
        qnAServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(qnAServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundQnA = { id: 123 };
        qnAServiceStub.find.resolves(foundQnA);
        qnAServiceStub.retrieve.resolves([foundQnA]);

        // WHEN
        comp.beforeRouteEnter({ params: { qnAId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.qnA).toBe(foundQnA);
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
