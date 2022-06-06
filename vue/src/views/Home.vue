<template>
  <div class="home">
    <div class="home-info">
      <h1>Welcome to iTracker</h1>
      <h3>Tracking your blood sugar levels just got a whole lot easier</h3>
      <p>InsuLogger is a multipurpose tool that helps you maintain a healthy lifestyle with diabetes. 
        With InsuLogger’s easy-to-use features, you can monitor your basal blood sugar level and calculate bolus insulin doses so you can worry less about your blood sugar, and focus more on doing what you love. </p>
      <p>The longer you use InsuLogger, the better it becomes. Users who regularly input their carbohydrate (carb) intake are able to view 3-day, two-week, and monthly reports that show averages of their  blood sugar and bolus insulin doses compared to target ranges. Our warning and alert notifications will let you know when your blood sugar is out of healthy range so you can take action and stay on track. Click on the login page to create an account and start tracking!</p>
      <button @click='displayLogin()' v-if="$store.state.token == ''">Login</button>
      <button v-if="$store.state.token != ''" @click='$router.push("/bolus")'>Generate Bolus</button>
    </div>
    <div class="side-img">
      <img src="../style/4435922.jpg" alt="a woman measuring her blood sugar level">
    </div>
    
  </div>
</template>

<script>
export default {
  name: "home",
  methods: {
    displayLogin() {
      this.$router.push('/login')
    }
  }
};
</script>

<style scoped>
  .home {
    padding-top: 5em;
    display: grid;
    grid-template-columns: 50% 50%;
  }
  .home-info {
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
