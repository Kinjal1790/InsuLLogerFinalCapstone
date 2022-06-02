import axios from 'axios';

export default {
    sendReadings(readings) {
        return axios.post('/bolus', readings)
    }

}
