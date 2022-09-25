<template>

  <h1>About us</h1>
  
</template>

<script>
import DashboardLayout from '../layouts/DashboardLayout'

  export default {
    name: "about-page",
    created() {
            this.$emit('update:layout', DashboardLayout);
         },
  }
</script>