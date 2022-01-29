/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import ToeicsDetailComponent from '@/entities/toeics/toeics-details.vue';
import ToeicsClass from '@/entities/toeics/toeics-details.component';
import ToeicsService from '@/entities/toeics/toeics.service';
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
  describe('Toeics Management Detail Component', () => {
    let wrapper: Wrapper<ToeicsClass>;
    let comp: ToeicsClass;
    let toeicsServiceStub: SinonStubbedInstance<ToeicsService>;

    beforeEach(() => {
      toeicsServiceStub = sinon.createStubInstance<ToeicsService>(ToeicsService);

      wrapper = shallowMount<ToeicsClass>(ToeicsDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { toeicsService: () => toeicsServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundToeics = { id: 123 };
        toeicsServiceStub.find.resolves(foundToeics);

        // WHEN
        comp.retrieveToeics(123);
        await comp.$nextTick();

        // THEN
        expect(comp.toeics).toBe(foundToeics);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundToeics = { id: 123 };
        toeicsServiceStub.find.resolves(foundToeics);

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
