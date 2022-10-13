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

  Chart.register(...registerables, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale);

  export default {
    name: 'MonthlyLoanChart',
    components: { Bar },
    props: {
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
        chartData: {
          labels: [ 'January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November','December' ],
          
          //TODO : Replace dummy data with backend data
          datasets: [
                {
                    type: 'line',
                    label: 'Total Loans',
                    data: [100, 88, 32, 113, 99, 165, 80],
                    backgroundColor: 'black',
                },
                {
                    type: 'bar',
                    label: 'Mandai Wildlife Reserve',
                    data: [40, 39, 10, 40, 39, 80, 40],
                    backgroundColor: 'rgba(255, 206, 86, 0.5)',
                    borderColor:'rgba(255, 206, 86, 1)'
                },
                {
                    type: 'bar',
                    label: 'Duck Tours',
                    data: [40, 39, 10, 40, 39, 80, 40],
                    backgroundColor: 'rgba(75, 192, 192, 0.5)',
                    borderColor:'rgba(75, 192, 192, 1)'
                },
                {
                    type: 'bar',
                    label: 'Singapore Flyer',
                    data: [20, 10, 12, 33, 22, 4, 0],
                    backgroundColor: 'rgba(153, 102, 255, 0.5)',
                    borderColor: 'rgba(153, 102, 255, 1)'
                },

            ]
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
