<template>
    <div>
        <h1>Notifications</h1>
    </div>


</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'


export default {
        name: "admin-coporate-pass-page",

        created() {
            this.$emit('update:layout', DashboardLayout);
         },
    };

</script>


