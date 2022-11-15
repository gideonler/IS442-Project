<template>
    <div>
        <h1>Pass Management</h1>
        <PassStatus></PassStatus>
    </div>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
import PassStatus from '../components/GOPassManagement/PassStatus.vue';

export default {
    data() {
      return {
        // componentKey: 0,
      };
    },
    name: "GO-pass-status",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },
    components: { PassStatus },
};

</script>


