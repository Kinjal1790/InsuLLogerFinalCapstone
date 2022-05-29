<template>
  <div class="home">
    <div class="home-info">
      <h1>Welcome to iTracker</h1>
      <h3>Insulin Tracker designed to make your life easier</h3>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Possimus quae eveniet tenetur voluptate impedit iusto ipsa blanditiis officiis dolorum numquam porro doloremque culpa recusandae, esse vitae omnis aliquam! Perspiciatis, tempore?</p>
      <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloribus consequuntur reiciendis commodi iste ipsam aliquam in est, eum libero pariatur dolores alias dolorum facilis, quis accusantium facere eaque perspiciatis officiis sit blanditiis voluptates explicabo! Nulla impedit blanditiis assumenda quo fuga sequi magnam nam voluptatum iure repudiandae odio, dolorum dolor reiciendis?</p>
      <button @click='displayModal()'>Login</button>
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
    display: grid;
    grid-template-columns: 50% 50%;
  }
  .btn-danger {
    color: black;
  }
  .side-img img {
    width: 35vw;
  }
</style>
