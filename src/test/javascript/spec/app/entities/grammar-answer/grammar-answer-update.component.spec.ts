/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import GrammarAnswerUpdateComponent from '@/entities/grammar-answer/grammar-answer-update.vue';
import GrammarAnswerClass from '@/entities/grammar-answer/grammar-answer-update.component';
import GrammarAnswerService from '@/entities/grammar-answer/grammar-answer.service';

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
  describe('GrammarAnswer Management Update Component', () => {
    let wrapper: Wrapper<GrammarAnswerClass>;
    let comp: GrammarAnswerClass;
    let grammarAnswerServiceStub: SinonStubbedInstance<GrammarAnswerService>;

    beforeEach(() => {
      grammarAnswerServiceStub = sinon.createStubInstance<GrammarAnswerService>(GrammarAnswerService);

      wrapper = shallowMount<GrammarAnswerClass>(GrammarAnswerUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          grammarAnswerService: () => grammarAnswerServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.grammarAnswer = entity;
        grammarAnswerServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarAnswerServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.grammarAnswer = entity;
        grammarAnswerServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarAnswerServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarAnswer = { id: 123 };
        grammarAnswerServiceStub.find.resolves(foundGrammarAnswer);
        grammarAnswerServiceStub.retrieve.resolves([foundGrammarAnswer]);

        // WHEN
        comp.beforeRouteEnter({ params: { grammarAnswerId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.grammarAnswer).toBe(foundGrammarAnswer);
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
