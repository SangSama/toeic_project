/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DetailsToeicUserDetailComponent from '@/entities/details-toeic-user/details-toeic-user-details.vue';
import DetailsToeicUserClass from '@/entities/details-toeic-user/details-toeic-user-details.component';
import DetailsToeicUserService from '@/entities/details-toeic-user/details-toeic-user.service';
import router from '@/router';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();
localVue.use(VueRouter);

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
localVue.component('font-awesome-icon', {});
localVue.component('router-link', {});

describe('Component Tests', () => {
  describe('DetailsToeicUser Management Detail Component', () => {
    let wrapper: Wrapper<DetailsToeicUserClass>;
    let comp: DetailsToeicUserClass;
    let detailsToeicUserServiceStub: SinonStubbedInstance<DetailsToeicUserService>;

    beforeEach(() => {
      detailsToeicUserServiceStub = sinon.createStubInstance<DetailsToeicUserService>(DetailsToeicUserService);

      wrapper = shallowMount<DetailsToeicUserClass>(DetailsToeicUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { detailsToeicUserService: () => detailsToeicUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDetailsToeicUser = { id: 123 };
        detailsToeicUserServiceStub.find.resolves(foundDetailsToeicUser);

        // WHEN
        comp.retrieveDetailsToeicUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.detailsToeicUser).toBe(foundDetailsToeicUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDetailsToeicUser = { id: 123 };
        detailsToeicUserServiceStub.find.resolves(foundDetailsToeicUser);

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
