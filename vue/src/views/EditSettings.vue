<template>
  <div id='settings'>
      <header>
          <img class='user-photo' src="../style/user.png" alt="user picture">
          <h2 class='greeting'>Hello, {{this.$store.state.user.firstName}}</h2>
          <h3>Your current settings:</h3>
      </header>
      <b-form class='settings-form' @submit.prevent='submitProfileSettings'>
          <label for="age">Date of birth:</label>
          <input type="date" id='age' class='form-control' v-model='profile.yob'  placeholder="Date of birth" required autofocus :disabled='disable'>
          
          <label for="sex">Sex:</label>
          <b-form-select id='sex'  :options='sexOptions' class='form-select' v-model='profile.sex' :disabled='disable'></b-form-select>

          <label for="weight">Weight (lbs):</label>
          <input type="number" id='weight' v-model='profile.weight' class='form-control' placeholder="Weight (lbs)" required autofocus :disabled='disable'>

          <label for="insulin-name">Insulin name:</label>
          <input type="text" id='insulin-name' v-model='profile.bolusInsulinName' class='form-control' placeholder="Insulin name" required autofocus :disabled='disable'>
          
          <label for="insulin-strength">Insulin strength:</label>
          <input type="text" id='insulin-strength' v-model='profile.insulinStrength' class='form-control' placeholder="Insulin strength" required autofocus :disabled='disable'>
         
          <label for="blood-sugar-target">Blood sugar target:</label>
          <div class="blood-sugar-range">
                <input type="number" step='0.25' v-model='profile.targetMin' id='blood-sugar-target' class='form-control' placeholder="Min" required autofocus :disabled='disable'>
                <input type="number" step='0.25' v-model='profile.targetMax' id='blood-sugar-target' class='form-control' placeholder="Min" required autofocus :disabled='disable'>
          </div>

          <label for="basal-rate">Basal rate:</label>
          <input type="number" id='basal-rate' v-model='profile.basalRate' class='form-control' placeholder="Basal rate" required autofocus :disabled='disable'>
         
          <label for="sensitivity-ratio">Sensitivity:</label>
          <input type="number" id='sensitivity-ratio' v-model='profile.carbInsulinRatio' class='form-control' placeholder="Carbs/Insulin ratio" required autofocus :disabled='disable'>
          
            <button type="submit" v-if='!disable'>Save</button>
            <button class='edit-btn' @click.prevent='enableInput()' v-if='disable'>Edit</button>
      </b-form>
  </div>
</template>

<script>
import profileService from '../services/ProfileService.js'
export default {
    name: 'edit_settings',
    data() {
        return {
            disable: true,
            profile: {
                userId: this.$store.state.user.id,
                yob: '',
                sex: '',
                weight: '',
                bolusInsulinName: '',
                insulinStrength: '',
                targetMin: '',
                targetMax: '',
                basalRate: '',
                sensitivity: '',
                carbInsulinRatio: ''
            },
            errorMsg: 'problems',
            sexOptions: [
                { value: 'male', text: 'Male' },
                { value: 'female', text: 'Female' },
            ]
        }
    },
    created() {
        profileService.getSettings(this.$route.params.id).then(r => {
            this.profile = r.data;
        })
        
    },
    methods: {
        enableInput() {
            this.disable = false;
        },
        submitProfileSettings() {
            console.log(this.profile)
            console.log(this.profile.userId)
            profileService.updateSettings(this.profile, this.profile.userId).then(r => {
                if (r.status == 200) {
                    this.$store.commit("SET_PROFILE_SETTINGS", this.profile);
                    // this.$store.commit("SET_INITIAL_SUBMISSION");
                    this.$router.push('/');
                    window.alert('success')
                }
                else {
                    window.alert("===========")
                }
            })
        }
    }
}
</script>

<style>
  header {
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
    header > * {
        padding: 0.5em;
    }
    header > h3 {
        font-size: 1.4em;
    }
    .user-photo {
        border-radius: 50%;
        margin: 0 auto;
        max-width: 25%;
    }
    .greeting {
        text-align: center;
    }
    button, .edit-btn {
        border-radius: 85px;
    }
    
    .blood-sugar-range {
        width: 96%;
        display: flex;
        justify-content: center;
    }
    .blood-sugar-range input {
        margin-right: 10px;
    }
</style>