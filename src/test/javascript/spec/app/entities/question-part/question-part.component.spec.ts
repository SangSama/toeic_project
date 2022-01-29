/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import QuestionPartComponent from '@/entities/question-part/question-part.vue';
import QuestionPartClass from '@/entities/question-part/question-part.component';
import QuestionPartService from '@/entities/question-part/question-part.service';
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
  describe('QuestionPart Management Component', () => {
    let wrapper: Wrapper<QuestionPartClass>;
    let comp: QuestionPartClass;
    let questionPartServiceStub: SinonStubbedInstance<QuestionPartService>;

    beforeEach(() => {
      questionPartServiceStub = sinon.createStubInstance<QuestionPartService>(QuestionPartService);
      questionPartServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<QuestionPartClass>(QuestionPartComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          questionPartService: () => questionPartServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      questionPartServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllQuestionParts();
      await comp.$nextTick();

      // THEN
      expect(questionPartServiceStub.retrieve.called).toBeTruthy();
      expect(comp.questionParts[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      questionPartServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(questionPartServiceStub.retrieve.called).toBeTruthy();
      expect(comp.questionParts[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      questionPartServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(questionPartServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      questionPartServiceStub.retrieve.reset();
      questionPartServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(questionPartServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.questionParts[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      questionPartServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(questionPartServiceStub.retrieve.callCount).toEqual(1);

      comp.removeQuestionPart();
      await comp.$nextTick();

      // THEN
      expect(questionPartServiceStub.delete.called).toBeTruthy();
      expect(questionPartServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
