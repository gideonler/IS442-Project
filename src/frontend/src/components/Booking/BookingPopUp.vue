<template>
    <div>
  
      <b-modal ref="book-new-pass-modal" 
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
            :value= "this.attraction_name"
            disabled
          ></b-form-input>
          <b-form-input
            id="no_passes"
            type="number"
            min="1"
            max="2"
            v-model="no_passes"
            placeholder="Enter No. of Passes to Book"
            required
          ></b-form-input>
        </b-form-group>
  
      </form>
        
        <b-button class="mt-2" variant="success" block @click="confirmBooking( no_passes)">Confirm Booking</b-button>
      </b-modal>
    <div hidden>
      <booking-reply></booking-reply>
    </div>
</div>
  </template>
  
  <script>
  import axios from 'axios';
  import BookingReply from './BookingReply.vue';
    export default {
    components: { BookingReply },
        name: 'booking-popup',
        props:['selected_date', 'no_avail', 'attraction_name'],
        data() {
            return {
                user: '',
                user_email :'',
                api: { 
                  create_booking: "http://localhost:8080/loan/book",
                  get_booking_count: 'http://localhost:8080/loan/getbookingcount',
                  get_bookings: 'http://localhost:8080/loan/',
                },
                no_passes:1,
                pass_type:null,
                loan_count: null,
                loans_in_month: {},
            };
        },
        created() {
          this.user = JSON.parse(localStorage.getItem('user'));
          this.user_email= this.user.username
          this.$root.$refs.BookingPopUp = this;
        },
        mounted(){
          // this.checkUserBookings()
          this.getBookings()
        },
        computed:{
          hasExceeededForMonth(){
            var month = this.selected_date.substring(0,7)
            if(this.loans_in_month[month]>=2){
              return true
            }
            return false
          }
    },
      methods: {
        updatePassType(pass_type){
          this.pass_type= pass_type
        },  
        showModal(date) {
          this.date= date
          this.$refs['book-new-pass-modal'].show()
        },
        hideModal() {
          this.$refs['book-new-pass-modal'].hide()
        },
        containsKey(obj, key ) {
        return Object.keys(obj).includes(String(key));
        },
        // async checkUserBookings(){
        //   await axios
        //   .get(this.api.get_booking_count,{
        //     params: {
        //       userEmail:  this.user_email
        //     }})
        //   .then((response) => {
        //     this.loan_count = response.data
        //   })
        //   .catch((error) => {
        //       if (error) {
        //           console.log(error);
        //       }
        //   });
        // },
        async getBookings() {
        await axios
            //change to user email
            .get(this.api.get_bookings + this.user_email)
            .then((response) => {
                // console.log(response.data)
                var loans_in_month= {}
                this.curr_bookings = response.data;
                var unique_ids= [];
                for(var booking of this.curr_bookings){
                  var month = booking.loanDate.substring(0,7)
                  var loanId = booking.loanID
                  var status= booking.status;
                  if(status== "CONFIRMED" && this.containsKey(loans_in_month, month) && (!unique_ids.includes(loanId))){
                    //alr exists and is unique
                    loans_in_month[month]+= 1
                    unique_ids.push(loanId)
                  }else if (status== "CONFIRMED") {
                    loans_in_month[month]= 1 
                }
                this.loans_in_month= loans_in_month
              }
              })
            .catch((error) => {
                console.log(error.response);
            });
        },
         async confirmBooking(no_passes) {
          //check if passes has been exceeded
          if(this.hasExceeededForMonth){
            this.$refs['book-new-pass-modal'].toggle('#toggle-btn')

            this.$root.$refs.BookingReply.showModal(
              "Unable to book. Max loans have been met for this month."
              ) 
          }
          else{
            await axios 
            .post(this.api.create_booking, 
              {
                "loanDate" : this.selected_date,
                "userEmail" : this.user_email,
                "attractionName" : this.attraction_name,
                "noOfPass": this.no_passes
              }
              )
            .then((response) => {
              // console.log(response.data)
              var status = response.data
              // console.log(response.data)
              // status.includes("unsuccessful") ? this.$root.$refs.BookingReply.showModal(status) :  this.$root.$refs.BookingConfirmation.showModal(this.selected_date, no_passes, this.attraction_name)
              this.$root.$refs.BookingConfirmation.showModal(this.selected_date, no_passes, this.attraction_name)
              this.$refs['book-new-pass-modal'].toggle('#toggle-btn')
            })
            .catch((err)=>{
              console.log(err)
              this.$root.$refs.BookingReply.showModal('Booking Unsuccessful. Try again later')
              this.$refs['book-new-pass-modal'].toggle('#toggle-btn')
            });
            ; 

          }

         
          // close current modal

          //show confirmation message
          // this.$root.$refs.BookingConfirmation.showModal(this.selected_date, no_passes);
        }
      }
    }
  </script>