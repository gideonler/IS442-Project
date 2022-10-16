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

    export default {
    name: 'LoanPerEmployeeChart',
    components: { Bar },
    props: {
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
        chartData: {
            labels: [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov','Dec' ],
            datasets: [ { 
            label: 'Avg. Loans of Employee',

            //TODO:  Replace Dummy Data with backend data
            data: this.loanChartData, 
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