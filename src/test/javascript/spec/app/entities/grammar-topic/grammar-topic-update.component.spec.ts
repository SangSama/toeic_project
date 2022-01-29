/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import Router from 'vue-router';
import { ToastPlugin } from 'bootstrap-vue';

import * as config from '@/shared/config/config';
import GrammarTopicUpdateComponent from '@/entities/grammar-topic/grammar-topic-update.vue';
import GrammarTopicClass from '@/entities/grammar-topic/grammar-topic-update.component';
import GrammarTopicService from '@/entities/grammar-topic/grammar-topic.service';

import GrammarUserService from '@/entities/grammar-user/grammar-user.service';

import GrammarQuestionService from '@/entities/grammar-question/grammar-question.service';
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
  describe('GrammarTopic Management Update Component', () => {
    let wrapper: Wrapper<GrammarTopicClass>;
    let comp: GrammarTopicClass;
    let grammarTopicServiceStub: SinonStubbedInstance<GrammarTopicService>;

    beforeEach(() => {
      grammarTopicServiceStub = sinon.createStubInstance<GrammarTopicService>(GrammarTopicService);

      wrapper = shallowMount<GrammarTopicClass>(GrammarTopicUpdateComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: {
          grammarTopicService: () => grammarTopicServiceStub,
          alertService: () => new AlertService(),

          grammarUserService: () =>
            sinon.createStubInstance<GrammarUserService>(GrammarUserService, {
              retrieve: sinon.stub().resolves({}),
            } as any),

          grammarQuestionService: () =>
            sinon.createStubInstance<GrammarQuestionService>(GrammarQuestionService, {
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
        comp.grammarTopic = entity;
        grammarTopicServiceStub.update.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarTopicServiceStub.update.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });

      it('Should call create service on save for new entity', async () => {
        // GIVEN
        const entity = {};
        comp.grammarTopic = entity;
        grammarTopicServiceStub.create.resolves(entity);

        // WHEN
        comp.save();
        await comp.$nextTick();

        // THEN
        expect(grammarTopicServiceStub.create.calledWith(entity)).toBeTruthy();
        expect(comp.isSaving).toEqual(false);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundGrammarTopic = { id: 123 };
        grammarTopicServiceStub.find.resolves(foundGrammarTopic);
        grammarTopicServiceStub.retrieve.resolves([foundGrammarTopic]);

        // WHEN
        comp.beforeRouteEnter({ params: { grammarTopicId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.grammarTopic).toBe(foundGrammarTopic);
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
