<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="detailsToeicUser">
        <h2 class="jh-entity-heading" data-cy="detailsToeicUserDetailsHeading">
          <span v-text="$t('finalProjectApp.detailsToeicUser.detail.title')">DetailsToeicUser</span> {{ detailsToeicUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.toeicId')">Toeic Id</span>
          </dt>
          <dd>
            <span>{{ detailsToeicUser.toeicId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.partId')">Part Id</span>
          </dt>
          <dd>
            <span>{{ detailsToeicUser.partId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.questionId')">Question Id</span>
          </dt>
          <dd>
            <span>{{ detailsToeicUser.questionId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.status')">Status</span>
          </dt>
          <dd>
            <span>{{ detailsToeicUser.status }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ detailsToeicUser.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ detailsToeicUser.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.detailsToeicUser.toeicUser')">Toeic User</span>
          </dt>
          <dd>
            <div v-if="detailsToeicUser.toeicUser">
              <router-link :to="{ name: 'ToeicUserView', params: { toeicUserId: detailsToeicUser.toeicUser.id } }">{{
                detailsToeicUser.toeicUser.id
              }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="detailsToeicUser.id"
          :to="{ name: 'DetailsToeicUserEdit', params: { detailsToeicUserId: detailsToeicUser.id } }"
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

<script lang="ts" src="./details-toeic-user-details.component.ts"></script>
