<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="detailsGrammarUser">
        <h2 class="jh-entity-heading" data-cy="detailsGrammarUserDetailsHeading">
          <span v-text="$t('finalProjectApp.detailsGrammarUser.detail.title')">DetailsGrammarUser</span> {{ detailsGrammarUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.detailsGrammarUser.grammarQuestionId')">Grammar Question Id</span>
          </dt>
          <dd>
            <span>{{ detailsGrammarUser.grammarQuestionId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsGrammarUser.status')">Status</span>
          </dt>
          <dd>
            <span>{{ detailsGrammarUser.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsGrammarUser.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ detailsGrammarUser.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsGrammarUser.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ detailsGrammarUser.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsGrammarUser.grammarUser')">Grammar User</span>
          </dt>
          <dd>
            <div v-if="detailsGrammarUser.grammarUser">
              <router-link :to="{ name: 'GrammarUserView', params: { grammarUserId: detailsGrammarUser.grammarUser.id } }">{{
                detailsGrammarUser.grammarUser.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="detailsGrammarUser.id"
          :to="{ name: 'DetailsGrammarUserEdit', params: { detailsGrammarUserId: detailsGrammarUser.id } }"
          custom
          v-slot="{ navigate }"
        >
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./details-grammar-user-details.component.ts"></script>
