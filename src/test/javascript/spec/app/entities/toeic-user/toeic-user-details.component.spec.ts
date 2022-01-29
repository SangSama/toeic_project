/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ToeicUserDetailComponent from '@/entities/toeic-user/toeic-user-details.vue';
import ToeicUserClass from '@/entities/toeic-user/toeic-user-details.component';
import ToeicUserService from '@/entities/toeic-user/toeic-user.service';
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
  describe('ToeicUser Management Detail Component', () => {
    let wrapper: Wrapper<ToeicUserClass>;
    let comp: ToeicUserClass;
    let toeicUserServiceStub: SinonStubbedInstance<ToeicUserService>;

    beforeEach(() => {
      toeicUserServiceStub = sinon.createStubInstance<ToeicUserService>(ToeicUserService);

      wrapper = shallowMount<ToeicUserClass>(ToeicUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { toeicUserService: () => toeicUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundToeicUser = { id: 123 };
        toeicUserServiceStub.find.resolves(foundToeicUser);

        // WHEN
        comp.retrieveToeicUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.toeicUser).toBe(foundToeicUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundToeicUser = { id: 123 };
        toeicUserServiceStub.find.resolves(foundToeicUser);

        // WHEN
        comp.beforeRouteEnter({ params: { toeicUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.toeicUser).toBe(foundToeicUser);
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
