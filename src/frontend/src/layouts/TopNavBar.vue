<template>
   <div>
  <b-navbar type="dark" variant="dark">
    <b-navbar-nav>
      <router-link to="/" class="nav-link">Home</router-link>
      <router-link to="/booking" class="nav-link">Booking</router-link>
      <router-link to="/about" class="nav-link">About</router-link>

      <b-nav-item-dropdown text="User" right>
        <b-dropdown-item href="#">Account</b-dropdown-item>
        <b-dropdown-item href="#">Settings</b-dropdown-item>
      </b-nav-item-dropdown>
    </b-navbar-nav>
  </b-navbar>
  <slot></slot>
</div>
</template>
<script>
    export default {}
    
  </script>