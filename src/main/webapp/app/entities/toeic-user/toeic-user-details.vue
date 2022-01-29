<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="toeicUser">
        <h2 class="jh-entity-heading" data-cy="toeicUserDetailsHeading">
          <span v-text="$t('finalProjectApp.toeicUser.detail.title')">ToeicUser</span> {{ toeicUser.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.userId')">User Id</span>
          </dt>
          <dd>
            <span>{{ toeicUser.userId }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.score')">Score</span>
          </dt>
          <dd>
            <span>{{ toeicUser.score }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.reading')">Reading</span>
          </dt>
          <dd>
            <span>{{ toeicUser.reading }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.listening')">Listening</span>
          </dt>
          <dd>
            <span>{{ toeicUser.listening }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ toeicUser.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ toeicUser.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.toeicUser.toeics')">Toeics</span>
          </dt>
          <dd>
            <div v-if="toeicUser.toeics">
              <router-link :to="{ name: 'ToeicsView', params: { toeicsId: toeicUser.toeics.id } }">{{ toeicUser.toeics.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="toeicUser.id"
          :to="{ name: 'ToeicUserEdit', params: { toeicUserId: toeicUser.id } }"
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

<script lang="ts" src="./toeic-user-details.component.ts"></script>
