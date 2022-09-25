import Vue from 'vue'
import VueRouter from  'vue-router'

// Layouts
import TopNavBar from '../layouts/TopNavBar.vue'
// import DashboardLayout from '../layouts/DashboardLayout'

// General Views

// User pages
import Home from '../views/Home.vue'
import Booking from '../views/Booking.vue'
import About from '../views/About.vue'
// import NotFound from '../views/NotFound.vue'
// import Overview from '../views/Overview.vue'

Vue.use(VueRouter);

const routes = [
  {
    path: '/',
    component: TopNavBar,
    redirect: '/home'
  },
  { path: '/home', component: Home },
  { path: '/about', component: About },
  { path: '/booking', component: Booking }
]


// const routes = [
//   {
//     path: '/',
//     component: DashboardLayout,
//     redirect: '/admin/overview'
//   },
//   {
//     path: '/admin',
//     component: DashboardLayout,
//     redirect: '/admin/overview',
//     children: [
//       {
//         path: 'overview',
//         name: 'Overview',
//         component: Overview
//       },
//     { path: '/home', component: Home },
//     { path: '/about', component: About },
//     { path: '/booking', component: Booking }
//     ]
//   },
//   { path: '*', component: NotFound }
// ]

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