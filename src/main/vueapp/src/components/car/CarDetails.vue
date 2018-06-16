<template>
    <div>
        <p v-if="!car.make">Loading...</p>
        <template v-else>
            <b-row>
                <b-col sm="2">ID:</b-col>
                <b-col sm="2">{{car.id}}</b-col>
            </b-row>
            <b-row>
                <b-col sm="2">Make:</b-col>
                <b-col sm="2">{{car.make}}</b-col>
            </b-row>
            <b-row>
                <b-col sm="2">Model:</b-col>
                <b-col sm="2">{{car.model}}</b-col>
            </b-row>
            <b-row>
                <b-col sm="2">Year of Production:</b-col>
                <b-col sm="2">{{car.yearOfProduction}}</b-col>
            </b-row>
            <b-row>
                <b-col sm="2">Horse Power:</b-col>
                <b-col sm="2">{{car.horsePower}}</b-col>
            </b-row>
            <br>
            <b-col sm="6">
                <b-alert :show="isDeletePressed" variant="danger">
                    Are you sure you want to delete this item? Press the button again to confirm.
                </b-alert>
                <b-button @click="onDelete" variant="danger">Delete</b-button>
                <b-button @click="onEdit" variant="info">Edit</b-button>
            </b-col>
        </template>
    </div>
</template>

<script>
import { CarService } from './carService.js'
export default {
    data() {
        return {
            car: {
                id: Number,
                make: '',
                model: '',
                yearOfProduction: 0,
                horsePower: 0
            },
            isDeletePressed: false
        }
    },
    mounted() {
        this.car.id = this.$router.history.current.params.id;
        CarService.getOne(this.car.id).then((response) => {
            this.car = response.data;
        }).catch(err => {
            this.$router.push({path: '/car'});
        });
    },
    methods: {
        onDelete() {
            if (!this.isDeletePressed) {
                this.isDeletePressed = true;
                return;
            } else {
                CarService.deleteById(this.car.id).then(response => {
                    this.$router.push({path: '/car'});
                })
            }
        },
        onEdit() {
            this.$router.push({name: 'car-edit', params:{id: this.car.id}});
        }
    }
}
</script>

<style>

</style>
