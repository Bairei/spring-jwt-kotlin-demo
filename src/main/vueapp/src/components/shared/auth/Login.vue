<template>
    <div>
        <b-alert :show="isLoginInvalid" dismissible variant="danger">
            Unable to log in, make sure to insert correct password, or if your username exists in database.
            <br>
            Don't have an account? <router-link to="/register">Click here</router-link> to register!
        </b-alert>    
        <b-form @submit.prevent="onSubmit" class="col-sm-4 offset-sm-4">
            <b-form-group label="Username"
                        label-for="form.username"
                        description="Enter your username">
                <b-form-input v-model="form.username" :class="{'is-invalid': isLoginInvalid}" type="text" required></b-form-input>
            </b-form-group>
            <b-form-group label="Password"
                    label-for="form.password"
                    description="Enter your password">
                <b-form-input v-model="form.password" :class="{'is-invalid': isLoginInvalid}" type="password" required></b-form-input>
            </b-form-group>

            <b-button type="submit" variant="primary">Submit</b-button>
            <b-button type="reset" @click="onReset" variant="warning">Reset</b-button>
        </b-form>
    </div>
</template>

<script>
import { AuthService } from './authService.js';

export default {
    data() {
        return {
            form: {
                username: '',
                password: ''
            },
            isLoginInvalid: false
        }
    },
    methods: {
        onSubmit(event) {
            AuthService.login(this.form).then(token => {               
                this.$store.dispatch('login', token).then(() => {
                    const redirect = this.$router.history.current.query.redirect;
                    this.$router.push({path: redirect ? redirect : '/'});
                });
            }).catch(err => {
                console.error(err);
                this.isLoginInvalid = true;
                this.onReset();
            });
        },
        onReset() {
            this.form.username = '';
            this.form.password = '';
        }
    }
}
</script>

<style>

</style>
