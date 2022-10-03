<template>
    <div>
      <b-button id="show-btn" @click="showModal">Open Modal</b-button>
  
      <b-modal ref="my-modal" header-bg-variant="success" 
        hide-footer
        header-text-variant="light"
        footerTextVariant= "dark"
        id="waitinglist-confirmation"
        >
        <div class="d-block text-center">
            <h2>Confirmed!</h2>
            <p>You are on the waiting list for {{pass}} on {{date}}. </p>
        </div>
        <b-button class="mt-3" block  @click="$bvModal.hide('booking-confirmation')">Ok</b-button>
        </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        data() {
            return {
                //TODO: replace dummy data with backend data
                pass: 'Mandai Wildlife Reserve',
                date: '27/09/2022'
            };
        },
      methods: {
        showModal() {
          this.$refs['my-modal'].show()
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
        
      }
    }
  </script>