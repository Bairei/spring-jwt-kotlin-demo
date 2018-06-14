import { httpApi } from '../../http/http-api.js';
import { store } from '../../store/store.js';

export const CarService = {
    getAll(){
        return httpApi.get('/car', {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => console.error(err));
    }
};