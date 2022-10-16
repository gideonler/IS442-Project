<template>
    <div>
      <b-modal ref="my-modal" header-bg-variant="success" 
        hide-footer
        header-text-variant="light"
        footerTextVariant= "dark"
        id="attraction-creation-confirmation"
        >
        <div class="d-block text-center">
            <h2>Attraction succesfully created.</h2>
            <p>You have created {{attraction}} </p>
        </div>
        <b-button class="mt-3" block  @click="$bvModal.hide('attraction-creation-confirmation')">Ok</b-button>
        </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        name: 'attraction-creation-confirmation-popup',
        
        data() {
            return {
                //TODO: replace dummy data with backend data
             
            };
        },
        created() {
        this.$root.$refs.AttractionCreationConfirmation= this;
        },
      methods: {
        showModal(date, no_passes) {
          this.$refs['my-modal'].show()
          this.date= date
          this.no_passes= no_passes
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
        
      }
    }
  </script>