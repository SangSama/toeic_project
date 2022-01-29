/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import GrammarUserDetailComponent from '@/entities/grammar-user/grammar-user-details.vue';
import GrammarUserClass from '@/entities/grammar-user/grammar-user-details.component';
import GrammarUserService from '@/entities/grammar-user/grammar-user.service';
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
  describe('GrammarUser Management Detail Component', () => {
    let wrapper: Wrapper<GrammarUserClass>;
    let comp: GrammarUserClass;
    let grammarUserServiceStub: SinonStubbedInstance<GrammarUserService>;

    beforeEach(() => {
      grammarUserServiceStub = sinon.createStubInstance<GrammarUserService>(GrammarUserService);

      wrapper = shallowMount<GrammarUserClass>(GrammarUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { grammarUserService: () => grammarUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundGrammarUser = { id: 123 };
        grammarUserServiceStub.find.resolves(foundGrammarUser);

        // WHEN
        comp.retrieveGrammarUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.grammarUser).toBe(foundGrammarUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarUser = { id: 123 };
        grammarUserServiceStub.find.resolves(foundGrammarUser);

        // WHEN
        comp.beforeRouteEnter({ params: { grammarUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.grammarUser).toBe(foundGrammarUser);
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
