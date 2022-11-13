<template>
    <div>
        <h1>Attraction Management</h1>
        <AttractionPasses v-on:update-attraction="forceRerender" :key="componentKey"></AttractionPasses>
    </div>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
import AttractionPasses from '@/components/AdminCorpPassManagement/AttractionPasses.vue';


export default {
    data() {
      return {
        componentKey: 0,
      };
    },
    name: "admin-coporate-pass-management-page",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },
    components: { AttractionPasses },
    methods: {
      forceRerender() {
        console.log('helo')
        this.componentKey += 1;
      }
    }
};

</script>


