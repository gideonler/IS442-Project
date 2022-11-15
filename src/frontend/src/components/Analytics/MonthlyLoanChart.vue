<template>
  <div>
      <b-card no-body class="mb-0">
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
  import { Chart, registerables } from 'chart.js';
  import { Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'
  import axios from 'axios';

  Chart.register(...registerables, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

  export default {
    name: 'MonthlyLoanChart',
    components: { Bar },
    created() {
        this.loadData();
    },
    methods: {
      async loadData(){
        await axios
          .get(this.api.get_total_attractions)
          .then((response) => {
            this.total_loans = response.data
          })
          .catch((error) => {
              if (error) {
                  console.log(error);
              }
          });
          this.chartData.datasets= this.loan_data;
          
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
          console.log(loans_in_year)
          var attractions= Object.keys(Object.values(loans_in_year)[0]);
          //  for( var i=0; i<no_attractions; i++){
          //     new_map = {type: 'bar'}
          //     new_map['test']=3;
          //  }
          var intermediate_data= {};

          for(var attraction of attractions){
            intermediate_data[attraction]= []
          }

          for( var i=1; i<13; i++){
            var formattedNumber = ("0" + i).slice(-2);
            if(this.containsKey(loans_in_year, formattedNumber)){
              for(var attraction of attractions){
                // console.log(loans_in_year[formattedNumber][attraction])
                intermediate_data[attraction].push(loans_in_year[formattedNumber][attraction])
              }
            }else{
              intermediate_data[attraction].push(0)
            }
          }
          // console.log(intermediate_data)
          // convert object to keys array
          const keys = Object.keys(intermediate_data);
          var temp_total=  {
                    type: 'line',
                    label: 'Total Loans',
                    data: [0,0,0,0,0,0,0,0,0,0,0,0],
                    backgroundColor: 'black',
                }
          keys.forEach((key, index) => {
            var attraction_name = key
            var attraction_data = intermediate_data[key]
            console.log(attraction_data)
            var temp_map = { type: 'bar'}
            temp_map['label']= attraction_name
            temp_map['data']= attraction_data
            temp_map['backgroundColor']= this.backgroundColor[index]
            temp_map['borderColor']= this.borderColor[index]
            var sum = attraction_data.map(function (num, idx) {
                      return num + temp_total["data"][idx];
                    });

            temp_total["data"]= sum;
            result.push(temp_map)
          });
          result.push(temp_total)
          console.log(result)

          return result;
        }else{
          return [{
                    type: 'line',
                    label: 'Total Loans',
                    data: [0,0,0,0,0,0,0,0,0,0,0,0],
                    backgroundColor: 'black',
                }]
        }
      },
      hasYear() {
            return this.containsKey(this.total_loans, this.selectedYear);
        },
    },
    props: {
      selectedYear: {},
      data:{
        type: Array,
        default:() => []
      },
      chartId: {
        type: String,
        default: 'monthly-loan-chart'
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
        default: 500
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
        backgroundColor: ['rgba(255, 206, 86, 0.5)','rgba(75, 192, 192, 0.5)', 'rgba(153, 102, 255, 0.5)'],
        borderColor:['rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)'],
        api: {
          get_total_attractions: "http://localhost:8080/analysis/totalattractions" ,
        },
        total_loans:{},
        chartData: {
          labels: [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November','December' ],
          datasets:[],
        },
        chartOptions: {
          responsive: true,
          layout: {
                padding: 20
            },
          aspectRatio: 4.3,
          plugins: {
            title: {
            display: true,
            text: 'No. of Loans By Month',
            fullSize: true,
            align: 'start',
            font: {
              size: 18
              }
            }
          },
          scales: {
            xAxes: {
                stacked:true
            },
            yAxes: 
            {
                stacked: true
            },
        },
        legend: { display: true }
        }
      }
    }
  }
</script>
