<template>
<div class="report">
    <h1 id='report-header'>Report</h1>
    <div class="details">
        <p>Select the days that you would like to view reports for using the drop-down calender. <br>
        Then, select a filter that will average the blood sugar levels and bolus insulin dosages based on the filter.</p>
    </div>
    <b-form class='report-form' @submit.prevent='getReport'>

    <div class="date-from">
        <label for="dateFrom">From :</label>
        <input type="date"  class= "formItems form-control" id='dateFrom' v-model='reportFilterData.dateFrom' required autofocus>
    </div>

    <div class="date-to">
        <label for="dateTo">To :</label>
        <input type="date" class= "formItems form-control" id='dateTo' v-model='reportFilterData.dateTo' required autofocus>
    </div>

    <div class="filter">
        <label for="filter">Filter :</label>
        <b-form-select id='filter' class= "formItems form-select" :options='filterOptions' v-model='reportFilterData.filter'></b-form-select>
    </div>

    <div>
        <button id="submit-report" type="submit">Report</button>
    </div>

    <div>
        <button v-show="isFormVisible" id="export-report" v-on:click="exportTableToCSV('report.csv')">Export</button>
    </div>
    
    </b-form>


    <div v-show="isFormVisible">
        
        <table id='report'>
                <thead>
                    <tr id='head-row'>
                        <!-- <th>&nbsp;</th> -->
                        <th>Date (From)</th>
                        <th>Date (To)</th>
                        <th>Blood Sugar (Average)</th>
                        <th>Insulin Bolus (Average)</th>
                        <th>Target Blood Sugar (Minimum)</th>
                        <th>Target Blood Sugar (Maximum)</th>
                    </tr>
                </thead>
            <tbody>
                <tr id= "row-body" v-for="row in reportData" :key="row.id">
                    <td class="name">{{ row.dateFrom | formatDate }}</td>        
                    <td class="name">{{ row.dateTo | formatDate }}</td>
                    <td class="name">{{ row.bloodSugarReading.toFixed(0) + " (mg/dL)"}}</td>
                    <td class="name">{{ row.bolusDose.toFixed(1) + " unit(s)" }}</td>
                    <td class="name">{{ row.targetMin + " (mg/dL)" }}</td>
                    <td class="name">{{ row.targetMax + " (mg/dL)" }}</td>
                </tr>
            </tbody>
        </table>   
    </div>
</div>
</template>

<script>
import ReportService from "../services/ReportService.js"
export default {
    name: 'report',
    data(){
        return{

            reportFilterData: {
                'dateFrom' : '',
                'dateTo' : '',
                'filter' : ''
            },

            isFormVisible: false,

          
            reportData: [],

            filterOptions: [
                { value: '1', text: 'Daily'},
                { value: '3', text: 'Every 3 days'},
                { value: '7', text: 'Weekly'},
                { value: '14', text: 'Biweekly'},
                { value: '30', text: 'Monthly'}
            ]
        }
    },

    methods: {

        getReport(){


            ReportService.getReport(this.reportFilterData, this.$store.state.user.id).then((r) => {
                if(r.status == 200) {
                    this.reportData = r.data;
                    this.isFormVisible = !this.isFormVisible
                }
                else{
                    console.log(r.status)
                }

            })
        },


     
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

    #report-header {
        text-align: center;
        margin: 0.4em 0 0.75em 0;
    }
    .report {
        border-collapse: collapse;
        width: 100%;
    }

    .report-form {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        margin: 2em 20px 2em 20px;
        align-items : flex-end;
        
    }

    #report {
        width: 100%;
        margin-bottom: 30px;
        border-radius: 30px;
        border-collapse: collapse;
        overflow: hidden;
    }
    
   
    #submit-report #export-report {
        padding: 0.7em;
        width: 8em;

    }
    #report tr:nth-child(even){
        background-color: #385a6421;
    }
    #report tr:hover {
        background-color: #fd74595b;
    }
    #head-row {
        text-align: center;
    }
    #row-body {
        width: 100%;
        text-align: center;
    }
    th {
        background-color: #227286;
        color: white;
        text-align: center;
        padding: 1em 0;
    }
    #report td{
        text-align: center;
        padding: 1em 0;
    }
    .report .details {
        margin: 0.5em 0 1em 0;
        /* text-align: left;
        margin-left: 5em; */
    }


</style>