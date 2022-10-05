<template>
    <div>
      <b-modal ref="my-modal" header-bg-variant="danger" 
      header-text-variant="light"
      footerTextVariant= "dark"
      >
        <div class="d-block text-center">
            <h2>Oops!</h2>
            <p>Passes are fully booked for <b>{{pass_type}}</b> on <b>{{date}}</b>. 
            <br>Would you like to be on the Waiting List?</p>
        </div>

        <template  #modal-footer="{ cancel}" >
      <!-- Emulate built in modal footer ok and cancel button actions -->
            <b-button class="ml-5" size="sm" variant="success" @click="confirmWaitinglist(date)">
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
      name: 'waitinglist-popup',
      data() {
          return {
              //TODO: replace dummy data with backend data
              date: null,
              pass_type:null
          };
      },
      created() {
        this.$root.$refs.WaitingListPopUp= this;
        },
    methods: {
      updatePassType(pass_type){
          this.pass_type= pass_type
        }, 
      showModal(date) {
        this.$refs['my-modal'].show()
        this.date= date;
      },
      confirmWaitinglist(date) {
          // close current modal
          this.$refs['my-modal'].hide()

          //TODO: Send to backend to confirm booking- update db

          //show confirmation message
          this.$root.$refs.WaitingListConfirmation.showModal(date, this.pass_type);
        }
      
    }
  }
  </script>