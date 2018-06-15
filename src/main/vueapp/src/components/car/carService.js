import { httpApi } from '../../http/http-api.js';

export const CarService = {
    getAll(){
        return httpApi.get('/car', {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => console.error(err));
    },
    getOne(id){
        return httpApi.get(`/car/${id}`, {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => console.error(err));
    },
    deleteById(id){
        return httpApi.delete(`/car/${id}`, {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => {throw err});
    }
};