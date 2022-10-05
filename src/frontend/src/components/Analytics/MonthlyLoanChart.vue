<template>
<div>
    <b-card
    title="No. of Loans By Month"
    class="mx-1"
  >
    <b-card-body>
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
    </b-card-body>
  </b-card>

</div>
    
       
      </template>
      
      <script>
      import { Bar } from 'vue-chartjs'

      export default {
        name: 'BarChart',
        components: { Bar },
        props: {
          chartId: {
            type: String,
            default: 'borrower-chart'
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
            chartData: {
              labels: [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November','December' ],
              datasets: [
                    {
                        label: 'Total',
                        data: [100, 88, 32, 113, 99, 165, 40],
                        color: 'black'
                    },
                    {
                        label: 'Mandai Wildlife Reserve',
                        data: [40, 39, 10, 40, 39, 80, 40],
                        backgroundColor: 'rgba(255, 206, 86, 0.2)',
                        borderColor:'rgba(255, 206, 86, 1)'
                    },
                    {
              
                        label: 'Duck Tours',
                        data: [40, 39, 10, 40, 39, 80, 40],
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor:'rgba(75, 192, 192, 1)'
                    },
                    {
                       
                        label: 'Singapore Flyer',
                        data: [20, 10, 12, 33, 22, 4, 0],
                        backgroundColor: 'rgba(153, 102, 255, 0.2)',
                        borderColor: 'rgba(153, 102, 255, 1)'
                    },
      
                ]
            },
            chartOptions: {
              responsive: true,
              maintainAspectRatio: false,
              scales: {
                xAxes: {
                    stacked:true
                },
                yAxes: 
                {
                    stacked: true
                }
                
            },
            legend: { display: true }
            }
          }
        }
      }
      </script>