<template>
  <div class="about">
    <div class="about-info">
      <h2>Our Features</h2>
      <p>InsuLogger’s easy-to-use features allow you to: </p>
      <ul>
        <li>Generate a bolus insulin does to correct high blood sugar levels before every meal</li>
        <li>Update your carbohydrate-to-insulin ratio and your insulin sensitivity for the most accurate bolus doses</li>
        <li>View and download a report of all your activity</li>
        <li>Generate averages of your month </li>

      </ul>
         <h2>Our Science</h2>
      <p>InsuLogger calculates your bolus insulin dose based on specific carbohydrates you expect to eat during each meal and your blood sugar reading. </p>
        <p>What is a bolus insulin dose? Bolus insulin is the medical term for the rapid-acting insulin that quickly lowers your blood sugar before you eat your meal, so the delicious carbs in your food don't raise your blood sugar too much and cause any ill side-effects or symptoms.  </p>
        <h2>About Diabetes</h2>
        <h5>Who gets diabetes and why?</h5>
        <p>Ever feel alone in your journey with diabetes? </p>
        <p>Understand that your are not alone! 8% of the population lives with diabetes and new technology makes it easier than ever to live an easy and healthy lifestyle with diabetes.</p>
        <h5>Factors that May Lead to a Diabetes Diagnosis</h5>
        <ul>
        <li>Family history of diabetes</li>
        <li>Lifestyle choices, such as consuming highly sweetened food and beverages</li>
        <li>Pregnancy</li>
        </ul>
    </div>
    <div class="side-img">
      <img src="../style/5574182.jpg" alt="woman flexing her arms">
    </div>
    
  </div>
</template>

<script>
export default {
  name: "home",
  methods: {
    displayHome() {
      this.$router.push('/home')
    }
  }
};
</script>

<style scoped>
  .about {
    padding-top: 5em;
    display: grid;
    grid-template-columns: 50% 50%;
  }
  .about-info {
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
