<template>
<b-navbar toggleable="md" type="dark" variant="dark">
  <b-navbar-brand to="/">NavBar</b-navbar-brand>  

  <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

  <b-collapse is-nav id="nav_collapse">

    <b-navbar-nav>
      <b-nav-item to="/" exact>Home</b-nav-item>
      <template v-if="isLoggedIn">
        <b-nav-item-dropdown right text="Car">
          <!-- <b-dropdown-header>Hello</b-dropdown-header> -->
          <b-dropdown-item to="/car">List</b-dropdown-item>
          <b-dropdown-item to="/car/create">Create a Car</b-dropdown-item>
        </b-nav-item-dropdown>
        <b-nav-item v-if="$store.state.role === 'ROLE_ADMIN'">Admin page</b-nav-item>
        <b-nav-item v-else-if="$store.state.role === 'ROLE_USER'">User page</b-nav-item>
      </template>
    </b-navbar-nav>

    <!-- Right aligned nav items -->
    <b-navbar-nav class="ml-auto">
        
        <template v-if="!isLoggedIn">
          <b-nav-item to="/login">Log in</b-nav-item>
          <b-nav-item to="/register">Register</b-nav-item>
        </template>
        <template v-else>
          <b-nav-text>Welcome, {{user}}!</b-nav-text>
          <b-nav-item to="/logout">Log out</b-nav-item>
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
  }
}
</script>

<style scoped>

</style>
