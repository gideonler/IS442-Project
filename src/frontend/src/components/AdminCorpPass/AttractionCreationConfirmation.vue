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
               attraction: null,
            };
        },
        created() {
        this.$root.$refs.AttractionCreationConfirmation= this;
        },
      methods: {
        showModal(selected_attraction) {
          this.$refs['my-modal'].show()
          this.attraction= selected_attraction
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
        
      }
    }
  </script>