<template>
    <div>
        <div class="controllers">
            <h1 id='activity-header'>Your Activity History</h1>
            <b-form id='export-button' @submit.prevent="exportTableToCSV('report.csv')">
                <button id='export-report'>Export</button>
            </b-form>
        </div>

  <table id='activity'>
      <thead>
          <tr id='head-row'>
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
          <tr v-for="item in activity" :key='item.id'>
              <!-- <td>{{item.dataAndTime.slice(0, 11) | formatDate}}</td>
              <td>{{item.dataAndTime.slice(11, 16)}}</td> -->

              <td>{{item.dataAndTime.slice(0,16) | formatDate1}}</td>
              <!-- <td>{{item.dataAndTime.slice(11, 16)}}</td> -->

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
            // console.log(this.$store.state.user.authorities[0].name)
        })
    },

    methods: {

        downloadCSV(csv, filename) {
            let csvFile;
            let downloadLink;

            // CSV file
            csvFile = new Blob([csv], {type: "text/csv"});

            // Download link
            downloadLink = document.createElement("a");

            // File name
            downloadLink.download = filename;

            // Create a link to the file
            downloadLink.href = window.URL.createObjectURL(csvFile);

            // Hide download link
            downloadLink.style.display = "none";

            // Add the link to DOM
            document.body.appendChild(downloadLink);

            // Click download link
            downloadLink.click();
        },

        exportTableToCSV(filename) {
            let csv = [];
            let rows = document.querySelectorAll("table tr");
            
            for (let i = 0; i < rows.length; i++) {
                var row = [], cols = rows[i].querySelectorAll("td, th");
                
                for (let j = 0; j < cols.length; j++) 
                    row.push(cols[j].innerText);
                
                csv.push(row.join(","));        
            }

            // Download CSV file
            this.downloadCSV(csv.join("\n"), filename);
        }

    }
}
</script>

<style>

    #activity-header {
        text-align: center;
        margin: 0.75em 0 0.75em 0;
        width: 60rem;
        align-items: center;
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

    #activity td {
        text-align: center;
        padding: 1em 0;
    }

    #export-report {
        padding: 0.7em;
        width: 8em;
        border-radius: 25px;
        align-content: right;
    }

    #export-button {
        margin: 48px;
    }

    

  

   
   
</style>