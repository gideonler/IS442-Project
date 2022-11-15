<template>
<div class="content">
  <div class="container-fluid">
    <div class="row float-right">
    <b-button  @click="downloadCsv" pill b-button class="my-1" variant="dark" size="sm" title="Export">
      <b-icon icon="download" aria-hidden="true"></b-icon>
    </b-button>
  </div>
    <div class="row">
        <div class="col-xl-3 col-sm-4 second">
          <StatsCard>
            <div slot="header" class="icon-warning">
              <i class="nc-icon nc-badge text-warning"></i>
            </div>
            <div slot="content">
              <p class="card-category">Total Loans</p>
              <h4 class="card-title" v-if="hasYear">{{year_stats[selected_year]["total_loans"]}}</h4>
              <h4 class="card-title" v-else>NA</h4>
            </div>
            <div slot="footer">
              <i class="fa fa-calendar-o"></i>{{selected_year}}
            </div>
          </StatsCard>
        </div>
        <div class="col-xl-3 col-sm-4 third">
          <StatsCard>
            <div slot="header" class="icon-warning">
              <i class="nc-icon nc-single-copy-04 text-success"></i>
            </div>
            <div slot="content">
              <p class="card-category">Total Borrowers</p>
              <h4 class="card-title" v-if="hasYear">{{year_stats[selected_year]["total_borrowers"]}}</h4>
              <h4 class="card-title" v-else>NA</h4>
            </div>
            <div slot="footer">
              <i class="fa fa-calendar-o"></i>{{selected_year}}
            </div>
          </StatsCard>
        </div>
        <div class="col-sm-4 col-xl-3 fourth">
          <StatsCard>
            <div slot="header" class="icon-warning">
              <i class="nc-icon nc-single-02 text-danger"></i>
            </div>
            <div slot="content">
              <p class="card-category">Avg Loans/Employee</p>
              <h4 class="card-title" v-if="hasYear">{{year_stats[selected_year]["avg_loans"]}}</h4>
              <h4 class="card-title" v-else>NA</h4>
            </div>
            <div slot="footer">
              <i class="nc-icon nc-calendar-60"></i>{{selected_year}}
            </div>
          </StatsCard>
        </div>
        <div class="col-xl-3 mb-3 first">
          <h3 style="letter-spacing: 2px;" class="mb-0"><strong>DASHBOARD</strong></h3>
          <strong>For the Year of</strong>
          <b-form-select v-model="selected_year" :options="options"></b-form-select>

        </div>
      </div>
      <div class="row">
        <MonthlyBorrowerChart  :key=this.selected_year :selectedYear=this.selected_year class="col-lg-6"></MonthlyBorrowerChart>
        <LoanPerEmployeeChart :key=this.selected_year :selectedYear=this.selected_year  class="col-lg-6"></LoanPerEmployeeChart>
      </div>
      <div class="row">
        <MonthlyLoanChart :key=this.selected_year :selectedYear=this.selected_year  class="col-lg-12"></MonthlyLoanChart>
      </div>
    </div>
    
</div>
</template>

<script>
import DashboardLayout from '../layouts/DashboardLayout'
import MonthlyBorrowerChart from '../components/Analytics/MonthlyBorrowerChart.vue'
import MonthlyLoanChart from '../components/Analytics/MonthlyLoanChart.vue'
import LoanPerEmployeeChart from '../components/Analytics/LoanPerEmployeeChart.vue';
import StatsCard from '../components/Cards/StatsCard.vue';
import axios from 'axios';

  const curr_year = new Date().getFullYear()
  const monthlyLoanData= {2021: [40, 20, 12], 2022: [56, 11, 60]}
  export default {
    name: "analytizcs-page",
    data(){
      return {
      selected_year:curr_year,
      options: [],
        monthlyLoan: [],
        total_attractions: {},
        total_employees: {},
        year_stats: {},
      api: {
          exportCsv: "http://localhost:8080/export/loans" ,
          get_total_employees: "http://localhost:8080/analysis/totalemployees" ,
          get_year_stats: "http://localhost:8080/analysis/yearstats" ,
        },
      }
    },
    computed: {
        hasYear() {
            return this.containsKey(this.year_stats, this.selected_year);
        }},
    watch: {
      selected_year() {
        console.log(this.selected_year)
        this.monthlyLoan=  monthlyLoanData[this.selected_year]
    }
  },
    created() {
        this.$emit("update:layout", DashboardLayout);
        this.loadData();
    },
    methods: {
      containsKey(obj, key ) {
        return Object.keys(obj).includes(String(key));
        },
      async loadData(){          
          // await axios
          // .get(this.api.get_total_attractions)
          // .then((response) => {
          //   this.total_attractions = response.data
          //   var years= Object.keys(this.total_attractions)
          //   console.log(years)
          //   console.log(this.total_attractions)
          // })
          // .catch((error) => {
          //     if (error) {
          //         console.log(error);
          //     }
          // });


          // await axios
          // .get(this.api.get_total_employees)
          // .then((response) => {
          //   this.total_employees = response.data
          // })
          // .catch((error) => {
          //     if (error) {
          //         console.log(error);
          //     }
          // });


          await axios
          .get(this.api.get_year_stats)
          .then((response) => {
            this.year_stats = response.data
            var years= Object.keys(this.year_stats)
            var options= []
            for(var year of years){
              options.push({"value": year, "text": year})
            }
            this.options= options
          })
          .catch((error) => {
              if (error) {
                  console.log(error);
              }
          });
          console.log(this.total_loans, this.total_attractions,
          this.avg_loans, this.total_employees, this.year_stats)
        },
       csvJSON(csvStr){
        var lines=csvStr.split("\n");
        var result = [];

        // NOTE: If your columns contain commas in their values, you'll need
        // to deal with those before doing the next step 
        // (you might convert them to &&& or something, then covert them back later)
        // jsfiddle showing the issue https://jsfiddle.net/
        var headers=lines[0].split(",");

        for(var i=1;i<lines.length;i++){

            var obj = {};
            var currentline=lines[i].split(",");

            for(var j=0;j<headers.length;j++){
                obj[headers[j]] = currentline[j];
            }

            result.push(obj);

        }
        return result; //JavaScript object
      },
        async downloadCsv() {
          console.log('dfe')
          await axios
          .get(this.api.exportCsv,
          {responseType: 'blob'}
          )
          .then((response) => {
           const file = response.data;
           console.log(file)
          //  file.text().then((csvStr) => {
          //   console.log(csvStr)
            // const jsonObj = this.csvJSON(csvStr);
            // console.log(jsonObj);
          // })

          })
          .catch((error) => {
              if (error) {
                  console.log(error);
              }
          });
        }
            
      },

    components: { MonthlyLoanChart, MonthlyBorrowerChart, LoanPerEmployeeChart, StatsCard }
}
</script>

<style scoped>
@media screen and (max-width:1200px) {
  .first {
    order: 1;
    
  }
  .second {
    order: 2;
  }
  .third {
    order: 4;
  }
  .fourth {
    order: 3;
  }
}
</style>