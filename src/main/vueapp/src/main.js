import Vue from 'vue'
import BootstrapVue from 'bootstrap-vue'
import VueRouter from 'vue-router'


import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'jquery/dist/jquery.js'
import 'popper.js/dist/popper.js'
import 'bootstrap/dist/js/bootstrap.js'

import App from './App.vue'
import { router } from './routes/router'

Vue.use(VueRouter)
Vue.use(BootstrapVue)

new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
