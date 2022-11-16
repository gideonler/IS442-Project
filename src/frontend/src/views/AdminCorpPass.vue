<template>
    <div class="content">
    <div class="container-fluid">
        <div class="row">
            
        </div>
        <div class="row">
            <div class="col-md-7">
                <EditPassForm :key="componentKey" v-on:update-creation="forceRerender"></EditPassForm>
            </div>
            <div class="col-md-5">
                <UserCard :key="componentKey" v-on:update-creation="forceRerender"></UserCard>
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
        data(){
            return{
                componentKey: 0
            }
        },  
        created() {
            this.$emit('update:layout', DashboardLayout);
         },
         components: {
            EditPassForm,
            UserCard,
        },
        methods: {
        forceRerender() {
            this.componentKey+=1
        }}
    };

</script>


