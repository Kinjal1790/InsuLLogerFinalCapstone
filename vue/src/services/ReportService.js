import axios from 'axios';

export default {
    getReport(reportFilterData) {
        return axios.post('/${id}/report', reportFilterData)
    }
}