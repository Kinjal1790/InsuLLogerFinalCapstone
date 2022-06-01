import axios from 'axios';

export default {

    submitSettings(profile) {
        return axios.post('/profile_settings', profile)
    },

    getSettings(id) {
        return axios.get(`/${id}/profile_settings`)
    }

}