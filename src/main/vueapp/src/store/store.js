import Vuex from 'vuex';
import Vue from 'vue';
import jwt_decode from 'jwt-decode';

Vue.use(Vuex)

export const store = new Vuex.Store({
    state: {
        isLoggedIn: !!localStorage.getItem('authorization'),
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
            localStorage.setItem('authorization', token);
            this.state.username = jwt_decode(token.split(" ")[1]).sub;
        },
        logout({commit}) {
            commit("LOGGED_OUT");
            localStorage.removeItem('authorization');
            this.state.username = '';
        }
    }
});
