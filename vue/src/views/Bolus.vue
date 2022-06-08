<template>
  <div class="bolus">
       <div class="side-img">
      <img src="../style/carbs.jpg" alt="a woman measuring her blood sugar level">
    </div>
      <form class="bolus-form"  @submit.prevent='getBolus'>
          <label for="carb-intake">Carbs Intake (g):</label>
          <input v-model='readings.carbIntake' id='carb-intake' type="number" step="0.01">
          <label for="bl-sugar-reading">Blood Sugar Level (mg/dL):</label>
          <input v-model='readings.bloodSugarReading' id='bl-sugar-reading' type="number">

          <button type="submit">Submit</button>

          <b-modal id="bv-modal-bolus" size='lg' centered >
              <template #modal-header="{ }">
                <h3>Your Recommended Bolus Dose (units):</h3>
            </template>
              <div class="d-block text-center">
                  <h3>{{bolus}}</h3>
              </div>

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
                  <h3>{{alert == "high" ? `Your blood sugar is higher than your target maximum (${targetMax}) !` : `Your blood sugar is lower than your target minimum (${targetMin}) !`}}</h3>
                  <h2>{{bolus > 0 ? `Recommended bolus: ${bolus}` : ""}}</h2>
              </div>
              <template #modal-footer="{ ok }">
                <b-button id='modal-btn' variant="success" @click="ok()">
                    OK
                </b-button>
                </template>
          </b-modal>

          <b-modal id="bv-modal-warning" size='lg' centered >
              <template #modal-header="{ }">
                    <h3>Warning</h3>
            </template>
              <div class="d-block text-center">
                  <h3>{{warning == "high" ?
                       'Your blood sugar is dangerously high (300 or above)! If you experience blurred vision, extreme thirst, vomiting consider calling 911!' :
                        'Your blood sugar is dangerously low (65 or below)! If you experience dizziness, shakiness, vomiting consider calling 911!'}}</h3>
                        <h2>{{bolus > 0 ? `Recommended bolus: ${bolus}` : ""}}</h2>
              </div>
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
            warning: '',
            alert: '',
            targetMin: '',
            targetMax: ''
        }
    },
   
    methods: {
        
        getBolus() {
            let today = new Date();
            let date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();
            let time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
            let dateTime = date+' '+ time;
            this.readings.dataAndTime = dateTime;
            
            console.log(this.readings);
            bolusService.sendReadings(this.readings).then((r) => {
                if (r.status == 201) {
                    this.bolus = r.data.bolus.toFixed(1);
                    this.alert = r.data.alert;
                    this.warning = r.data.warning;
                    this.targetMin = r.data.targetMin;
                    this.targetMax = r.data.targetMax;
                    console.log(r.data)
                    console.log(this.alert, r.data.warning, this.bolus)
                    this.chooseModal()
                }
                else {
                    console.log(r.status)
                }
            })
        },
        chooseModal() {
            if (this.alert != "") {
                this.$bvModal.show('bv-modal-alert')
            } 
            else if (this.warning != "") {
                this.$bvModal.show('bv-modal-warning')
            }
            else {
                this.$bvModal.show('bv-modal-bolus')
            }
        },
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