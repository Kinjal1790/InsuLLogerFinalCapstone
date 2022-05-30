<template>
  <div class="home">
    <div class="home-info">
      <h1>Welcome to iTracker</h1>
      <h3>Tracking your blood sugar levels just got a whole lot easier</h3>
      <p>iTracker is a multipurpose tool that helps you maintain a healthy lifestyle with diabetes. It helps you monitor your basal blood sugar level and calculate bolus insulin doses so you can focus less on diabetes, and focus more on doing what you love.</p>
      <p>The longer you use iTracker, the better it becomes. Users who regularly input their carb intake are able to view 3-day, two-week, and month-long reports that show a detailed history of their blood sugar levels and bolus insulin doses. Our warning and alert notifications will let you know when your blood sugar is out of your personal healthy range so you can take action and stay on track. Click on login to create an account and start tracking.</p>
      <button @click='displayModal()' v-if="$store.state.token == ''">Login</button>
      <button v-if="$store.state.token != ''">Input</button>
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
    displayModal() {
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
