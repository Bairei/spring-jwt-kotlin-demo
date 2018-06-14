import VueRouter from 'vue-router'

import Home from '../components/shared/Home.vue'
import Register from '../components/shared/auth/Register.vue'
import Login from '../components/shared/auth/Login.vue'
import Car from '../components/car/Car.vue'
import CarList from '../components/car/CarList.vue'
import { store } from '../store/store.js'

export const router = new VueRouter({
    routes: [
        {
            // name: 'Home',
            path: '/',
            component: Home
        },
        {
            path: '/register',
            component: Register
        },
        {
            path: '/login',
            component: Login
        },
        {
            path: '/car',
            component: Car,
            meta: {
                requiresAuth: true
            },
            children: [
                {
                    path: '',
                    component: CarList
                }
            ]
        }

    ], mode: 'history'
})

router.beforeEach((to, from, next) => {
    if (to.matched.some(record => record.meta.requiresAuth)) {
        // if path has meta data containing requiresAuth, check if user is logged in
        if (!store.state.isLoggedIn) {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
            return
        }
    }
    next()
    
})