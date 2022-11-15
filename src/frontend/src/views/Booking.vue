<template>
<div class="content">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-7" >
          <CalendarFilter></CalendarFilter>
         <Calendar fluid></Calendar>
        
        </div>
        <div class="col-md-5">
          <Sidebar></Sidebar>
        </div>
      </div>
    </div>
  </div>
 </template>
 
 
 <script>
    import DashboardLayout from '../layouts/DashboardLayout'
    import Calendar from '../components/Booking/Calendar'
    import CalendarFilter from '../components/Booking/CalendarFilter.vue';
    import Sidebar from '../components/Booking/Sidebar.vue';
    
    export default {
            name: "booking-page",
            components: { Calendar, CalendarFilter, Sidebar },
            created() {
                this.$emit('update:layout', DashboardLayout);
            },
            
        };

</script>


