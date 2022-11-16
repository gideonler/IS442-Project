<template>
    <div>
      <b-modal ref="my-modal" 
        hide-footer
        footerTextVariant= "dark"
        id="booking-confirmation"
        >
        <div class="d-block text-center">
            <h3>{{message}}</h3>
        </div>
        <b-button class="mt-3" block  @click="$bvModal.hide('booking-confirmation')">Ok</b-button>
        </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        name: 'booking-reply-popup',
        
        data() {
            return {
                //TODO: replace dummy data with backend data
                // no_passes: null,
                // pass: 'Mandai Wildlife Reserve',
                // date: null
                message:''
            };
        },
        created() {
        this.$root.$refs.BookingReply= this;
        },
      methods: {
        showModal(message) {
          this.$refs['my-modal'].show()
          this.message= message
        //   this.no_passes= no_passes
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
        
      }
    }
  </script>