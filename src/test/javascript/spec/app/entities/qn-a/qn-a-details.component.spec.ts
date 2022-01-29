/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import QnADetailComponent from '@/entities/qn-a/qn-a-details.vue';
import QnAClass from '@/entities/qn-a/qn-a-details.component';
import QnAService from '@/entities/qn-a/qn-a.service';
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
  describe('QnA Management Detail Component', () => {
    let wrapper: Wrapper<QnAClass>;
    let comp: QnAClass;
    let qnAServiceStub: SinonStubbedInstance<QnAService>;

    beforeEach(() => {
      qnAServiceStub = sinon.createStubInstance<QnAService>(QnAService);

      wrapper = shallowMount<QnAClass>(QnADetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { qnAService: () => qnAServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundQnA = { id: 123 };
        qnAServiceStub.find.resolves(foundQnA);

        // WHEN
        comp.retrieveQnA(123);
        await comp.$nextTick();

        // THEN
        expect(comp.qnA).toBe(foundQnA);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundQnA = { id: 123 };
        qnAServiceStub.find.resolves(foundQnA);

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
