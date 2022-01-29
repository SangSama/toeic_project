/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import ExtendQuestionUpdateComponent from '@/entities/extend-question/extend-question-update.vue';
import ExtendQuestionClass from '@/entities/extend-question/extend-question-update.component';
import ExtendQuestionService from '@/entities/extend-question/extend-question.service';

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
  describe('ExtendQuestion Management Update Component', () => {
    let wrapper: Wrapper<ExtendQuestionClass>;
    let comp: ExtendQuestionClass;
    let extendQuestionServiceStub: SinonStubbedInstance<ExtendQuestionService>;

    beforeEach(() => {
      extendQuestionServiceStub = sinon.createStubInstance<ExtendQuestionService>(ExtendQuestionService);

      wrapper = shallowMount<ExtendQuestionClass>(ExtendQuestionUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          extendQuestionService: () => extendQuestionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.extendQuestion = entity;
        extendQuestionServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(extendQuestionServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.extendQuestion = entity;
        extendQuestionServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(extendQuestionServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundExtendQuestion = { id: 123 };
        extendQuestionServiceStub.find.resolves(foundExtendQuestion);
        extendQuestionServiceStub.retrieve.resolves([foundExtendQuestion]);

        // WHEN
        comp.beforeRouteEnter({ params: { extendQuestionId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.extendQuestion).toBe(foundExtendQuestion);
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
