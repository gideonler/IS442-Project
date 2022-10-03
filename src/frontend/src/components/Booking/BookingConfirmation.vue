<template>
    <div>
      <b-button id="show-btn" @click="showModal">Open Modal</b-button>
  
      <b-modal ref="my-modal" header-bg-variant="success" 
        hide-footer
        header-text-variant="light"
        footerTextVariant= "dark"
        id="booking-confirmation"
        >
        <div class="d-block text-center">
            <h2>Booking Confirmed!</h2>
            <p>You have booked {{no_tickets}} {{pass}} Passses for  {{date}}. </p>
            <p>A confirmation email will be sent to your email. Please bring along the Authorisation Letter attached in the email.</p>
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
                no_tickets: 2,
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