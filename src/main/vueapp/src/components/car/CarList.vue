<template>
    <div>
        <template v-if="items.length > 0">
            <b-table striped :fields="fields" :current-page="currentPage" :per-page="10" hover small :items="items" :show="items.length > 0">
                <template slot="actions" slot-scope="data">
                    <b-btn size="sm" :to="'/car/' + data.item.id" variant="info">Details</b-btn>
                </template>
            </b-table>
            <b-pagination align="center" v-model="currentPage" :total-rows="items.length" :per-page="10"></b-pagination>
        </template>
        <p v-if="items.length === 0">Loading...</p>
    </div>
</template>

<script>
import { CarService } from './carService.js'

export default {
    data() {
        return {
            fields: ['id', 'make','model','horsePower', 'yearOfProduction', 'actions'],
            items: [],
            currentPage: 1
        }
    },
    mounted() {
        CarService.getAll().then(response => {
            this.items = response.data
        }).catch(err => {
            console.error(err)
            this.$router.push({path: '/'})
        })
    }
}
</script>

<style>

</style>
