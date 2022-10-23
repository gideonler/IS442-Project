<template>
<div class="content">
  <div class="container-fluid">
    <div class="row float-right">
    <b-button pill b-button class="my-1" variant="dark" size="sm" title="Export">
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
              <p class="card-category">Total Passes</p>
              <h4 class="card-title">450</h4>
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
              <h4 class="card-title">300</h4>
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
              <h4 class="card-title">3</h4>
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
        <MonthlyLoanChart  class="col-lg-6"></MonthlyLoanChart>
        <LoanPerEmployeeChart v-bind:loanChartData="monthlyLoan" class="col-lg-6"></LoanPerEmployeeChart>
      </div>
      <div class="row">
        <MonthlyBorrowerChart class="col-lg-12"></MonthlyBorrowerChart>
      </div>
    </div>
    
</div>
</template>

<script>
import DashboardLayout from '../layouts/DashboardLayout'
import MonthlyLoanChart from '../components/Analytics/MonthlyBorrowerChart.vue'
import MonthlyBorrowerChart from '../components/Analytics/MonthlyLoanChart.vue'
import LoanPerEmployeeChart from '../components/Analytics/LoanPerEmployeeChart.vue';
import StatsCard from '../components/Cards/StatsCard.vue';


  const curr_year = new Date().getFullYear()
  const monthlyLoanData= {2021: [40, 20, 12], 2022: [56, 11, 60]}
  // const monthlyBorrowsData= {2021: [19, 20, 30], 2022: [36, 21, 50]}

  export default {
    name: "analytizcs-page",
    data(){
      return {
      selected_year:curr_year,
      options: [
          { value: 2022, text: '2022' },
          { value: 2021, text: '2021' },
        ],
        monthlyLoan: [],
      }
    },
    watch: {
      selected_year() {
        console.log(this.selected_year)
        this.monthlyLoan=  monthlyLoanData[this.selected_year]
      
    }
  },
    created() {
        this.$emit("update:layout", DashboardLayout);
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