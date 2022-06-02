<template>
  <div class="bolus">
       <div class="side-img">
      <img src="../style/carbs.jpg" alt="a woman measuring her blood sugar level">
    </div>
      <form class="bolus-form"  @submit.prevent='getBolus'>
          <label for="carb-intake">Carbs intake (g):</label>
          <input v-model='readings.carbIntake' id='carb-intake' type="number" step="0.01" placeholder="Carbs">
          <label for="bl-sugar-reading">Blood sugar reading (mg/dL):</label>
          <input v-model='readings.bloodSugarReading' id='bl-sugar-reading' type="number" placeholder="Current blood sugar level">
          <button type="submit" @click="$bvModal.show('bv-modal-bolus')">Submit</button>

          <b-modal id="bv-modal-bolus" size='lg' centered hide-footer>
              <template #modal-header="{ }">
                <h3>Your recommended bolus:</h3>
            </template>
              <div class="d-block text-center">
                  <h3>{{bolus}}</h3>
              </div>
              <b-button class="mt-3" block @click="$bvModal.hide('bv-modal-bolus')">Okay</b-button>
              <template #modal-footer="{ ok, cancel, hide }">
                <b>Custom Footer</b>
                <!-- Emulate built in modal footer ok and cancel button actions -->
                <b-button size="sm" variant="success" @click="ok()">
                    OK
                </b-button>
                <b-button size="sm" variant="danger" @click="cancel()">
                    Cancel
                </b-button>
                <!-- Button with custom close trigger value -->
                <b-button size="sm" variant="outline-secondary" @click="hide('forget')">
                    Forget it
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
                bloodSugarReading: ''
            },
            bolus: '5.5'
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
                    console.log(r.data)
                    window.alert('success');
                }
                else {
                    window.alert(r.status)
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
    button {
        border-radius: 85px;
    }
    .side-img {
        margin: 0 auto;
    }
    .side-img img {
        width: 35vw;
    }
</style>