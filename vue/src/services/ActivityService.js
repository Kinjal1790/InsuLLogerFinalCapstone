import axios from 'axios';

export default {

    getActivity(id) {
        return axios.get(`/${id}/activity`)
    },
    getAdminHistory() {
        return axios.get('/activity')
    }

}