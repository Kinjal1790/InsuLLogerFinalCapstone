<template>
  <div id='settings'>
      <header>
          <img class='user-photo' src="../style/user.png" alt="user picture">
          <h2 class='greeting'>Hello, {{this.$store.state.user.firstName}}</h2>
          <h3>Please provide the following information:</h3>
      </header>
      <b-form class='settings-form' @submit.prevent='submitProfileSettings'>
          <label for="age">Date of birth:</label>
          <input type="date" id='age' v-model='profile.yob' class='form-control'  placeholder="Date of birth" required autofocus>
          
          <label for="sex">Sex:</label>
          <b-form-select id='sex' v-model='profile.sex' :options='sexOptions' class='form-select'></b-form-select>

          <label for="weight">Weight (lbs):</label>
          <input type="number" id='weight' v-model='profile.weight' class='form-control' placeholder="Weight (lbs)" step="0.01" required autofocus>

          <label for="insulin-name">Insulin name:</label>
          <input type="text" id='insulin-name' v-model='profile.bolusInsulinName' class='form-control' placeholder="Insulin name" required autofocus>
          
          <label for="insulin-strength">Insulin strength:</label>
          <input type="text" id='insulin-strength' v-model='profile.insulinStrength' class='form-control' placeholder="Insulin strength" required autofocus>
         
          <label for="blood-sugar-target">Blood sugar target:</label>
          <div class="blood-sugar-range">
                <input type="number" step='0.01' v-model='profile.targetMin' id='blood-sugar-target' class='form-control' placeholder="Min" required autofocus>
                <input type="number" step='0.01' v-model='profile.targetMax' id='blood-sugar-target' class='form-control' placeholder="Max" required autofocus>
          </div>

          <label for="basal-rate">Basal rate:</label>
          <input type="number" id='basal-rate' v-model='profile.basalRate' class='form-control' placeholder="Basal rate" step="0.01" required autofocus>
         
          <label for="sensitivity-ratio">Sensitivity:</label>
          <input type="number" id='sensitivity-ratio' v-model='profile.sensitivity' class='form-control' placeholder="sensitivity" step="0.01" required autofocus>

          <label for="carb-insulin-ratio">Carb-Insulin-Ratio:</label>
          <input type="number" id='carb-insulin-ratio' v-model='profile.carbInsulinRatio' class='form-control' placeholder="Carbs/Insulin ratio" step="0.01" required autofocus>
          
          
            <button type="submit">Submit</button>
      </b-form>
  </div>
</template>

<script>
import profileService from '../services/ProfileService'

export default {
    name: 'profile-settings',
    components: {},
    data() {
        return {
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
            sexOptions: [
                { value: 'male', text: 'Male' },
                { value: 'female', text: 'Female' },
            ]
        }
    },
    methods: {
        submitProfileSettings() {
            profileService.submitSettings(this.profile).then((r) => {
                if (r.status == 201) {
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
    button[type=submit] {
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