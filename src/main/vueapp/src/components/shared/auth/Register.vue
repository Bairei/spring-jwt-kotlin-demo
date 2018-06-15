<template>

<div>
    <b-alert :show="isFormInvalid" dismissible variant="danger">Your form is not valid! Please make sure to check, if all input is valid!</b-alert>
    <b-alert :show="isServerNotResponding" dismissible variant="danger">
        Server is not responding.
        <br>
        Error message: 
        <br>
        <em>{{error}}</em>
    </b-alert>
    <b-form @submit.prevent="onSubmit">
        <b-form-group label="Username"
                    label-for="form.username"
                    description="Enter your username"
        >
            <b-form-input v-model="form.username" type="text" required
                :class="{'is-invalid': isFormInvalid && form.username === ''}"></b-form-input>
        </b-form-group>

        <b-form-group label="Password"
                label-for="form.password"
                description="Enter your password"
        >
            <b-form-input v-model="form.password" type="password" required
                        :class="{'is-invalid': isFormInvalid && form.password !== confirmPassword}"></b-form-input>
        </b-form-group>
        
        <b-form-group label="Confirm password"
                label-for="confirmPassword"
                description="Confirm your password"
        >
            <b-form-input v-model="confirmPassword" type="password" required
                        :class="{'is-invalid': isFormInvalid && form.password !== confirmPassword}"></b-form-input>
        </b-form-group>

        <b-form-group label="Name"
                label-for="form.firstName"
                description="First name">
            <b-form-input v-model="form.firstName" type="text"></b-form-input>
        </b-form-group>

        <b-form-group label="Surname"
                label-for="form.lastName"
                description="Last name">
            <b-form-input v-model="form.lastName" type="text"></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" @click="onReset" variant="warning">Reset</b-button>
    </b-form>

</div>

</template>

<script>

import { AuthService } from './authService.js';
import { EventBus } from '../../../event-bus.js';

export default {
    data() {
        return {
            form: {
                username: '',
                password: '',
                firstName: '',
                lastName: ''
            },
            confirmPassword: '',
            isFormInvalid: false,
            isServerNotResponding: false,
            error: ''
        }
    },
    methods: {
        onSubmit(event) {
            this.isFormInvalid = false;
            if (!this.isValid()) {
                this.isFormInvalid = true;
                return;
            }
            AuthService.register(this.form).then(() => {
                this.$router.push({name: 'home', params: {isRegistered: true}});
            }).catch(err => {
                this.isServerNotResponding = true;
                this.error = `${err}`;
                this.onReset();
            });
        },
        onReset() {
            this.form.username = '';
            this.form.password = '';
        },
        isValid() {
            return this.form.username !== "" && this.form.password !== "" 
            && this.confirmPassword !== "" && this.confirmPassword === this.form.password
        }
    }
}
</script>

<style>

</style>
