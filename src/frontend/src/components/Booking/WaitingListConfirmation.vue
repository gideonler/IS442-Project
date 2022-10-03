<template>
    <div>
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
        <b-button class="mt-3" block  @click="$bvModal.hide('waitinglist-confirmation')">Ok</b-button>
        </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        name: 'waitinglist-confirmation-popup',
        data() {
            return {
                //TODO: replace dummy data with backend data
                pass: 'Mandai Wildlife Reserve',
                date: null
            };
        },
        created() {
        this.$root.$refs.WaitingListConfirmation= this;
        },
      methods: {
        showModal(date) {
          this.$refs['my-modal'].show()
          this.date= date;
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
      }
    }
  </script>