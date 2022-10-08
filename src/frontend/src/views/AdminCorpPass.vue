<template>
    <div class="content">
    <div class="container-fluid">
        <h2>Pass Management</h2>
        <div class="row">
            
        </div>
        <div class="row">
            <div class="col-md-8">
                <EditPassForm></EditPassForm>
            </div>
            <div class="col-md-4">
            <UserCard></UserCard>
            </div>
        </div>
        </div>
  </div>


</template>


<script>
    import DashboardLayout from '../layouts/DashboardLayout'
    import EditPassForm from '../components/AdminCorpPass/EditPassForm.vue'
    import UserCard from '../components/AdminCorpPass/UserCard.vue'

export default {
        name: "admin-coporate-pass-page",

        created() {
            this.$emit('update:layout', DashboardLayout);
         },
         components: {
            EditPassForm,
            UserCard,
        }
    };

</script>


