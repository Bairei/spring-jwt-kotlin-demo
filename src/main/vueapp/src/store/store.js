import Vuex from 'vuex';
import Vue from 'vue';
import jwt_decode from 'jwt-decode';
import { router } from '../routes/router.js';

Vue.use(Vuex)

export const store = new Vuex.Store({
    state: {
        isLoggedIn: !!localStorage.getItem('authorization'),
        token: '',
        username: ''
    },
    mutations: {
        LOGGED_IN (state) {
            state.isLoggedIn = true;
        },
        LOGGED_OUT (state) {
            state.isLoggedIn = false;
        }
    },
    actions: {
        login({commit}, token) {
            commit("LOGGED_IN");
            this.state.token = token;
            localStorage.setItem('authorization', token);
            this.state.username = jwt_decode(token.split(" ")[1]).sub;
            return new Promise((resolve, reject) => {
                resolve();
            });
        },
        logout({commit}) {
            commit("LOGGED_OUT");
            this.state.token = '';
            localStorage.removeItem('authorization');
            this.state.username = '';
        }
    }
});
