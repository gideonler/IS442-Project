<template>
    <UsersList></UsersList>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout'
import UsersList from '../components/Users/UsersList.vue';

export default {
    name: "Users-Management",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },

    components: { UsersList },
};

</script>


