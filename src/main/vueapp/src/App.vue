<template>
  <div class="container">
    <app-header></app-header>

    <div class="jumbotron">
      <router-view></router-view>
    </div>

  </div>
</template>
<script>
import Header from './components/shared/Header.vue';

export default {
  name: 'app',
  components: {
    appHeader: Header
  }
}
</script>

<style>

</style>
