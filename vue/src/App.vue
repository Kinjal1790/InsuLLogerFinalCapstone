<style src='@/style/insulin_style.css'>

</style>
<template>
  <div id="app" class='container'>
    <div id="nav">
      <div class="logo">
        <img src="@/style/insulogger_logo.png" alt="logo">
        <h2 class='company-name'>InsuLogger</h2>
      </div>
      <nav>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            <router-link v-bind:to="{name: 'about'}" v-if="$store.state.token == ''">About</router-link>
            <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
            
            <router-link v-bind:to="{name: 'bolus'}" v-if="$store.state.token != ''">Generate Bolus</router-link>
            <router-link v-bind:to="{name: 'activity', params: {id: $store.state.user.id}}" v-if="$store.state.token != '' && $store.state.user.authorities[0].name == 'ROLE_USER'">Activity</router-link>
            <router-link v-bind:to="{name: 'admin-report'}" v-if="$store.state.token != '' && $store.state.user.authorities[0].name == 'ROLE_ADMIN'">Activity</router-link>
            <router-link v-bind:to="{name:'report'}" v-if="$store.state.token != ''">Report</router-link>
            <!-- <router-link v-bind:to='{name: "profile_settings"}'  v-if="$store.state.token != '' && !$store.state.profileSettings.userId">Profile Settings</router-link> -->
            <router-link v-bind:to="{name: 'edit_settings', params: {id: $store.state.user.id}}"  v-if="$store.state.token != ''">Profile Settings</router-link>
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Log Out</router-link>
      </nav> 
    </div>
    <router-view />
  </div>
</template>

<script>
</script>


<style scoped>

</style>