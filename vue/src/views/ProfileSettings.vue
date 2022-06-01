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
          
          <label for="weight">Weight (lbs):</label>
          <input type="number" id='weight' v-model='profile.weight' class='form-control' placeholder="Weight (lbs)" required autofocus>
          
          <!-- <label for="age">Sex:</label>
          <input type="text" id='sex' v-model='profile.sex' class='form-control' placeholder="Sex" required autofocus> -->
          <label for="sex">Sex:</label>
          <b-form-select id='sex' v-model='profile.sex' :options='sexOptions' class='form-select'></b-form-select>
            
          
          <!-- <label for="year-of-diagnosis">Year of diagnosis:</label> -->
          <!-- diabetes duration affect your metabolism -->
          <!-- <input type="number" id='year-of-diagnosis' v-model='profile.yearOfDiagnosis' class='form-control' placeholder="Year of diagnosis" required autofocus> -->
          <!--Do we let the user chose units of blood sugar (mmol/L or mg/dL-->
          <!--Which basal rate should be set hourly(how many units/hour)-->

          <label for="sensitivity-ratio">Insulin name:</label>
          <input type="text" id='sensitivity-ratio' v-model='profile.insulinType' class='form-control' placeholder="Insulin name" required autofocus>
          
          <label for="sensitivity-ratio">Insulin strength:</label>
          <input type="text" id='sensitivity-ratio' v-model='profile.insulinStrength' class='form-control' placeholder="Insulin strength" required autofocus>
         
          <label for="basal-rate">Basal rate:</label>
          <input type="number" id='basal-rate' v-modal='profile.basalRate' class='form-control' placeholder="Basal rate" required autofocus>
          
          <label for="blood-sugar-target">Blood sugar target:</label>
          <div class="blood-sugar-range">
                <input type="number" step='0.25' v-model='profile.bloodSugarTargetMin' id='blood-sugar-target' class='form-control' placeholder="Min" required autofocus>
                <input type="number" step='0.25' v-model='profile.bloodSugarTargetMax' id='blood-sugar-target' class='form-control' placeholder="Min" required autofocus>
          </div>
         
          <!-- <label for="sensitivity-ratio">Carbs/Insulin Ratio:</label>
          <input type="number" id='sensitivity-ratio' v-model='profile.sensitivity' class='form-control' placeholder="Carbs/Insulin ratio" required autofocus>
           -->
          
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
                userId: this.$store.state.user.username,
                yob: '',
                sex: '',
                weight: '',
                yearOfDiagnosis: '',
                insulinType: '',
                insulinStrength: '',
                basalRate: '',
                bloodSugarTargetMin: '',
                bloodSugarTargetMax: '',
                sensitivity: ''
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
        /* align-items: space-between; */
        justify-content: center;
    }
    .blood-sugar-range input {
        margin-right: 10px;
    }

</style>