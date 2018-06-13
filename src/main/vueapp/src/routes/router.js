import VueRouter from "vue-router";
import Home from '../components/shared/Home.vue'
import Register from '../components/shared/auth/Register.vue'
import Login from '../components/shared/auth/Login.vue'

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
        }

    ]
})