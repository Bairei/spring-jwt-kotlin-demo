<template>
    <b-form @submit.prevent="onSubmit">
        <b-form-group label="Make"
                    label-for="form.make"
                    description="Car make"
        >
            <b-form-input v-model="form.make" type="text" required></b-form-input>
        </b-form-group>

        <b-form-group label="Model"
                label-for="form.Model"
                description="Car model">
            <b-form-input v-model="form.model" type="text"></b-form-input>
        </b-form-group>

        <b-form-group label="Year of Production"
                label-for="form.yearOfProduction"
                description="Year of Production">
            <b-form-input v-model="form.yearOfProduction" type="number" min="1900" max="2018"></b-form-input>
        </b-form-group>

        <b-form-group label="Horse Power"
                label-for="form.horsePower"
                description="Horse Power">
            <b-form-input v-model="form.horsePower" type="number" min="0"></b-form-input>
        </b-form-group>

        <b-button type="submit" variant="primary">Submit</b-button>
        <b-button type="reset" @click="onReset" variant="warning">Reset</b-button>
    </b-form>
</template>

<script>
import { CarService } from './carService.js'
export default {
    data() {
        return {
            id: this.$route.params.id || null,
            form: {
                make: '',
                model: '',
                horsePower: '',
                yearOfProduction: ''
            }
        }
    },
    mounted(){
        if (this.id) {
            CarService.getOne(this.id).then(response => {
                this.form = response.data
            }).catch(err => this.$router.push({path: '/car'}))
        }
    },
    methods: {
        onReset() {
            this.form.make = ''
            this.form.model = ''
            this.form.horsePower = 0
            this.form.yearOfProduction = 0
        },
        onSubmit() {
            if (this.id) {
                CarService.updateCar(this.form, this.id).then(response => {
                    this.$router.push({path: '/car'})
                }).catch(err => {
                    console.error(err)
                })
            } else {
                CarService.postCar(this.form).then(response => {
                    this.$router.push({path: '/car'})
                }).catch(err => {
                    console.error(err)
                })
            }
        }
    }
}
</script>

<style>

</style>
