import axios from 'axios';

export default {

    submitSettings(profile) {
        return axios.post('/profile_settings', profile)
    }

}