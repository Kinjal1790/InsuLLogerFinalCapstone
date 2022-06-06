<template>
<div class="report">

    <b-form class='report-form'>

    <div class="date-from">
        <label for="dateFrom">From</label>
        <input type="date" id='dateFrom' v-model='reportFilterData.dateFrom' class='form-control' required autofocus>
    </div>

    <div class="date-to">
        <label for="dateTo">To</label>
        <input type="date" id='dateTo' v-model='reportFilterData.dateTo' class='form-control' required autofocus>
    </div>

    <div class="filter">
        <label for="filter">Filter</label>
          <b-form-select id='filter'  :options='filterOptions' class='form-select' v-model='reportFilterData.filter'></b-form-select>
    </div>


<table>

    <thead>
      <tr>
        <th>&nbsp;</th>
        <th>From</th>
        <th>To</th>
        <th>Blood Sugar (Average)</th>
        <th>Insulin Bolus (Average)</th>
        <th>Target Blood sugar (Minimum)</th>
        <th>Target Blood sugar (Maximum)</th>
      </tr>
    </thead>

    <tbody>
      <tr v-for="row in reportData" :key="row.id">
        <td class="name">{{ row.dateFrom }}</td>        
        <td class="name">{{ row.dateTo }}</td>
        <td class="name">{{ row.bloodSugarReading }}</td>
        <td class="name">{{ row.bolusDose}}</td>
        <td class="name">{{ row.targetMin }}</td>
        <td class="name">{{ row.targetMax }}</td>
      </tr>
    </tbody>
  </table>

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
            // bloodSugarReading: '',
            // bolusDose: '',
            // targetMin: '',
            // targetMax: '',
            // dateFrom: '',
            // dateTo: ''
            reportData: [],

            filterOptions: [
                { value: '1', text: '1'},
                { value: '3', text: '3'},
                { value: '7', text: '7'},
                { value: '14', text: '14'},
                { value: '30', text: '30'}
            ]
        }
    },

    methods: {

        getReportData(){


            ReportService.getReport(this.reportFilterData).then((r) => {
                if(r.status == 201) {
                    this.reportData = r.data;
                    // this.bloodSugarReading = r.data.bloodSugarReading
                    // this.bolusDose = r.data.bolusDose.toFixed(2)
                    // this.targetMin = r.data.targetMin
                    // this.targetMax = r.data.targetMax
                    // this.dateFrom = r.data.dateFrom
                    // this.dateTo = r.dateTo
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

</style>