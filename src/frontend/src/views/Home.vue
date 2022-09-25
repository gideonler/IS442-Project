<template>
    <div class="Home">
        <h1>Home</h1>
    </div>


</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'


export default {
        name: "home-page",

        created() {
            this.$emit('update:layout', DashboardLayout);
         },
    };

</script>


