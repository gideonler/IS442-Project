<template>
    <div>
        <h1>Pass Management</h1>
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
    },
    components: { PassDataTable}
};

</script>


