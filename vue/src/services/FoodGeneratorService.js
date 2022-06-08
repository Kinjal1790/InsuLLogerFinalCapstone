import axios from 'axios';

export default {
    

    getFood(carbs) {
        return axios.get(`${carbs}/food`)
    },
    getFoodInfo(id) {
        return axios.get(`https://api.spoonacular.com/recipes/${id}/information`)
    }
}