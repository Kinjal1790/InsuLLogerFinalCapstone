<template>
  <div :class="{'food-wrapper': hasData}">
      <h1>Recipe Generator</h1>
    <div class='food-section'>
        
        <form action="carbs" class='food-form'>
            <label for=""># of carbs</label>
            <input v-model='carbs' id='carb-amount' type="number" step="0">
        </form>
        <button id="generate-recipe-btn" v-on:click="getFood">Generate</button>
        <h2 class='recipe-title' v-if="food.instructions != ''">{{food.name}}</h2>
        <img class='food-img' v-bind:src="food.image" v-if="food.instructions != ''">
        <h3 class='food-header' v-if="food.instructions != ''" >Recipe Info</h3>
        <p v-html="food.summary" v-if="food.instructions != ''"></p>
        <h3 class='food-header' v-if="food.instructions != ''" >Ingredients</h3>

        <b-list-group v-for="ingredient in food.ingredients" v-bind:key="ingredient"  >
            <b-list-group-item>{{ingredient}}</b-list-group-item>
        </b-list-group>
        <h3 class='food-header' v-if="food.instructions != ''">Instructions</h3>
        <p v-html="food.instructions"></p>
    </div>

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
            carbs : 0,
            hasData: true

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
                            if (this.hasData == true) {
                                this.hasData = !this.hasData
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
    h1 {
        text-align: center;
    }
    .food-section p a {
        color: #458fa5;
    }
    .food-section p a:hover {
        color: #385a64;
    }
    .food-header {
        text-align: center;
    }
    .list-group-item:hover {
        background-color: rgb(235, 236, 236);
    }
     .recipe-title {
        margin: 0 auto;
    }
    
    .food-section > p {
        width: 80%;
        margin: 0 auto;
        font-size: 1.1em;
        line-height: 2em;
    }
    .food-img {
        max-width: 40%;
        margin: 0 auto;
        border-radius: 35px;
    }
    .food-section > * {
        padding: 1em;
    }
    .food-section {
        display: flex;
        justify-content: center;
        flex-direction: column;
        background-color: rgba(255, 255, 255, 0.295);
    }
    .list-group {
        width: 40%;
        margin: 0 auto;
        padding: 0
    }
    #generate-recipe-btn {
        border-radius: 35px;
        margin: 0 auto;
    }
    .food-form > input{
        padding: 0.4em;
        border-radius: 7px;
        border: 1px solid grey;
        margin-left: 1em;
    }
    .food-form {
        width: 50%;
        display: flex;
        justify-content: center;
        margin: 0 auto;
        align-items: center;
        background-color: white;
    }
    .food-wrapper {
        background-image: url("../style/recipe.jpg");
        background-position-y: 150px;
        background-position-x: 150px;
        background-size: 80% auto;
        background-repeat: no-repeat;
        height: 100vh;
    }
</style>