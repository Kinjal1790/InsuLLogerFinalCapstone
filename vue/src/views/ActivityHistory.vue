<template>
    <div>
        <h1 id='activity-header'>Your Activity History</h1>
        <table id='activity'>
      <thead>
          <tr id='head-row'>
              <th>Date / Time</th>
              <!-- <th>Time of the day</th> -->
              <th>Blood Sugar Level</th>
              <th>Carbs Intake</th>
              <th>Warning</th>
              <th>Alert</th>
          </tr>
      </thead>
      <tbody>
          <tr v-for="item in activity" :key='item.id'>
              <!-- <td>{{item.dataAndTime.slice(0, 11) | formatDate}}</td>
              <td>{{item.dataAndTime.slice(11, 16)}}</td> -->

              <td>{{item.dataAndTime.slice(0,16) | formatDate1}}</td>
              <!-- <td>{{item.dataAndTime.slice(11, 16)}}</td> -->

              <td>{{item.bloodSugarReading}}</td>
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
    name: 'activity-history',
    data() {
        return {
            activity: []
        }
    },
    created() {
        let id = this.$store.state.user.id;
        activityService.getActivity(id).then(r => {
            this.activity = r.data;
        })
    }
}
</script>

<style>
    #activity-header {
        text-align: center;
        margin: 0.60em 0 0.75em 0;
    }
    #activity {
        border-collapse: collapse;
        width: 100%;
        margin-bottom: 30px;
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
   
</style>