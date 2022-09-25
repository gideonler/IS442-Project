<template>
    <TopNavBar>
        <Calendar class="p-5"></Calendar>
    </TopNavBar>
 </template>
 
 
 <script>
 
 import TopNavBar from '../layouts/TopNavBar'
 import Calendar from '../components/Booking/Calendar'



export default {
        name: "booking-page",
        components: { TopNavBar, Calendar }
        
    };

</script>


