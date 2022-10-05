<template>
    <div>
        <h2>Pass Management</h2>
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


