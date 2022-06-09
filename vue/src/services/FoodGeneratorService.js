import axios from 'axios';

export default {
    

    getFood(carbs) {
        return axios.get(`${carbs}/food`)
    },

}