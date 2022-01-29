/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import PartsDetailComponent from '@/entities/parts/parts-details.vue';
import PartsClass from '@/entities/parts/parts-details.component';
import PartsService from '@/entities/parts/parts.service';
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
  describe('Parts Management Detail Component', () => {
    let wrapper: Wrapper<PartsClass>;
    let comp: PartsClass;
    let partsServiceStub: SinonStubbedInstance<PartsService>;

    beforeEach(() => {
      partsServiceStub = sinon.createStubInstance<PartsService>(PartsService);

      wrapper = shallowMount<PartsClass>(PartsDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { partsService: () => partsServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundParts = { id: 123 };
        partsServiceStub.find.resolves(foundParts);

        // WHEN
        comp.retrieveParts(123);
        await comp.$nextTick();

        // THEN
        expect(comp.parts).toBe(foundParts);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundParts = { id: 123 };
        partsServiceStub.find.resolves(foundParts);

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
