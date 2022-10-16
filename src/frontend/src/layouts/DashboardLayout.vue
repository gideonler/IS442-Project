<template>
  
    <div class="wrapper">
      <side-bar>
      <mobile-menu slot="content"></mobile-menu>
      <sidebar-link to="/user">
        <i class="nc-icon nc-circle-09"></i>
        <p>User Profile</p>
      </sidebar-link>
      <sidebar-link to="/booking">
        <i class="nc-icon nc-notes"></i>
        <p>My Bookings</p>
      </sidebar-link>
      <sidebar-link to="/analytics">
        <i class="nc-icon nc-chart-bar-32"></i>
        <p>Analytics</p>
      </sidebar-link>
      <sidebar-link to="/corporate-pass-creation">
        <i class="nc-icon nc-badge"></i>
        <p>Create Passes</p>
      </sidebar-link>
      <sidebar-link to="/corporate-pass-management">
        <i class="nc-icon nc-ruler-pencil"></i>
        <p>Manage Passes</p>
      </sidebar-link>
      <sidebar-link to="/notifications">
        <i class="nc-icon nc-bell-55"></i>
        <p>Notifications</p>
      </sidebar-link>

      <template slot="bottom-links">
        <sidebar-link class="active"
                      to="/admin/upgrade">
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
  // import MobileMenu from './MobileMenu.vue'
  

  export default {
    components: {
      TopNavBar,
      ContentFooter,
      DashboardContent,
      // MobileMenu

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
