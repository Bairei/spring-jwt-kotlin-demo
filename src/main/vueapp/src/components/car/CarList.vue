<template>
    <div>
        <template v-if="items.length > 0">
            <b-table striped :current-page="currentPage" :per-page="10" hover small :items="items" :show="items.length > 0"></b-table>
            <b-pagination align="center" v-model="currentPage" :total-rows="items.length" :per-page="10"></b-pagination>
        </template>
        <p v-if="items.length === 0">Loading...</p>
    </div>
</template>

<script>
import { CarService } from './carService.js';

export default {
    data() {
        return {
            items: [],
            currentPage: 1
        }
    },
    mounted() {
        CarService.getAll().then(response => {
            this.items = response.data;
        });
    }
}
</script>

<style>

</style>
