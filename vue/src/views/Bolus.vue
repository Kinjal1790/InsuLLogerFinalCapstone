<template>
  <div class="bolus">
       <div class="side-img">
      <img src="../style/carbs.jpg" alt="a woman measuring her blood sugar level">
    </div>
      <form class="bolus-form"  @submit.prevent='getBolus'>
          <label for="carb-intake">Carbs intake (g):</label>
          <input v-model='readings.carbs' id='carb-intake' type="number" step="0.01" placeholder="Carbs">
          <label for="bl-sugar-reading">Blood sugar reading (mg/dL):</label>
          <input v-model='readings.currentBloodSugar' id='bl-sugar-reading' type="number" placeholder="Current blood sugar level">
          
          <button type="submit">Submit</button>
      </form>
  </div>
</template>

<script>
export default {
    name: 'bolus',
    data() {
        return {
            readings: {
                userId: this.$store.state.user.id,
                datetime: '',
                carbs: '',
                currentBloodSugar: ''
            },
        }
    },
    methods: {
        getBolus() {
            var today = new Date();
            var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            var time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
            var dateTime = date+' '+time;
            this.readings.datetime = dateTime;
            console.log(this.readings);
        }
    }
}
</script>

<style>
    .bolus {
        padding-top: 5em;
        display: grid;
        grid-template-columns: 50% 50%;
    }
    .bolus-form {
        margin-left: 2em;
        display: flex;
        flex-direction: column;
        justify-content: center;
        gap: 1em;
        justify-content: center;
    }
    button {
        border-radius: 85px;
    }
    .side-img {
        margin: 0 auto;
    }
    .side-img img {
        width: 35vw;
    }
</style>