<template>
    <div>
        <b-card
        title="No. of Borrows By Month"
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
                default: 350
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
                  datasets: [ { 
                    label: 'No. of Borrows',
        
                    //TODO:  Replace Dummy Data with backend data
                    data: [40, 20, 12], 
                    backgroundColor: [
                        // 'rgba(255, 99, 132, 0.2)',
                        // 'rgba(54, 162, 235, 0.2)',
                        // 'rgba(255, 206, 86, 0.2)',
                        // 'rgba(75, 192, 192, 0.2)',
                        // 'rgba(153, 102, 255, 0.2)',
                        'rgba(255, 159, 64, 0.2)'
                    ],
                    borderColor: [
                        // 'rgba(255, 99, 132, 1)',
                        // 'rgba(54, 162, 235, 1)',
                        // 'rgba(255, 206, 86, 1)',
                        // 'rgba(75, 192, 192, 1)',
                        // 'rgba(153, 102, 255, 1)',
                        'rgba(255, 159, 64, 1)'
                    ],
                    borderWidth: 1
                    } ]
                },
                chartOptions: {
                  responsive: true,
                  maintainAspectRatio: false,
                  scales: {
                        y: {
                            beginAtZero: true
                        }
                    }
                }
              }
            }
          }
          </script>