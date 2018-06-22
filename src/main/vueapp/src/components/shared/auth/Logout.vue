<template>
    <p>Logging out...</p>
</template>

<script>
export default {
    created(){
        this.$store.dispatch('logout').then(() => {
            this.$router.push({name: 'home', params: {isLoggedOut: true}})
        })
    }
}
</script>

<style>

</style>
