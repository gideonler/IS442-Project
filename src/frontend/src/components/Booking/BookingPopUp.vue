<template>
    <div>
      <b-button id="show-btn" @click="showModal">Open Modal</b-button>
  
      <b-modal ref="my-modal" 
      hide-footer title="Book New Pass">
        <div class="d-block text-center">
          <h3>There are {{no_avail}} available passes on {{date}} </h3>
          <ul>
            <li>You can only borrow 1 day to 8 weeks prior visitation.</li>
            <li>You may borrow a maximum of 2 passes each time.</li>
          </ul>
         
        </div>
        <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
        >
          <b-form-input
            id="no_passes"
            type="number"
            min="1"
            max="2"
            v-model="name"
            placeholder="Enter No. of Passes to Book"
            :state="nameState"
            required
          ></b-form-input>
        </b-form-group>
  
      </form>
        
        <b-button class="mt-2" variant="success" block @click="toggleModal">Confirm Booking</b-button>
      </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        data() {
            return {
                //TODO: replace dummy data with backend data
                no_avail: 3,
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
        toggleModal() {
          // We pass the ID of the button that we want to return focus to
          // when the modal has hidden
          this.$refs['my-modal'].toggle('#toggle-btn')
        }
      }
    }
  </script>