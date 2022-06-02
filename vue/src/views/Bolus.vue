<template>
  <div class="bolus">
       <div class="side-img">
      <img src="../style/carbs.jpg" alt="a woman measuring her blood sugar level">
    </div>
      <form class="bolus-form">
          <label for="carb-intake">Carb intake:</label>
          <input id='carb-intake' type="number" step="0.01">
          <label for="bl-sugar-reading">Blood sugar reading:</label>
          <input id='bl-sugar-reading' type="number" step="0.01">
          
          <button type="submit">Submit</button>
      </form>
  </div>
</template>

<script>
export default {

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