import '@babel/polyfill'
import 'mutationobserver-shim'

import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'// loads from src/router/index.js

// LightBootstrap plugin
import LightBootstrap from './light-bootstrap-main'

import './assets/scss/app.scss'
import './assets/sass/light-bootstrap-dashboard.scss'

Vue.use(LightBootstrap)

Vue.config.productionTip = false

// configure router

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')