<template>
    <div>
      <b-modal v-if="(this.action=='Deactivation')" ref="my-modal" header-bg-variant="danger" 
      header-text-variant="light"
      footerTextVariant= "dark">

        <div class="d-block text-center">
            <h2>{{action}}</h2>
            <p>Confirm {{action}} of {{attraction}} ?</p>
            
        </div>

        <template  #modal-footer="{ cancel}" >
      <!-- Emulate built in modal footer ok and cancel button actions -->
            <b-button class="ml-5" size="sm" variant="success" @click="confirm">
                Confirm
            </b-button>
            <b-button class="mr-5" size="sm" variant="outline-secondary" @click="cancel()">
                No
            </b-button>
        
            </template>

      </b-modal>

      <b-modal v-if="(this.action=='Reactivation')" ref="my-modal" header-bg-variant="success" 
      header-text-variant="light"
      footerTextVariant= "dark">

        <div class="d-block text-center">
            <h2>{{action}}</h2>
            <p>Confirm {{action}} of {{attraction}} ?</p>
            
        </div>

        <template  #modal-footer="{ cancel}" >
      <!-- Emulate built in modal footer ok and cancel button actions -->
            <b-button class="ml-5" size="sm" variant="success" @click="confirm">
                Confirm
            </b-button>
            <b-button class="mr-5" size="sm" variant="outline-secondary" @click="cancel()">
                No
            </b-button>
        
            </template>

      </b-modal>
      
    </div>
  </template>
  
  <script>
    export default {
      name: 'attraction-status-change-pop-up',
      props:['action', 'attraction'],
      data() {
          return {
              //TODO: replace dummy data with backend data
              date: null,
              pass_type:null
          };
      },
      created() {
        this.$root.$refs.AttractionStatusChangePopUp= this;
        },
    methods: {
      confirm(){
        this.$refs['my-modal'].hide()
        this.$emit('confirm-update-status')
      },
      showModal(date) {
        this.$refs['my-modal'].show()
        this.date= date;
      }
    }
  }
  </script>