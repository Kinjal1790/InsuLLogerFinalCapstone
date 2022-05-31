import axios from 'axios';

export default {

    submitSettings(profile) {
        return axios.post('/profile-settings', profile)
    }

}