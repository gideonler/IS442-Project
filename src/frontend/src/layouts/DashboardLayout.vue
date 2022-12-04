<template>
  
    <div class="wrapper">
      <side-bar :title="this.user">
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link  v-if="(this.user == 'STAFF' || this.user=='ADMIN')" to="/booking">
        <i class="nc-icon nc-notes"></i>
        <p>My Bookings</p>
      </sidebar-link>
      <sidebar-link  v-if="(this.user == 'GENERALOFFICE')" to="/booking">
        <i class="nc-icon nc-notes"></i>
        <p>Booking Calendar</p>
      </sidebar-link>
      <sidebar-link  v-if="(this.user == 'STAFF' || this.user=='ADMIN')" to="/available-attractions">
        <i class="nc-icon nc-bullet-list-67"></i>
        <p>Available Attractions</p>
      </sidebar-link>
      <sidebar-link v-if="this.user=='ADMIN'" to="/analytics">
        <i class="nc-icon nc-chart-bar-32"></i>
        <p>Analytics</p>
      </sidebar-link>
      <sidebar-link v-if="this.user=='ADMIN'" to="/corporate-pass-creation">
        <i class="nc-icon nc-badge"></i>
        <p>Create Passes</p>
      </sidebar-link>
      <sidebar-link v-if="this.user=='ADMIN'" to="/corporate-pass-management">
        <i class="nc-icon nc-ruler-pencil"></i>
        <p>Manage Passes</p>
      </sidebar-link>
      <sidebar-link v-if="this.user == 'ADMIN'" to="/users">
        <i class="nc-icon nc-circle-09"></i>
        <p>Manage Employees</p>
      </sidebar-link>
      <sidebar-link v-if="this.user == 'GENERALOFFICE'" to="/gopass">
        <i class="nc-icon nc-badge"></i>
        <p>Passes Collection</p>
      </sidebar-link>
      <sidebar-link v-if="this.user == 'STAFF'" to="/pass-details">
        <i class="nc-icon nc-notes"></i>
        <p>Manage Passes</p>
      </sidebar-link>

      <template slot="bottom-links">
        <sidebar-link class="active"
                      to="/booking">
          <i class="nc-icon nc-notes"></i>
          <p>Book Now</p>
        </sidebar-link>
      </template>
    </side-bar> 
  
    <div class="main-panel">
      <top-nav-bar></top-nav-bar>
      <dashboard-content  @click="toggleSidebar">
      </dashboard-content>

      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
  import TopNavBar from './TopNavBar'
  import ContentFooter from './ContentFooter.vue'
  import DashboardContent from './Content.vue'
  import MobileMenu from './MobileMenu.vue'
  

  export default {
    components: {
      TopNavBar,
      ContentFooter,
      DashboardContent,
      MobileMenu
    },
    created(){
      this.user= JSON.parse(localStorage.getItem('user')).userType.authority
    },
    data(){
      return {
        user: '',
      }
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>
