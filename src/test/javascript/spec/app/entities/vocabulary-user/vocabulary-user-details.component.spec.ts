/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import VocabularyUserDetailComponent from '@/entities/vocabulary-user/vocabulary-user-details.vue';
import VocabularyUserClass from '@/entities/vocabulary-user/vocabulary-user-details.component';
import VocabularyUserService from '@/entities/vocabulary-user/vocabulary-user.service';
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
  describe('VocabularyUser Management Detail Component', () => {
    let wrapper: Wrapper<VocabularyUserClass>;
    let comp: VocabularyUserClass;
    let vocabularyUserServiceStub: SinonStubbedInstance<VocabularyUserService>;

    beforeEach(() => {
      vocabularyUserServiceStub = sinon.createStubInstance<VocabularyUserService>(VocabularyUserService);

      wrapper = shallowMount<VocabularyUserClass>(VocabularyUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { vocabularyUserService: () => vocabularyUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundVocabularyUser = { id: 123 };
        vocabularyUserServiceStub.find.resolves(foundVocabularyUser);

        // WHEN
        comp.retrieveVocabularyUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.vocabularyUser).toBe(foundVocabularyUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundVocabularyUser = { id: 123 };
        vocabularyUserServiceStub.find.resolves(foundVocabularyUser);

        // WHEN
        comp.beforeRouteEnter({ params: { vocabularyUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.vocabularyUser).toBe(foundVocabularyUser);
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
