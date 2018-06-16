import Vuex from 'vuex';
import Vue from 'vue';
import jwt_decode from 'jwt-decode';

Vue.use(Vuex)

export const store = new Vuex.Store({
    state: {
        isLoggedIn: !!localStorage.getItem('authorization'),
        username: '',
        role: ROLE_NONE
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
            const decodedToken = jwt_decode(token.split(" ")[1]);
            this.state.username = decodedToken.sub;
            this.state.role = decodedToken.roles[0] || ROLE_NONE;
            return new Promise((resolve, reject) => {
                resolve();
            });
        },
        logout({commit}) {
            commit("LOGGED_OUT");
            this.state.token = '';
            localStorage.removeItem('authorization');
            this.state.username = '';
            this.state.role = ROLE_NONE;
        }
    }
});

const ROLE_USER = 'ROLE_USER';
const ROLE_ADMIN = 'ROLE_ADMIN';
const ROLE_NONE = 'ROLE_NONE';