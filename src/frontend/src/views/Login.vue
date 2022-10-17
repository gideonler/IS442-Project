<template>
    <div class="Login">
        <LoginPage></LoginPage>
    </div>


</template>


<script>
import LoginPage from '../components/Login/LoginPage'


export default {
        name: "login-page",
        components: {LoginPage}

        // created() {
        //     this.$emit('update:layout', DashboardLayout);
        //  },
    };

</script>


