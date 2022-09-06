<template>
  <hello-world/>
</template>
<!-- importing HelloWorld.vue component -->


<script>
import HelloWorld from './components/HelloWorld.vue'

export default {
  name: 'App',
  components: {
    HelloWorld
  }
}
</script>

