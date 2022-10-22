<template>
    <div>
        <h1>Attraction Management</h1>
        <AttractionPasses></AttractionPasses>
    </div>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
import AttractionPasses from '@/components/AdminCorpPassManagement/AttractionPasses.vue';


export default {
    name: "admin-coporate-pass-management-page",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },
    components: { AttractionPasses }
};

</script>


