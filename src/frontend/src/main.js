import '@babel/polyfill'
import 'mutationobserver-shim'

import Vue from 'vue'
import './plugins/bootstrap-vue'
import App from './App.vue'
import router from './router'// loads from src/router/index.js

import './assets/scss/app.scss'

Vue.config.productionTip = false

// configure router

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
