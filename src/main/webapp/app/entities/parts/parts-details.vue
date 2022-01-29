<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="parts">
        <h2 class="jh-entity-heading" data-cy="partsDetailsHeading">
          <span v-text="$t('finalProjectApp.parts.detail.title')">Parts</span> {{ parts.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.parts.part')">Part</span>
          </dt>
          <dd>
            <span>{{ parts.part }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.description')">Description</span>
          </dt>
          <dd>
            <span>{{ parts.description }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.example')">Example</span>
          </dt>
          <dd>
            <span>{{ parts.example }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.fileLC')">File LC</span>
          </dt>
          <dd>
            <span>{{ parts.fileLC }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.view')">View</span>
          </dt>
          <dd>
            <span>{{ parts.view }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.test')">Test</span>
          </dt>
          <dd>
            <span>{{ parts.test }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ parts.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ parts.updatedAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.parts.toeics')">Toeics</span>
          </dt>
          <dd>
            <div v-if="parts.toeics">
              <router-link :to="{ name: 'ToeicsView', params: { toeicsId: parts.toeics.id } }">{{ parts.toeics.id }}</router-link>
            </div>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link v-if="parts.id" :to="{ name: 'PartsEdit', params: { partsId: parts.id } }" custom v-slot="{ navigate }">
          <button @click="navigate" class="btn btn-primary">
            <font-awesome-icon icon="pencil-alt"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.edit')"> Edit</span>
          </button>
        </router-link>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./parts-details.component.ts"></script>
