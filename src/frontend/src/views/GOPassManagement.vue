<template>
    <div>
        <h2>Pass Management</h2>
        <PassStatus></PassStatus>
    </div>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
// import PassStatus from '../components/GOPassManagement/PassStatus.vue';
import PassStatus from '../components/GOPassManagement/PassList.vue';

export default {

    name: "GO-pass-status",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },
    components: { PassStatus },
};

</script>


