<template>
    
    <nav class="navbar navbar-expand-lg">
       
      <div class="container-fluid">
        <img height="65" class="py-1" src="../assets/images/sss_logo1.jpg" alt="">
       
        <button type="button"
                class="navbar-toggler navbar-toggler-right"
                :class="{toggled: $sidebar.showSidebar}"
                aria-controls="navigation-index"
                aria-expanded="false"
                aria-label="Toggle navigation"
                @click="toggleSidebar">
          <span class="navbar-toggler-bar burger-lines"></span>
          <span class="navbar-toggler-bar burger-lines"></span>
          <span class="navbar-toggler-bar burger-lines"></span>
        </button>

        <button type="button"
                class="navbar-toggler navbar-toggler-right">
         
        </button>
        <div class="collapse navbar-collapse justify-content-end">
          <ul class="navbar-nav ml-auto">
            <li class="nav-item">
                
              <a class="nav-link" href="/booking">
                Bookings
              </a>
            </li>
           
            <li class="nav-item">
              <a href="/login" @click='logOut()' class="nav-link">
                Log out
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </template>
  <script>
    export default {
      computed: {
        routeName () {
          const {name} = this.$route
          return this.capitalizeFirstLetter(name)
        }
      },
      data () {
        return {
          activeNotifications: false
        }
      },
      methods: {
        logOut(){
          localStorage.removeItem('user') 
        },
        capitalizeFirstLetter (string) {
          return string.charAt(0).toUpperCase() + string.slice(1)
        },
        toggleNotificationDropDown () {
          this.activeNotifications = !this.activeNotifications
        },
        closeDropDown () {
          this.activeNotifications = false
        },
        toggleSidebar () {
          this.$sidebar.displaySidebar(!this.$sidebar.showSidebar)
        },
        hideSidebar () {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  
  </script>
  <style>

  </style>
  