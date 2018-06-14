import axios from 'axios';
import { httpApi } from '../../../http/http-api.js';


export const authService = {
    login(form) {
        return httpApi.post('/login', form, {
            withCredentials: true
        }).then(response => {
            // console.log(response.headers['authorization']);
            return response.headers['authorization'];
        }).catch(err => console.error(err));
    },
    register(form) {
        return httpApi.post('/users/signup', form).then(response => {
            console.log(response);
            console.log('Successfully registered');
        }).catch(err => console.error(err));
    }
}