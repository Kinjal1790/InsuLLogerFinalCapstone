<template>
  <div class="about">
    <div class="about-info">
      <h2>Our Features</h2>
      <p>InsuLogger’s easy-to-use features allow you to: </p>
      <ul>
        <li>Generate a bolus insulin does to correct high blood sugar levels before every meal</li>
        <li>Update your carbohydrate-to-insulin ratio and your insulin sensitivity for the most accurate bolus doses</li>
        <li>View and download a report of all your activity</li>
        <li>View averagea blood sugar levels and insulin doses over different periods of time </li>
        <li>...and most importantly, feel empowered on your diabetes journey! </li>

      </ul>
         <h2>Our Science</h2>
      <p>InsuLogger calculates your bolus insulin dose based on specific carbohydrates you expect to eat during each meal and your blood sugar reading. </p>
        <p>What is a bolus insulin dose? Bolus insulin is the medical term for the rapid-acting insulin that quickly lowers your blood sugar before you eat your meal, so the delicious carbs in your food don't raise your blood sugar too much and cause any ill side-effects or symptoms. We use your specific data such as your carb-to-insulin ratio and insulin sensitivity to calculate the most accurate bolus dose information for you.  </p>
        <h2>About Diabetes</h2>
        <h5>Who gets diabetes and why?</h5>
        <p>Ever feel alone in your journey with diabetes? </p>
        <p>Understand that your are not alone! 8% of the population lives with diabetes and new technology makes it easier than ever to live an easy and healthy lifestyle with diabetes. Diabetes occurs when insulin-producing cells in the pancreas do not function as they should. There are various types of diabetes, such as Type 1 (which occurs in childhood), Type 2 (most common in adults over 40) and gestational (pregnant women).</p>
        <h5>Factors that May Lead to a Diabetes Diagnosis</h5>
        <ul>
        <li>Family history of diabetes (Type 1)</li>
        <li>Lifestyle choices, such as consuming highly sweetened food and beverages (Type 2)</li>
        <li>Pregnancy (gestational)</li>
        </ul>
    </div>
    <div class="side-img">
      <img src="../style/5574182.jpg" alt="woman flexing her arms">
      <h5 class="socials-header"> Follow InsuLogger on your Favorite Platforms</h5>
      <div class="socials-imgs"> 
            <img id="facebook" src="../style/facebook.jpg" alt="facebook logo"> 
            <img id="instagram" src="../style/instagram.jpg" alt="instagram logo"> 
            <img id="twitter" src="../style/twitter.jpg" alt="twitter log">
      </div>
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

div {
    display: flex;
    flex-direction: column;
}
#twitter, #facebook, #instagram {
    max-width: 30px
}

.socials-imgs {
    display: flex;
    flex-direction: row;
    justify-content: space-around;
    padding: 10px;
    width: 70%;
    margin: 0 auto;
}
.social-img {
    width: 10px;
    height: auto;
}
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
    position: sticky;

  }
  .side-img img {
    width: 35vw;
  }
  .socials-header {
      text-align: center;
  }
</style>
