<template>
    <div>
        <div id='filter-portion'>
        <b-form class='report-form' @submit.prevent='getActivityById'>
            <div class="user-id-input">
                <label for="user-id-input">Filter:</label>
                <input type="number" id='user-id-input' v-model='id' class='form-control' placeholder="User-ID" step="0.01" required autofocus>
            </div>
            <div>
                <button id="submit-report">Submit</button>
            </div>
        </b-form>

         <b-form class='report-form' @submit.prevent='reset'>
            <div>
                <button id="submit-report">Reset</button>
            </div>
        </b-form>
        </div>

        <h1 id='activity-header'>Activity History</h1>
        <table id='activity'>
            <thead>
                <tr id='head-row'>
                    <th>User ID</th>
                    <th>Date / Time</th>
                    <!-- <th>Time of the day</th> -->
                    <th>Blood Sugar Level</th>
                    <th>Bolus</th> 
                    <th>Carbs Intake</th>
                    <th>Warning</th>
                    <th>Alert</th>
                </tr>
            </thead>
      <tbody>
          <tr v-for="item in filteredActivity" v-bind:key='item.id'>
              <td>{{item.userId}}</td>
              <!-- <td>{{item.dataAndTime.slice(0, 11)}}</td>
              <td>{{item.dataAndTime.slice(11, 16)}}</td> -->
              <td>{{item.dataAndTime.slice(0,16) | formatDate1}}</td>
              <td>{{item.bloodSugarReading}}</td>
              <td>{{item.bolus.toFixed(1)}}</td>
              <td>{{item.carbIntake}}</td>
              <td>{{item.warning == "high" ? "Dangerously High Blood Sugar" : item.warning == 'low' ? 'Dangerously Low Blood Sugar' : ""}}</td>
              <td>{{item.alert == "high" ? "Blood Sugar Higher than Normal" : item.alert == 'low' ? 'Blood Sugar Lower than Normal' : ""}}</td>
          </tr>
      </tbody>
    </table>
    </div>
  
</template>

<script>
import activityService from '../services/ActivityService.js'

export default {
    name: 'admin-report',
    data() {
        return {
            activity: [],
            filteredActivity : [],
           id: ''
        }
    },
    created() {
        // let id = this.$store.state.user.id;
       
        activityService.getAdminHistory().then(r => {
            this.activity = r.data;
            this.$store.commit("ALL_ACTIVITY_HISTORY", this.activity)
             this.filteredActivity = this.$store.state.activityHistoryAllUsers
        })
    },


     methods: {

        getActivityById() {
            console.log('working?');
             this.filteredActivity = this.$store.state.activityHistoryAllUsers.filter((x)=>{   
                return x.userId == this.id
            })
            console.log('content of filtered array:');
            console.log(this.filteredActivity);

        },

        reset(){
            this.filteredActivity = this.$store.state.activityHistoryAllUsers
        }


    }}




</script>

<style>
    #activity-header {
        text-align: center;
        margin: 1em 0 2em 0;
    }
    #activity {
        border-collapse: collapse;
        width: 100%;
    }
    #activity tr:nth-child(even){
        background-color: #385a6421;
    }
    #activity tr:hover {
        background-color: #fd74595b;
    }
    #head-row {
        text-align: center;
    }
    th {
        background-color: #fd7459;
        color: white;
        text-align: center;
        padding: 1em 0;
    }
    #activity td{
        text-align: center;
        padding: 1em 0;
    }

     #submit-report {
        padding: 0.7em;
         width: 8em;
    }

   
</style>