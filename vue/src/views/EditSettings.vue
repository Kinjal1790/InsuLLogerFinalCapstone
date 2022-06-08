<template>
  <div id='settings'>
      <header>
          <img class='user-photo' src="../style/user.png" alt="user picture">
          <h2 class='greeting'>Hello, {{this.$store.state.user.firstName}}</h2>
          <h3>Your Current Settings:</h3>
      </header>
      <b-form class='settings-form' @submit.prevent='submitProfileSettings'>
          <label for="age">Date of Birth:</label>
          <input type="date" id='age' class='form-control' v-model='profile.yob'  placeholder="Date of birth" required autofocus :disabled='disable'>
          
          <label for="sex">Sex:</label>
          <b-form-select id='sex'  :options='sexOptions' class='form-select' v-model='profile.sex' :disabled='disable'></b-form-select>

          <label for="weight">Weight (lbs):</label>
          <input type="number" id='weight' v-model='profile.weight' class='form-control' placeholder="Weight (lbs)" step="0.01" min='1' required autofocus :disabled='disable'>

          <label for="insulin-name">Insulin Name:</label>
          <input type="text" id='insulin-name' v-model='profile.bolusInsulinName' class='form-control' placeholder="Insulin name" required autofocus :disabled='disable'>
         
          <label for="insulin-strength">Insulin Strength:</label>
          <b-form-select id='insulin-strength'  :options='strengthOptions' class='form-select' v-model='profile.insulinStrength'  :disabled='disable'></b-form-select>
         
          <label for="blood-sugar-target">Blood Sugar Target:</label>
          <div class="blood-sugar-range">
                <input type="number"  v-model='profile.targetMin' id='blood-sugar-target' class='form-control' placeholder="Min" min='1' required autofocus :disabled='disable'>
                <input type="number" v-model='profile.targetMax' id='blood-sugar-target' class='form-control' placeholder="Min" min='1' required autofocus :disabled='disable'>
          </div>

          <label for="basal-rate">Basal Rate:</label>
          <input type="number" id='basal-rate' v-model='profile.basalRate' class='form-control' placeholder="Basal rate" step="0.01" min='0.1' required autofocus :disabled='disable'>
         
          <label for="sensitivity-ratio">Sensitivity:</label>
          <input type="number" id='sensitivity-ratio' v-model='profile.sensitivity' class='form-control' placeholder="Sensitivity" step="0.01" min='0.1' required autofocus :disabled='disable'>
          
          <label for="carb-insulin-ratio">Carbs/Insulin Ratio:</label>
          <input type="number" id='carb-insulin-ratio' v-model='profile.carbInsulinRatio' class='form-control' placeholder="Carbs/Insulin ratio" step="0.01" min='0.1' required autofocus :disabled='disable'>
            
          <button type="submit" v-if='!disable'>Save</button>
          <button class='edit-btn' @click.prevent='enableInput()' v-if='disable'>Edit</button>
      </b-form>

       <b-modal id="bv-modal-edit" size='lg' centered hide-header>
              <div class="d-block text-center">
                  <h3>Your changes were successfully saved!</h3>
              </div>
              <!-- <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-bolus')">Okay</b-button> -->
              <template #modal-footer="{ ok }">
                    <b-button id='modal-btn' variant="success" @click="ok()">
                        OK
                    </b-button>
               </template>
          </b-modal>
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
            // errorMsg: 'problems',
            sexOptions: [
                { value: 'male', text: 'Male' },
                { value: 'female', text: 'Female' },
            ],
            strengthOptions: [
                { value: 'u-100', text: 'U-100'},
                { value: 'u-300', text: 'U-300'},
                { value: 'u-500', text: 'U-500'},
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
            // console.log(this.profile)
            // console.log(this.profile.userId)
            profileService.updateSettings(this.profile, this.profile.userId).then(r => {
                if (r.status == 200) {
                    this.$store.commit("SET_PROFILE_SETTINGS", this.profile);
                    this.$bvModal.show('bv-modal-edit')
                    this.disable = true;
                    
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