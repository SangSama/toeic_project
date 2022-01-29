<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="toeics">
        <h2 class="jh-entity-heading" data-cy="toeicsDetailsHeading">
          <span v-text="$t('finalProjectApp.toeics.detail.title')">Toeics</span> {{ toeics.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.toeics.nameToeic')">Name Toeic</span>
          </dt>
          <dd>
            <span>{{ toeics.nameToeic }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.number')">Number</span>
          </dt>
          <dd>
            <span>{{ toeics.number }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.description')">Description</span>
          </dt>
          <dd>
            <span>{{ toeics.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.view')">View</span>
          </dt>
          <dd>
            <span>{{ toeics.view }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.test')">Test</span>
          </dt>
          <dd>
            <span>{{ toeics.test }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.linkDetail')">Link Detail</span>
          </dt>
          <dd>
            <span>{{ toeics.linkDetail }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ toeics.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeics.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ toeics.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="toeics.id" :to="{ name: 'ToeicsEdit', params: { toeicsId: toeics.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./toeics-details.component.ts"></script>
