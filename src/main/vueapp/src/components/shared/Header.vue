<template>
<b-navbar toggleable="md" type="dark" variant="dark">
  <b-navbar-brand to="/">NavBar</b-navbar-brand>  

  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

  <b-collapse is-nav id="nav_collapse">

    <b-navbar-nav>
      <b-nav-item to="/" exact>Link</b-nav-item>
    </b-navbar-nav>

    <!-- Right aligned nav items -->
    <b-navbar-nav class="ml-auto">
        
        <template v-if="!isLoggedIn">
          <b-nav-item to="/login">Log in</b-nav-item>
          <b-nav-item to="/register">Register</b-nav-item>
        </template>
        <template v-else>
          <b-nav-text>Welcome, {{user}}!</b-nav-text>
          <b-nav-item @click.prevent="logout()">Log out</b-nav-item>
        </template>
        
    </b-navbar-nav>

  </b-collapse>
</b-navbar>

</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.isLoggedIn;
    },
    user() {
      return this.$store.state.username
    }
  },
  methods: {
    logout() {
      this.$store.dispatch('logout');
    }
  }
}
</script>

<style scoped>

</style>
