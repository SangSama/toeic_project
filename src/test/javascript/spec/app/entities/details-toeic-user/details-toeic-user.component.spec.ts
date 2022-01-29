/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DetailsToeicUserComponent from '@/entities/details-toeic-user/details-toeic-user.vue';
import DetailsToeicUserClass from '@/entities/details-toeic-user/details-toeic-user.component';
import DetailsToeicUserService from '@/entities/details-toeic-user/details-toeic-user.service';
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
  describe('DetailsToeicUser Management Component', () => {
    let wrapper: Wrapper<DetailsToeicUserClass>;
    let comp: DetailsToeicUserClass;
    let detailsToeicUserServiceStub: SinonStubbedInstance<DetailsToeicUserService>;

    beforeEach(() => {
      detailsToeicUserServiceStub = sinon.createStubInstance<DetailsToeicUserService>(DetailsToeicUserService);
      detailsToeicUserServiceStub.retrieve.resolves({ headers: {} });

      wrapper = shallowMount<DetailsToeicUserClass>(DetailsToeicUserComponent, {
        store,
        i18n,
        localVue,
        stubs: { jhiItemCount: true, bPagination: true, bModal: bModalStub as any },
        provide: {
          detailsToeicUserService: () => detailsToeicUserServiceStub,
          alertService: () => new AlertService(),
        },
      });
      comp = wrapper.vm;
    });

    it('Should call load all on init', async () => {
      // GIVEN
      detailsToeicUserServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.retrieveAllDetailsToeicUsers();
      await comp.$nextTick();

      // THEN
      expect(detailsToeicUserServiceStub.retrieve.called).toBeTruthy();
      expect(comp.detailsToeicUsers[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should load a page', async () => {
      // GIVEN
      detailsToeicUserServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();

      // THEN
      expect(detailsToeicUserServiceStub.retrieve.called).toBeTruthy();
      expect(comp.detailsToeicUsers[0]).toEqual(expect.objectContaining({ id: 123 }));
    });

    it('should not load a page if the page is the same as the previous page', () => {
      // GIVEN
      detailsToeicUserServiceStub.retrieve.reset();
      comp.previousPage = 1;

      // WHEN
      comp.loadPage(1);

      // THEN
      expect(detailsToeicUserServiceStub.retrieve.called).toBeFalsy();
    });

    it('should re-initialize the page', async () => {
      // GIVEN
      detailsToeicUserServiceStub.retrieve.reset();
      detailsToeicUserServiceStub.retrieve.resolves({ headers: {}, data: [{ id: 123 }] });

      // WHEN
      comp.loadPage(2);
      await comp.$nextTick();
      comp.clear();
      await comp.$nextTick();

      // THEN
      expect(detailsToeicUserServiceStub.retrieve.callCount).toEqual(3);
      expect(comp.page).toEqual(1);
      expect(comp.detailsToeicUsers[0]).toEqual(expect.objectContaining({ id: 123 }));
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
      detailsToeicUserServiceStub.delete.resolves({});

      // WHEN
      comp.prepareRemove({ id: 123 });
      expect(detailsToeicUserServiceStub.retrieve.callCount).toEqual(1);

      comp.removeDetailsToeicUser();
      await comp.$nextTick();

      // THEN
      expect(detailsToeicUserServiceStub.delete.called).toBeTruthy();
      expect(detailsToeicUserServiceStub.retrieve.callCount).toEqual(2);
    });
  });
});
