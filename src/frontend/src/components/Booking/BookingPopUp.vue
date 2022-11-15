<template>
    <div>
  
      <b-modal ref="my-modal" 
      hide-footer title="Book New Pass">
        <div class="d-block text-center">
          <h3>There are {{no_avail}} available passes on {{selected_date}} </h3>
          <ul>
            <li>You can only borrow 1 day to 8 weeks prior visitation.</li>
            <li>You may borrow a maximum of 2 passes each time.</li>
          </ul>
         
        </div>
        <form ref="form" @submit.stop.prevent="handleSubmit">
        <b-form-group
        >
          <b-form-input
            :value= "pass_type"
            disabled
          ></b-form-input>
          <b-form-input
            id="no_passes"
            type="number"
            min="1"
            max="2"
            v-model="no_passes"
            placeholder="Enter No. of Passes to Book"
            :state="nameState"
            required
          ></b-form-input>
        </b-form-group>
  
      </form>
        
        <b-button class="mt-2" variant="success" block @click="confirmBooking(date, no_passes)">Confirm Booking</b-button>
      </b-modal>

      
    </div>
  </template>
  
  <script>
  import axios from 'axios';
    export default {
        name: 'booking-popup',
        props:['selected_date', 'no_avail', 'attraction_name'],
        data() {
            return {
                api: { 
                  create_booking: "http://localhost:8080/loan/book"
                },
                no_passes:null,
                pass_type:null,
            };
        },
        created() {
        this.$root.$refs.BookingPopUp = this;
        },
      methods: {
        updatePassType(pass_type){
          this.pass_type= pass_type
        },  
        showModal(date) {
          this.date= date
          this.$refs['my-modal'].show()
        },
        hideModal() {
          this.$refs['my-modal'].hide()
        },
        async confirmBooking(date, no_passes) {
          await axios 
            .post(this.api.create_booking, 
              {
                "loanDate" : this.date,

                //TO DO: REPLACE WITH USER EMAIL. CAN GET FROM API.
                "userEmail" : parseFloat(this.replacementfee),
                "attractionName" : this.attraction_name,
                "noOfPass": this.no_passes
              }
              )
            .then((response) => {
              console.log(response);
            }); 

          // close current modal
          this.$refs['my-modal'].toggle('#toggle-btn')

          //show confirmation message
          this.$root.$refs.BookingConfirmation.showModal(date, no_passes);
        }
      }
    }
  </script>