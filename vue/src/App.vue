<style src='@/style/insulin_style.css'>

</style>
<template>
  <div id="app" class='container'>
    <div id="nav">
      <div class="logo">
        <img src="@/style/Blood_drop_plain.svg.png" alt="logo">
        <h2>iTracker</h2>
      </div>
      <nav>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            <a v-if="$store.state.token == ''">About</a>
            <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
            
            <a  v-if="$store.state.token != ''">Readings</a>
            <a  v-if="$store.state.token != ''">Reports</a>
            <a  v-if="$store.state.token != ''">Input</a>
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </nav>

      
    </div>
    <router-view />
  </div>
</template>

<style scoped>

</style>