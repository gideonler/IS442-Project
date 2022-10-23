<template>
    <div>
      <b-modal ref="edit-attraction-confirmation-modal" header-bg-variant="success" 
        hide-footer
        header-text-variant="light"
        footerTextVariant= "dark"
        id="attraction-edit-confirmation"
        >
        <div class="d-block text-center">
            <h2>Attraction succesfully updated.</h2>
            <p>You have updated {{attraction}} </p>
        </div>
        <b-button class="mt-3" block  @click="hideModal()">Ok</b-button>
        </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        name: 'attraction-edit-confirmation-popup',
        
        data() {
            return {
               attraction: null,
            };
        },
        created() {
        this.$root.$refs.AttractionEditConfirmation= this;
        },
      methods: {
        showConfirmModal(selected_attraction) {
          this.$refs['edit-attraction-confirmation-modal'].show()
          this.attraction= selected_attraction
        },
        hideModal() {
          this.$bvModal.hide('edit-attraction-modal')
          this.$refs['edit-attraction-confirmation-modal'].hide()
        },
        
      }
    }
  </script>