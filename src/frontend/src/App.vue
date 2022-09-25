<template>
    <div id="app">
      <component :is="layout">
        <router-view :layout.sync="layout"/>
      </component>
    </div>
  </template>
  <script>
  
  export default {
    name: 'App',
    data() {
      return {
        layout: 'div',
      }
    },
    components: {
      
    }
  }
  </script>
  
  <style lang="scss">
     .vue-notifyjs.notifications{
    .list-move {
      transition: transform 0.3s, opacity 0.4s;
    }
    .list-item {
      display: inline-block;
      margin-right: 10px;

    }
    .list-enter-active {
      transition: transform 0.2s ease-in, opacity 0.4s ease-in;
    }
    .list-leave-active {
      transition: transform 1s ease-out, opacity 0.4s ease-out;
    }

    .list-enter {
      opacity: 0;
      transform: scale(1.1);

    }
    .list-leave-to {
      opacity: 0;
      transform: scale(1.2, 0.7);
    }
  }
  @import "~@/assets/scss/app";
  
  #app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
  }
  
  </style>
  