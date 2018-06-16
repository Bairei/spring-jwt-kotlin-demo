import VueRouter from 'vue-router'

import Home from '../components/shared/Home.vue'
import Register from '../components/shared/auth/Register.vue'
import Login from '../components/shared/auth/Login.vue'
import Car from '../components/car/Car.vue'
import CarList from '../components/car/CarList.vue'
import Logout from '../components/shared/auth/Logout.vue'
import CarDetails from '../components/car/CarDetails.vue'
import CarForm from '../components/car/CarForm.vue'
import { store } from '../store/store.js'

export const router = new VueRouter({
    routes: [
        {
            name: 'home',
            path: '/',
            component: Home,
            props: true
        },
        {
            path: '/register',
            component: Register,
            meta: {
                cannotBeLoggedIn: true
            }
        },
        {
            path: '/login',
            component: Login,
            meta: {
                cannotBeLoggedIn: true
            }
        },
        {
            path: '/logout',
            name: 'logout',
            component: Logout,
            meta: {
                requiresAuth: true
            }
        },
        {
            path: '/car',
            component: Car,
            meta: {
                requiresAuth: true
            },
            children: [
                {
                    path: 'create',
                    component: CarForm,
                    name: 'car-create'
                },
                {
                    path: ':id/edit',
                    component: CarForm,
                    name: 'car-edit',
                    props: true
                },
                {
                    path: ':id',
                    component: CarDetails
                },
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
        if (!store.state.isLoggedIn && to.name === 'logout') {
            next({
                name: 'home'
            })
            return
        } else if (!store.state.isLoggedIn) {
            next({
                path: '/login',
                query: {redirect: to.fullPath}
            })
            return
        }
        next()
    } 
    if (to.matched.some(record => record.meta.cannotBeLoggedIn)) {
        if (store.state.isLoggedIn) {
            next({
                name: 'home'
            })
            return
        }
        next()
    }
    next()
    
})