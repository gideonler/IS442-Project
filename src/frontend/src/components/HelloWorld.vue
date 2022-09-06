<template>
  <h1>{{msg}}</h1>
</template>
<!-- msg displayed at the top and defaulted to an empty string -->
<script>
export default {
  name: 'HelloWorld',
  data(){
    return{
      msg: ''
    }
  },
  mounted(){
    fetch("/api/messages/hello")
    .then((response)=>response.text())
    .then((data)=>{
      this.msg=data;

    })
  }
}
</script>

