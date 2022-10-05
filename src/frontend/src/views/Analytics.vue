<template>
<div class="content">
  <h1>Analytics Dashboard</h1>
  <div class="container-fluid">
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
              <i class="fa fa-calendar-o"></i>{{selected_year}}
            </div>
          </StatsCard>
        </div>
        <div class="col-xl-3 mb-3 first">
          <strong>For the Year of</strong>
          <b-form-select v-model="selected_year" :options="options"></b-form-select>
        </div>
      </div>
      <div class="row">
        <MonthlyLoanChart class="col-lg-6"></MonthlyLoanChart>
        <LoanPerEmployeeChart class="col-lg-6"></LoanPerEmployeeChart>
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
  console.log(curr_year)
  export default {
    name: "analytics-page",
    data(){
      return {
      selected_year:curr_year,
      options: [
          { value: '2022', text: '2022' },
          { value: '2021', text: '2021' },
        ]
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