<template>
  <div id='settings'>
      <header>
          <img class='user-photo' src="../style/user.png" alt="user picture">
          <h2 class='greeting'>Hello, {{this.$store.state.user.username}}</h2>
          <h3>Please provide the following information:</h3>
      </header>
      <form class='settings-form' @submit.prevent='submitProfileSettings'>
          <label for="age">Year of birth:</label>
          <input type="number" id='age' v-model='profile.yob' class='form-control' placeholder="Age" required autofocus>
          
          <label for="weight">Weight:</label>
          <input type="number" id='weight' v-model='profile.weight' class='form-control' placeholder="Weight (lbs.)" required autofocus>
          <!-- <label for="age">Sex:</label>
          <input type="number" id='sex' class='form-control' placeholder="Sex" required autofocus> -->
          
          <label for="year-of-diagnosis">Year of diagnosis:</label>
          <!-- diabetes duration affect your metabolism -->
          <input type="number" id='year-of-diagnosis' v-model='profile.yearOfDiagnosis' class='form-control' placeholder="Year of diagnosis" required autofocus>
          <!--Do we let the user chose units of blood sugar (mmol/L or mg/dL-->
          <!--Which -->
          <label for="basal-rate">Basal rate:</label>
          <input type="number" id='basal-rate' v-modal='profile.basalRate' class='form-control' placeholder="Basal rate" required autofocus>
          <!--basal rate should be set hourly(how many units/hour)-->

          <label for="blood-sugar-target">Blood sugar target:</label>
          <input type="number" step='0.25' v-model='bloodSugarTarget' id='blood-sugar-target' class='form-control' placeholder="Blood sugar target" required autofocus>
        
          <label for="sensitivity-ratio">Carbs/Insulin Ratio:</label>
          <input type="number" id='sensitivity-ratio' v-model='sensitivity' class='form-control' placeholder="Carbs/Insulin ratio" required autofocus>
            <button type="submit">Submit</button>
      </form>
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
                weight: '',
                yearOfDiagnosis: '',
                basalRate: '',
                bloodSugarTarget: '',
                sensitivity: ''
            }
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

</style>