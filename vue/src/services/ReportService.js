import axios from 'axios';

export default {
    getReport(reportFilterData, id) {
        return axios.post(`/${id}/report`, reportFilterData)
    }
}