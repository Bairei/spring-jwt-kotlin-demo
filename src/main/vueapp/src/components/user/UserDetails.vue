<template>
    <div>
        <template v-if="!isLoaded">Loading...</template>
        <template v-else>
            <template v-if="user.username">
                <h3>{{user.username || '(unknown)'}} details: </h3>
                <p>User: {{user.username}}</p>
                <p>Name: {{user.firstName}}</p>
                <p>Surname: {{user.lastName}}</p>
            </template>
            <template v-else>
                <p>No info about {{username}} available.</p>
            </template>
            </template>
    </div>
</template>

<script>
import {httpApi} from '../../http/http-api.js'
export default {
    data() {
        return {
            username: '',
            user: {},
            isLoaded: false
        }
    },
    computed: {
        currentUser(){
            return this.$store.state.username
        }
    },
    watch: {
        currentUser(val) {
            if (!this.$route.params.username) {
                this.username = val
            }
        },
        username() {
            this.getUser()
        }
    },
    mounted() {
        this.username = this.$route.params.username || this.currentUser
    },
    methods: {
        getUser() {
            httpApi.get(`/user/${this.username}`, {
                headers: {
                    'Authorization': localStorage.getItem('authorization')
                }
            }).then(response => {
                this.user = response.data
                this.isLoaded = true
            }).catch(err => {
                console.error(err)
                this.isLoaded = true
            })
        }
    }
}
</script>

<style>

</style>
