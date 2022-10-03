<template>
    <div>
      <b-button id="show-btn" @click="showModal">Open Modal</b-button>
  
      <b-modal ref="my-modal" header-bg-variant="danger" 

      header-text-variant="light"
      footerTextVariant= "dark"
      >
        <div class="d-block text-center">
            <h2>Oops!</h2>
            <p>Passes are fully booked on {{date}}. 
            <br>Would you like to be on the Waiting List?</p>
        </div>

        <template  #modal-footer="{ ok, cancel}" >
      <!-- Emulate built in modal footer ok and cancel button actions -->

         
            <b-button class="ml-5" size="sm" variant="success" @click="ok()">
                Yes, I would like to!
            </b-button>
            <b-button class="mr-5" size="sm" variant="outline-secondary" @click="cancel()">
                No, it's okay.
            </b-button>
        
            </template>

      </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        data() {
            return {
                //TODO: replace dummy data with backend data
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