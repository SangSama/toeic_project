/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DetailsGrammarUserDetailComponent from '@/entities/details-grammar-user/details-grammar-user-details.vue';
import DetailsGrammarUserClass from '@/entities/details-grammar-user/details-grammar-user-details.component';
import DetailsGrammarUserService from '@/entities/details-grammar-user/details-grammar-user.service';
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
  describe('DetailsGrammarUser Management Detail Component', () => {
    let wrapper: Wrapper<DetailsGrammarUserClass>;
    let comp: DetailsGrammarUserClass;
    let detailsGrammarUserServiceStub: SinonStubbedInstance<DetailsGrammarUserService>;

    beforeEach(() => {
      detailsGrammarUserServiceStub = sinon.createStubInstance<DetailsGrammarUserService>(DetailsGrammarUserService);

      wrapper = shallowMount<DetailsGrammarUserClass>(DetailsGrammarUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { detailsGrammarUserService: () => detailsGrammarUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDetailsGrammarUser = { id: 123 };
        detailsGrammarUserServiceStub.find.resolves(foundDetailsGrammarUser);

        // WHEN
        comp.retrieveDetailsGrammarUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.detailsGrammarUser).toBe(foundDetailsGrammarUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDetailsGrammarUser = { id: 123 };
        detailsGrammarUserServiceStub.find.resolves(foundDetailsGrammarUser);

        // WHEN
        comp.beforeRouteEnter({ params: { detailsGrammarUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.detailsGrammarUser).toBe(foundDetailsGrammarUser);
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
