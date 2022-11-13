<template>
    <div>
        <h1>Pass Management</h1>
        <h3>{{placeOfInterest}}</h3>
        <PassDataTable></PassDataTable>
    </div>


</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
import PassDataTable from '@/components/AdminCorpPassManagement/PassDataTable.vue';

export default {
    name: "admin-coporate-pass-display-page",
    created() {
        this.$emit("update:layout", DashboardLayout);
        this.placeOfInterest= this.$route.params.name;
    },
    components: { PassDataTable},
    watch: {
    $route(to, from) {
      // if anything needs to be done when the route changes
    },
    beforeRouteUpdate(to, from, next) {
        this.placeOfInterest= to.params.name;
        next()
  }
    
  }
};

</script>


