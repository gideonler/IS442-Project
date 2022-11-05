<template>
  <!-- <div class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-7" >
            <LoginPage></LoginPage>
          </div>
          <div class="col-md-5">
  
          </div>
        </div>
      </div>
    </div> -->
    <div>
      <LoginPage></LoginPage>
    </div>
   </template>
   
   
   <script>
      import LoginPage from '../components/Login/LoginPage.vue'
      
      export default {
              name: "login-page",
              components: { LoginPage}
              // created() {
              //     this.$emit('update:layout', DashboardLayout);
              // },            
          };
  
  </script>  