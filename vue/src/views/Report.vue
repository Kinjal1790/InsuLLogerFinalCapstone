<template>
<div class="report">

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
    </b-form>

  
    <div>
        <h1 id='report-header'>Report</h1>
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
                    <td class="name">{{ row.dateFrom }}</td>        
                    <td class="name">{{ row.dateTo }}</td>
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
                }
                else{
                    console.log(r.status)
                }

            })
        }

    }
}

</script>


<style>

    #report-header {
        text-align: center;
        margin: 0.75em 0 0.75em 0;
    }
    .report {
        border-collapse: collapse;
        width: 100%;
    }

     

   
    .report-form {
        display: flex;
        flex-direction: row;
        justify-content: space-around;
        margin: 20px;
        align-items : flex-end;
        
    }

    #report {
        width: 100%;
    }
    
    #submit-report {
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
        background-color: #fd7459;
        color: white;
        text-align: center;
        padding: 1em 0;
    }
    #report td{
        text-align: center;
        padding: 1em 0;
    }

</style>