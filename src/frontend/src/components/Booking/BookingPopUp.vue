<template>
    <div>
  
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
        
        <b-button class="mt-2" variant="success" block @click="confirmBooking(date)">Confirm Booking</b-button>
      </b-modal>

      
    </div>
  </template>
  
  <script>
    export default {
        name: 'booking-popup',
        data() {
            return {
                //TODO: replace dummy data with backend data
                no_avail: 3,
                date: null,
            };
        },
        created() {
        this.$root.$refs.BookingPopUp = this;
        },
      methods: {
        showModal(date) {
          this.date= date
          this.$refs['my-modal'].show()
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
        confirmBooking(date) {
          // close current modal
          this.$refs['my-modal'].toggle('#toggle-btn')

          //TODO: Send to backend to confirm booking- update db

          //show confirmation message
          this.$root.$refs.BookingConfirmation.showModal(date);
        }
      }
    }
  </script>