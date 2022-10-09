<template>
  <div class="sticky-footer">
    <footer class="footer mt-auto">
      <div class="container-fluid">
        <!-- <nav>
          <ul class="footer-menu">
            <li>
              <router-link :to="{path:'/booking'}">Booking</router-link>
            </li>
          </ul>
        </nav> -->
        <div class="copyright text-center">
          &copy; Singapore Sports School.
          Designed by G2T4.
        </div>
      </div>
    </footer>
  </div>


</template>
<script>
export default {}

</script>

<style>
.sticky-footer {
  position: fixed;
  bottom: 0;
  right: 0;
  width: 100%;
}
</style>