<style src='@/style/insulin_style.css'>

</style>
<template>
  <div id="app" class='container'>
    <div id="nav">
      <div class="logo">
        <img src="@/style/Blood_drop_plain.svg.png" alt="logo">
        <h2 class='company-name'>iTracker</h2>
      </div>
      <nav>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            <a v-if="$store.state.token == ''">About</a>
            <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
            
            <a  v-if="$store.state.token != ''">Readings</a>
            <a  v-if="$store.state.token != ''">Reports</a>
            <!-- <router-link v-bind:to='{name: "profile_settings"}'  v-if="$store.state.token != '' && !$store.state.profileSettings.userId">Profile Settings</router-link> -->
            <router-link v-bind:to="{name: 'edit_settings', params: {id: $store.state.user.id}}"  v-if="$store.state.token != ''">Profile Settings</router-link>
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Logout</router-link>
      </nav> 
    </div>
    <router-view />
  </div>
</template>

<script>
</script>


<style scoped>

</style>