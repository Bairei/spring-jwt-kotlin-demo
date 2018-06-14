<template>
    <b-form @submit.prevent="onSubmit" class="col-sm-4 offset-sm-4">
        <b-form-group label="Username"
                    label-for="form.username"
                    description="Enter your username">
            <b-form-input v-model="form.username" type="text" required></b-form-input>
        </b-form-group>
        <b-form-group label="Password"
                label-for="form.password"
                description="Enter your password">
            <b-form-input v-model="form.password" type="password" required></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" @click="onReset" variant="warning">Reset</b-button>
    </b-form>
</template>

<script>
import { authService } from './authService.js';

export default {
    data() {
        return {
            form: {
                username: '',
                password: ''
            }
        }
    },
    methods: {
        onSubmit(event) {
            authService.login(this.form).then(response => {
                console.log(response);
                this.$router.push({path: '/'});
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
