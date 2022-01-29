<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="qnA">
        <h2 class="jh-entity-heading" data-cy="qnADetailsHeading">
          <span v-text="$t('finalProjectApp.qnA.detail.title')">QnA</span> {{ qnA.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.qnA.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ qnA.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.qnA.email')">Email</span>
          </dt>
          <dd>
            <span>{{ qnA.email }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.qnA.status')">Status</span>
          </dt>
          <dd>
            <span>{{ qnA.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.qnA.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ qnA.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.qnA.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ qnA.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="qnA.id" :to="{ name: 'QnAEdit', params: { qnAId: qnA.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./qn-a-details.component.ts"></script>
