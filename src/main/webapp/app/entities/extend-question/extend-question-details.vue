<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <div v-if="extendQuestion">
        <h2 class="jh-entity-heading" data-cy="extendQuestionDetailsHeading">
          <span v-text="$t('finalProjectApp.extendQuestion.detail.title')">ExtendQuestion</span> {{ extendQuestion.id }}
        </h2>
        <dl class="row jh-entity-details">
          <dt>
            <span v-text="$t('finalProjectApp.extendQuestion.paragraph')">Paragraph</span>
          </dt>
          <dd>
            <span>{{ extendQuestion.paragraph }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.extendQuestion.image')">Image</span>
          </dt>
          <dd>
            <div v-if="extendQuestion.image">
              <a v-on:click="openFile(extendQuestion.imageContentType, extendQuestion.image)">
                <img
                  v-bind:src="'data:' + extendQuestion.imageContentType + ';base64,' + extendQuestion.image"
                  style="max-width: 100%"
                  alt="extendQuestion image"
                />
              </a>
              {{ extendQuestion.imageContentType }}, {{ byteSize(extendQuestion.image) }}
            </div>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.extendQuestion.createdAt')">Created At</span>
          </dt>
          <dd>
            <span>{{ extendQuestion.createdAt }}</span>
          </dd>
          <dt>
            <span v-text="$t('finalProjectApp.extendQuestion.updatedAt')">Updated At</span>
          </dt>
          <dd>
            <span>{{ extendQuestion.updatedAt }}</span>
          </dd>
        </dl>
        <button type="submit" v-on:click.prevent="previousState()" class="btn btn-info" data-cy="entityDetailsBackButton">
          <font-awesome-icon icon="arrow-left"></font-awesome-icon>&nbsp;<span v-text="$t('entity.action.back')"> Back</span>
        </button>
        <router-link
          v-if="extendQuestion.id"
          :to="{ name: 'ExtendQuestionEdit', params: { extendQuestionId: extendQuestion.id } }"
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

<script lang="ts" src="./extend-question-details.component.ts"></script>
