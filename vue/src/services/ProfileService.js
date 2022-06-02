import axios from 'axios';

export default {

    submitSettings(profile) {
        return axios.post('/profile_settings', profile)
    },

    getSettings(id) {
        return axios.get(`/${id}/profile_settings`)
    },
    updateSettings(profile, id) {
        return axios.put(`/${id}/profile_settings`, profile)
    }

}