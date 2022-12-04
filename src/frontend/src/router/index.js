import Vue from 'vue'
import VueRouter from  'vue-router'

// Layouts
import TopNavBar from '../layouts/TopNavBar.vue'
// import DashboardLayout from '../layouts/DashboardLayout'

// General Views

// User pages
import Notifications from '../views/Notifications.vue'
import Booking from '../views/Booking.vue'
import Analytics from '../views/Analytics.vue'
import AdminCorpPass from '../views/AdminCorpPass.vue'
import AdminCorpPassManagement from '../views/AdminCorpPassManagement.vue'
import AdminCorpPassDisplay from '../views/AdminCorpPassDisplay.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Authentication from '../views/Authentication.vue'
import GOPass from '../views/GOPassManagement.vue'
import UsersManagement from '../views/UsersManagement.vue'
import PassDetails from '../views/PassDetails.vue'
import ViewAttractions from '../views/ViewAttractionPage.vue'
// import NotFound from '../views/NotFound.vue'
// import Overview from '../views/Overview.vue'


Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: TopNavBar,
    redirect: '/booking'
  },
  { path: '/notifications', component: Notifications },
  { path: '/analytics', component: Analytics},
  { path: '/booking', component: Booking, name: 'Booking' },
  { path: '/available-attractions', component: ViewAttractions },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/authentication', component: Authentication },
  { path: '/corporate-pass-creation', component: AdminCorpPass},
  { path: '/corporate-pass-management', component: AdminCorpPassManagement},
  { path: '/corporate-pass-management/passes/:name', component: AdminCorpPassDisplay}, ,
  { path: '/register', component: Register },
  { path: '/gopass', component: GOPass },
  { path: '/users', component: UsersManagement },
  { path: '/pass-details', component: PassDetails },
]

const router = new VueRouter({
  mode: 'history',
  routes, // short for routes: routes
  linkActiveClass: 'nav-item active',
  scrollBehavior: (to) => {
    if (to.hash) {
      return {selector: to.hash}
    } else {
      return { x: 0, y: 0 }
    }
  }
})

export default router