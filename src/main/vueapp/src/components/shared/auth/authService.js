import axios from 'axios';
import { httpApi } from '../../../http/http-api.js';


export const AuthService = {
    login(form) {
        return httpApi.post('/login', form, {
            withCredentials: true
        }).then(response => {
            return response.headers['authorization'];
        }).catch(err => { throw err });
    },
    register(form) {
        return httpApi.post('/users/signup', form).then(response => {
            console.log(response);
            console.log('Successfully registered');
        }).catch(err => console.error(err));
    }
}