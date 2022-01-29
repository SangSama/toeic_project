/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import GrammarQuestionComponent from '@/entities/grammar-question/grammar-question.vue';
import GrammarQuestionClass from '@/entities/grammar-question/grammar-question.component';
import GrammarQuestionService from '@/entities/grammar-question/grammar-question.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(ToastPlugin);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('b-badge', {});
localVue.component('jhi-sort-indicator', {});
localVue.directive('b-modal', {});
localVue.component('b-button', {});
localVue.component('router-link', {});

const bModalStub = {
  render: () => {},
  methods: {
    hide: () => {},
    show: () => {},
  },
};

describe('Component Tests', () => {
  describe('GrammarQuestion Management Component', () => {
    let wrapper: Wrapper<GrammarQuestionClass>;
    let comp: GrammarQuestionClass;
    let grammarQuestionServiceStub: SinonStubbedInstance<GrammarQuestionService>;

    beforeEach(() => {
      grammarQuestionServiceStub = sinon.createStubInstance<GrammarQuestionService>(GrammarQuestionService);
      grammarQuestionServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<GrammarQuestionClass>(GrammarQuestionComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          grammarQuestionService: () => grammarQuestionServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      grammarQuestionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllGrammarQuestions();
      await comp.$nextTick();

      // THEN
      expect(grammarQuestionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.grammarQuestions[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      grammarQuestionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(grammarQuestionServiceStub.retrieve.called).toBeTruthy();
      expect(comp.grammarQuestions[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      grammarQuestionServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(grammarQuestionServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      grammarQuestionServiceStub.retrieve.reset();
      grammarQuestionServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(grammarQuestionServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.grammarQuestions[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should calculate the sort attribute for an id', () => {
      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['id,asc']);
    });

    it('should calculate the sort attribute for a non-id attribute', () => {
      // GIVEN
      comp.propOrder = 'name';

      // WHEN
      const result = comp.sort();

      // THEN
      expect(result).toEqual(['name,asc', 'id']);
    });
    it('Should call delete service on confirmDelete', async () => {
      // GIVEN
      grammarQuestionServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(grammarQuestionServiceStub.retrieve.callCount).toEqual(1);

      comp.removeGrammarQuestion();
      await comp.$nextTick();

      // THEN
      expect(grammarQuestionServiceStub.delete.called).toBeTruthy();
      expect(grammarQuestionServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
