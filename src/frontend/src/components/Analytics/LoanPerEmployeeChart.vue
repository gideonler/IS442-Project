<template>
    <div>
        <b-card no-body class="mb-3"> 
            <Bar
                :chart-options="chartOptions"
                :chart-data="chartData"
                :chart-id="chartId"
                :dataset-id-key="datasetIdKey"
                :plugins="plugins"
                :css-classes="cssClasses"
                :styles="styles"
                :width="width"
                :height="height"
                />
      </b-card>
    </div>
</template>

<script>
    import { Bar } from 'vue-chartjs'
    import axios from 'axios';
    export default {
    name: 'LoanPerEmployeeChart',
    components: { Bar },
    created() {
        this.loadData();
    },
    methods: {
      async loadData(){
        await axios
          .get(this.api.get_avg_loans)
          .then((response) => {
            this.total_loans = response.data
          })
          .catch((error) => {
              if (error) {
                  console.log(error);
              }
          });
          this.chartData.datasets[0].data= this.loan_data;
          
        },
        containsKey(obj, key ) {
        return Object.keys(obj).includes(String(key));
        },
    },
    computed: {
      loan_data(){
        if(this.hasYear){
          var result= []
          var loans_in_year= this.total_loans[this.selectedYear]
          for( var i=1; i<13; i++){
            var formattedNumber = ("0" + i).slice(-2);
            if(this.containsKey(loans_in_year, formattedNumber)){
              result.push(loans_in_year[formattedNumber])
            }else{
              result.push(0)
            }
          }
          return result;
        }else{
          return [0,0,0,0,0,0,0,0,0,0,0,0]
        }
      },
      hasYear() {
            return this.containsKey(this.total_loans, this.selectedYear);
        },
    },
    props: {
        selectedYear: {},
        loanChartData:{
            type: Array
        },
        chartId: {
        type: String,
        default: 'bar-chart'
        },
        datasetIdKey: {
        type: String,
        default: 'label'
        },
        width: {
        type: Number,
        default: 400
        },
        height: {
        type: Number,
        default: 300
        },
        cssClasses: {
        default: '',
        type: String
        },
        styles: {
        type: Object,
        default: () => {}
        },
        plugins: {
        type: Array,
        default: () => []
        }

    },
    data() {
        return {
        api: {
            get_avg_loans: "http://localhost:8080/analysis/averageloans" ,
        },
        chartData: {
            labels: [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov','Dec' ],
            datasets: [ { 
            label: 'Avg. Loans of Employee',
            data: [], 
            backgroundColor: [
                'rgba(255, 159, 64, 0.2)'
            ],
            borderColor: [
                'rgba(255, 159, 64, 1)'
            ],
            borderWidth: 1
            } ]
        },
        
        chartOptions: {
            responsive: true,
            layout: {
                padding: 20
            },
            aspectRatio: 2.5, 
            plugins: {
            title: {
            display: true,
            text: 'No. of Loans/ Employee By Month',
            fullSize: true,
            align: 'start',
            font: {
                size: 16
                }
            },
            legend: {
                onClick: function(){}
            }
            },
            scales: {
                y: {
                    beginAtZero: true,
                }
            }
        }
        }
    }
    }
</script>