import axios from "axios";

export const httpApi = axios.create({
    baseURL: 'http://localhost:9090/api',
    headers: {
        'Content-Type': 'application/json',
    }
});