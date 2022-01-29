/* tslint:disable max-line-length */
import { shallowMount, createLocalVue, Wrapper } from '@vue/test-utils';
import sinon, { SinonStubbedInstance } from 'sinon';
import VueRouter from 'vue-router';

import * as config from '@/shared/config/config';
import DetailsVocabularyUserDetailComponent from '@/entities/details-vocabulary-user/details-vocabulary-user-details.vue';
import DetailsVocabularyUserClass from '@/entities/details-vocabulary-user/details-vocabulary-user-details.component';
import DetailsVocabularyUserService from '@/entities/details-vocabulary-user/details-vocabulary-user.service';
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
  describe('DetailsVocabularyUser Management Detail Component', () => {
    let wrapper: Wrapper<DetailsVocabularyUserClass>;
    let comp: DetailsVocabularyUserClass;
    let detailsVocabularyUserServiceStub: SinonStubbedInstance<DetailsVocabularyUserService>;

    beforeEach(() => {
      detailsVocabularyUserServiceStub = sinon.createStubInstance<DetailsVocabularyUserService>(DetailsVocabularyUserService);

      wrapper = shallowMount<DetailsVocabularyUserClass>(DetailsVocabularyUserDetailComponent, {
        store,
        i18n,
        localVue,
        router,
        provide: { detailsVocabularyUserService: () => detailsVocabularyUserServiceStub, alertService: () => new AlertService() },
      });
      comp = wrapper.vm;
    });

    describe('OnInit', () => {
      it('Should call load all on init', async () => {
        // GIVEN
        const foundDetailsVocabularyUser = { id: 123 };
        detailsVocabularyUserServiceStub.find.resolves(foundDetailsVocabularyUser);

        // WHEN
        comp.retrieveDetailsVocabularyUser(123);
        await comp.$nextTick();

        // THEN
        expect(comp.detailsVocabularyUser).toBe(foundDetailsVocabularyUser);
      });
    });

    describe('Before route enter', () => {
      it('Should retrieve data', async () => {
        // GIVEN
        const foundDetailsVocabularyUser = { id: 123 };
        detailsVocabularyUserServiceStub.find.resolves(foundDetailsVocabularyUser);

        // WHEN
        comp.beforeRouteEnter({ params: { detailsVocabularyUserId: 123 } }, null, cb => cb(comp));
        await comp.$nextTick();

        // THEN
        expect(comp.detailsVocabularyUser).toBe(foundDetailsVocabularyUser);
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
