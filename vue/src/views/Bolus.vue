<template>
  <div class="bolus">
       <div class="side-img">
      <img src="../style/carbs.jpg" alt="a woman measuring her blood sugar level">
    </div>
    <div class="bolus-form">
      <form>
          
          <input type="text">
          <input type="text">
          <input type="text">
          <button type="submit">Submit</button>
      </form>
    </div>
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
        display: flex;
        flex-direction: column;
        justify-content: center;
        gap: 1em;
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