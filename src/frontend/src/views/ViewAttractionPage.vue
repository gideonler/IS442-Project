<template>
    <div>
        <h1>Available Attractions</h1>
        <ViewAttractions ></ViewAttractions>
    </div>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
import ViewAttractions from '@/components/Booking/ViewAttractions.vue';

export default {
    data() {
      return {
        componentKey: 0,
      };
    },
    name: "view-attraction-page",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },
    components: { ViewAttractions },
    methods: {
      forceRerender() {
        console.log('helo')
        this.componentKey += 1;
      }
    }
};

</script>


