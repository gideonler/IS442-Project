<template>
    <!-- <div>
        <div v-if="display" class="row">
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body text-center">
                        <h5 class="card-title">Users List</h5>
                        <p class="card-text mt-2">Shows all the users in the system</p>
                        <a href="#" class="btn btn-primary" @click="users()">See all users</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body text-center">
                        <h5 class="card-title">Staff List</h5>
                        <p class="card-text mt-2">Shows all the staff in the system</p>
                        <a href="#" class="btn btn-primary" @click="staff()">See all staff</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body text-center">
                        <h5 class="card-title">GO List</h5>
                        <p class="card-text mt-2">Shows all the GO in the system</p>
                        <a href="#" class="btn btn-primary">See all GO</a>
                    </div>
                </div>
            </div>
            <div class="col-sm-6">
                <div class="card">
                    <div class="card-body text-center">
                        <h5 class="card-title">Admin List</h5>
                        <p class="card-text mt-2">Shows all the admin in the system</p>
                        <a href="#" class="btn btn-primary">See all admin</a>
                    </div>
                </div>
            </div>
        </div>

        <div v-if="users">
            <UsersList></UsersList>
        </div>
    </div> -->
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

    data() {
        return {
            // componentKey: 0,
            // display: true,
            // users: false
        };
    },

    methods: {
        // staff() {
        //     this.$router.push();
        // },

        users() {
            this.display = false,
            this.users = true
        },
    }
};

</script>


