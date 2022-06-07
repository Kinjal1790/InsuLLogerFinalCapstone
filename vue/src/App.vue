<style src='@/style/insulin_style.css'>

</style>
<template>
  <div id="app" class='container'>
    <div id="nav">
      <div class="logo">
        <img src="@/style/insulogger_logo.png" alt="logo">
        <h2 class='company-name'>InsuLogger</h2>
      </div>
      <nav v-bind:class="[isOpen ? 'topnav responsive' : 'topnav']">
        <span  v-if="$store.state.token != '' && $store.state.user.authorities[0].name == 'ROLE_ADMIN'">You are logged in as Admin!</span>
            <router-link v-bind:to="{ name: 'home' }">Home</router-link>
            <a v-if="$store.state.token == ''">About</a>
            <router-link v-bind:to="{ name: 'login' }" v-if="$store.state.token == ''">Login</router-link>
            
            <router-link v-bind:to="{name: 'bolus'}" v-if="$store.state.token != ''  && $store.state.user.authorities[0].name != 'ROLE_ADMIN'">Generate Bolus</router-link>
            <router-link v-bind:to="{name: 'activity', params: {id: $store.state.user.id}}" v-if="$store.state.token != '' && $store.state.user.authorities[0].name == 'ROLE_USER'">Activity</router-link>
            
            <router-link v-bind:to="{name: 'admin-report'}" v-if="$store.state.token != '' && $store.state.user.authorities[0].name == 'ROLE_ADMIN'">Activity</router-link>
            <router-link v-bind:to="{name:'report'}" v-if="$store.state.token != ''  && $store.state.user.authorities[0].name != 'ROLE_ADMIN'">Report</router-link>
            <!-- <router-link v-bind:to='{name: "profile_settings"}'  v-if="$store.state.token != '' && !$store.state.profileSettings.userId">Profile Settings</router-link> -->
            <router-link v-bind:to="{name: 'edit_settings', params: {id: $store.state.user.id}}"  v-if="$store.state.token != '' && $store.state.user.authorities[0].name != 'ROLE_ADMIN'">Profile Settings</router-link>
            <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token != ''">Log Out</router-link>
      </nav> 
      <a href="javascript:void(0);" class="icon" @click="toggleDropDown()">
          <span>{{isOpen ? "Show Menu" : "Hide Menu"}}</span>
      </a>
    </div>
    <router-view />
  </div>
</template>

<script>
  export default {
    data() {
      return {
        isOpen: false,
      }
      
    },
    methods: {
      toggleDropDown() {
        this.isOpen = !this.isOpen
      }
    }
  }
</script>


<style scoped>
  
  .icon {
    display: none;
  }
  @media screen and (max-width: 900px) {
    .icon {
      display: inline;
    }
    .responsive {
    display: none;
  }
  }
</style>