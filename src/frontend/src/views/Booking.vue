<template>
<div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-8" >
         <Calendar fluid></Calendar>
        </div>
        <div class="col-md-4">

        </div>
      </div>
    </div>
  </div>
 </template>
 
 
 <script>
    import DashboardLayout from '../layouts/DashboardLayout'
 import Calendar from '../components/Booking/Calendar'
 
export default {
        name: "booking-page",
        components: { Calendar },
        created() {
            this.$emit('update:layout', DashboardLayout);
         },
        
    };

</script>


