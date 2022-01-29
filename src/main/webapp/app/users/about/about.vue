<template>
  <div class="about row">
    <div class="col-md-3">
      <span class="hipster rounded"></span>
    </div>
    <div class="col-md-9">
      <h1 class="display-4" v-text="$t('home.title')">Welcome, TOEICsv!</h1>
      <p class="lead" v-text="$t('home.subtitle')">This is your homepage !!!</p>
    </div>
  </div>
</template>

<script  lang="ts" src="./about.component.ts"></script>

<style scoped>

</style>
