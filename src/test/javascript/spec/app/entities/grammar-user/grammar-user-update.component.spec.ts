/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import GrammarUserUpdateComponent from '@/entities/grammar-user/grammar-user-update.vue';
import GrammarUserClass from '@/entities/grammar-user/grammar-user-update.component';
import GrammarUserService from '@/entities/grammar-user/grammar-user.service';

import DetailsGrammarUserService from '@/entities/details-grammar-user/details-grammar-user.service';

import GrammarTopicService from '@/entities/grammar-topic/grammar-topic.service';
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
  describe('GrammarUser Management Update Component', () => {
    let wrapper: Wrapper<GrammarUserClass>;
    let comp: GrammarUserClass;
    let grammarUserServiceStub: SinonStubbedInstance<GrammarUserService>;

    beforeEach(() => {
      grammarUserServiceStub = sinon.createStubInstance<GrammarUserService>(GrammarUserService);

      wrapper = shallowMount<GrammarUserClass>(GrammarUserUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          grammarUserService: () => grammarUserServiceStub,
          alertService: () => new AlertService(),

          detailsGrammarUserService: () =>
            sinon.createStubInstance<DetailsGrammarUserService>(DetailsGrammarUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          grammarTopicService: () =>
            sinon.createStubInstance<GrammarTopicService>(GrammarTopicService, {
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
        comp.grammarUser = entity;
        grammarUserServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarUserServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.grammarUser = entity;
        grammarUserServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarUserServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarUser = { id: 123 };
        grammarUserServiceStub.find.resolves(foundGrammarUser);
        grammarUserServiceStub.retrieve.resolves([foundGrammarUser]);

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
