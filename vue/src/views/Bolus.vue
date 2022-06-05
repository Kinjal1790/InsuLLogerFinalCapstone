<template>
  <div class="bolus">
       <div class="side-img">
      <img src="../style/carbs.jpg" alt="a woman measuring her blood sugar level">
    </div>
      <form class="bolus-form"  @submit.prevent='chooseModal'>
          <label for="carb-intake">Carbs Intake (g):</label>
          <input v-model='readings.carbIntake' id='carb-intake' type="number" step="0.01">
          <label for="bl-sugar-reading">Blood Sugar Level (mg/dL):</label>
          <input v-model='readings.bloodSugarReading' id='bl-sugar-reading' type="number">
          <!-- <button type="submit" @click="chooseModal">Submit</button> -->
          <button type="submit">Submit</button>

          <b-modal id="bv-modal-bolus" size='lg' centered >
              <template #modal-header="{ }">
                <h3>Your Recommended Bolus Dose (units):</h3>
            </template>
              <div class="d-block text-center">
                  <h3>{{bolus}}</h3>
              </div>
              <!-- <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-bolus')">Okay</b-button> -->
              <template #modal-footer="{ ok }">
                <b-button id='modal-btn' variant="success" @click="ok()">
                    OK
                </b-button>
                </template>
          </b-modal>

          <b-modal id="bv-modal-alert" size='lg' centered >
              <template #modal-header="{ }">
                    <h3>Alert</h3>
            </template>
              <div class="d-block text-center">
                  <h3>{{readings.alert == "high" ? 'Your blood sugar is higher than your target!' : 'Your blood sugar is lower than your target!'}}</h3>
              </div>
              <!-- <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-bolus')">Okay</b-button> -->
              <template #modal-footer="{ ok }">
                <b-button id='modal-btn' variant="success" @click="ok()">
                    OK
                </b-button>
                </template>
          </b-modal>

          <b-modal id="bv-modal-warning" size='lg' centered >
              <template #modal-header="{ }">
                    <h3>Alert</h3>
            </template>
              <div class="d-block text-center">
                  <h3>{{readings.warning == "high" ?
                       'Your blood sugar is dangerously high! If you experience blurred vision, extreme thirst, vomiting consider calling 911!' :
                        'Your blood sugar is dangerously low! If you experience dizziness, shakiness, vomiting consider calling 911!'}}</h3>
              </div>
              <!-- <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-bolus')">Okay</b-button> -->
              <template #modal-footer="{ ok }">
                <b-button id='modal-btn' variant="success" @click="ok()">
                    OK
                </b-button>
                </template>
          </b-modal>
      
      </form>
  </div>
</template>

<script>
import bolusService from "../services/BolusService.js"
export default {
    name: 'bolus',
    data() {
        return {
            readings: {
                userId: this.$store.state.user.id,
                dataAndTime: '',
                carbIntake: '',
                bloodSugarReading: '',
                warning: '',
                alert: ''
            },
            bolus: '',
            targetMax: this.$store.state.profileSettings.targetMax,
            targetMin: this.$store.state.profileSettings.targetMin
        }
    },
    // props: {

    // },
    computed: {
        checkForWarning() {
            if (this.readings.bloodSugarReading > 300) {
                return 'high'
            }
            else if (this.readings.bloodSugarReading < 65) {
                return 'low'
            }
            return "";
        },
        checkForAlert() {
            if (this.readings.bloodSugarReading < 300 && this.readings.bloodSugarReading > this.targetMax) {
                return 'high'
            }
            else if (this.readings.bloodSugarReading > 65 && this.readings.bloodSugarReading < this.targetMin) {
                return 'low'
            }
            return "";
            
        },
    },
    methods: {
        chooseModal() {
            this.getBolus()
            if (this.readings.alert != "") {
                this.$bvModal.show('bv-modal-alert')
            } 
            else if (this.readings.warning != "") {
                this.$bvModal.show('bv-modal-warning')
            }
            else {
                this.$bvModal.show('bv-modal-bolus')
            }
        },
        getBolus() {
            let today = new Date();
            let date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
            let dateTime = date+' '+ time;
            this.readings.dataAndTime = dateTime;
            
            console.log(this.$store.state.profileSettings.targetMin)
            this.readings.warning = this.checkForWarning;
            this.readings.alert = this.checkForAlert;
            
            console.log(this.readings);
            bolusService.sendReadings(this.readings).then((r) => {
                if (r.status == 201) {
                    this.bolus = r.data.toFixed(2)
                }
                else {
                    console.log(r.status)
                }
            })
        }
    }
}
</script>

<style>
    .bolus {
        padding-top: 5em;
        display: grid;
        grid-template-columns: 50% 50%;
    }
    .bolus-form {
        margin-left: 2em;
        display: flex;
        flex-direction: column;
        justify-content: center;
        gap: 1em;
        justify-content: center;
    }
    button, #modal-btn {
        border-radius: 85px;
    }
    .side-img {
        margin: 0 auto;
    }
    .side-img img {
        width: 35vw;
    }
</style>