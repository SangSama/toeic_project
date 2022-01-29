/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import DetailsGrammarUserUpdateComponent from '@/entities/details-grammar-user/details-grammar-user-update.vue';
import DetailsGrammarUserClass from '@/entities/details-grammar-user/details-grammar-user-update.component';
import DetailsGrammarUserService from '@/entities/details-grammar-user/details-grammar-user.service';

import GrammarUserService from '@/entities/grammar-user/grammar-user.service';
import AlertService from '@/shared/alert/alert.service';

const localVue = createLocalVue();

config.initVueApp(localVue);
const i18n = config.initI18N(localVue);
const store = config.initVueXStore(localVue);
const router = new Router();
localVue.use(Router);
localVue.use(ToastPlugin);
localVue.component('font-awesome-icon', {});
localVue.component('b-input-group', {});
localVue.component('b-input-group-prepend', {});
localVue.component('b-form-datepicker', {});
localVue.component('b-form-input', {});

describe('Component Tests', () => {
  describe('DetailsGrammarUser Management Update Component', () => {
    let wrapper: Wrapper<DetailsGrammarUserClass>;
    let comp: DetailsGrammarUserClass;
    let detailsGrammarUserServiceStub: SinonStubbedInstance<DetailsGrammarUserService>;

    beforeEach(() => {
      detailsGrammarUserServiceStub = sinon.createStubInstance<DetailsGrammarUserService>(DetailsGrammarUserService);

      wrapper = shallowMount<DetailsGrammarUserClass>(DetailsGrammarUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          detailsGrammarUserService: () => detailsGrammarUserServiceStub,
          alertService: () => new AlertService(),

          grammarUserService: () =>
            sinon.createStubInstance<GrammarUserService>(GrammarUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),
        },
      });
      comp = wrapper.vm;
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', async () => {
        // GIVEN
        const entity = { id: 123 };
        comp.detailsGrammarUser = entity;
        detailsGrammarUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(detailsGrammarUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.detailsGrammarUser = entity;
        detailsGrammarUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(detailsGrammarUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDetailsGrammarUser = { id: 123 };
        detailsGrammarUserServiceStub.find.resolves(foundDetailsGrammarUser);
        detailsGrammarUserServiceStub.retrieve.resolves([foundDetailsGrammarUser]);

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
