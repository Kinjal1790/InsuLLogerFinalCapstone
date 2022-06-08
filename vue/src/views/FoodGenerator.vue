<template>
  <div>

      <form action="carbs">
          <label for=""># of carbs</label>
          <input v-model='carbs' id='carb-amount' type="number" step="0">
      </form>
      <button v-on:click="getFood">Generate</button> <br> <br>
      <h2>{{food.name}}</h2> <br>
      <img v-bind:src="food.image">
      <p v-html="food.summary"></p>
      <ul v-for="ingredient in food.ingredients" v-bind:key="ingredient">
          <li>{{ingredient}}</li>
      </ul>
      <p v-html="food.instructions"></p>
  </div>
</template>

<script>
import FoodGenerator from '../services/FoodGeneratorService.js'

export default {
    name: 'food-generator',
    data() {
        return {
            food: {
                id : 0,
                // carbs : 0,
                name : '',
                image : '',
                summary : '',
                instructions: '',
                ingredients: []
            },
            carbs : 0
            

        }
      
    },
    methods: {
            getFood() {
                FoodGenerator.getFood(this.carbs).then(
                (response) => {
                            this.food.name = response.data.title
                            this.food.image = response.data.image
                            this.food.summary = response.data.summary
                            this.food.instructions = response.data.instructions
                            for(let i = 0; i < response.data.ingredients.length; i++) {
                                this.food.ingredients.push(response.data.ingredients[i])
                            }
                            return this.food
            })
    },
    mounted() {
        
    }

    
}
}
</script>

<style>

</style>