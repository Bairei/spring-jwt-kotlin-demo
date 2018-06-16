import { httpApi } from '../../http/http-api.js';

export const CarService = {
    getAll(){
        return httpApi.get('/car', {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => {throw err});
    },
    getOne(id){
        return httpApi.get(`/car/${id}`, {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => { throw err });
    },
    deleteById(id){
        return httpApi.delete(`/car/${id}`, {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => {throw err});
    },
    updateCar(car, id){
        return httpApi.put(`/car/${id}`, car, {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => {throw err});
    },
    postCar(car){
        return httpApi.post('/car', car, {
            headers: {
                'Authorization': localStorage.getItem('authorization')
            }
        }).catch(err => {throw err});
    },
};