import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'jquery/dist/jquery.js'
import 'popper.js/dist/popper.js'
import 'bootstrap/dist/js/bootstrap.js'

import App from './App.vue'
import { router } from './routes/router'
import { store } from './store/store.js'

Vue.use(VueRouter)
Vue.use(BootstrapVue)

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})

const token = localStorage.getItem('authorization')
if (token && token.split(" ").length > 1) {
  store.dispatch('login', token)
}