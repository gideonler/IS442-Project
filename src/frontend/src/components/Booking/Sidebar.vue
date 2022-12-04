<template>
    <!-- Vertical navbar -->
    <div class="vertical-nav bg-light fixed-right" id="sidebar" style="padding-bottom: 10%;">

        <!-- <p class="bg-dark text-white font-weight-bold text-uppercase px-3">Bookings This Month</p>

        <p class="px-3"> You have {{ number }} bookings in this month </p> -->

        <p class="bg-dark text-white font-weight-bold text-uppercase px-3">{{nameOfMonth}} Bookings</p>

        <p class="px-3"> You have {{ number }} bookings in this month </p>

        <!-- <div class="scroll px-3 bg-booking " tabindex="0"> -->
        <div>
            <div v-if="bookingList.length > 1">
                <!-- <ul class="no-bullets" v-for="(booking, b) in bookingList.filter(booking => booking.status != 'CANCELLED' && booking.loanDate.substring(5, 7) == monthNumber)" :key="b"> -->
                <ul class="no-bullets" v-for="(booking, b) in bookingList.filter(booking => booking.loanDate.substring(5, 7) == monthNumber)" :key="b">
                    <li>Attraction Name: {{ booking.attractionName }}</li>
                    <li>Loan Date: {{ booking.loanDate.split("T")[0] }}</li>
                    <li>Pass ID: {{ booking.passId }}</li>
                    <li>Booking Status: {{ booking.status }}</li>
                    <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="danger" @click="sendInfo(booking.loanID)" v-if="booking.status != 'CANCELLED'">
                        Cancel booking
                    </b-button>
                    <hr>
                </ul>
            </div>
            <div class="px-3" v-else>No upcoming bookings</div>
        </div>

        <b-modal id="modal-1" title="Booking Cancellation" alignment="center">
            <p class="my-4">Are you sure you want to cancel your booking?</p>
            <template #modal-footer>
                <b-button variant="danger" @click="$bvModal.hide('modal-1')">No</b-button>
                <b-button variant="primary" @click="cancelBooking(bookingId); $bvModal.hide('modal-1')">Yes</b-button>
            </template>
        </b-modal>

        <p class="bg-dark text-white font-weight-bold text-uppercase px-3">Note</p>
        <p class="px-3">Click <a href="/pass-details"> here </a> for more info regarding the passes</p>

        <p class="bg-dark text-white font-weight-bold text-uppercase px-3">Contact</p>
        <p class="px-3"> General Office Phone: +65 6766 0100</p>
<!-- 
        <br>
        <br>
        <br> -->
    </div>
</template>
  
<script>
import axios from "axios";
import moment from "moment";

function dateFormat(date) {
    var dateSplit = date.split("/");
    var dateFormatted = dateSplit[2] + "-" + dateSplit[1] + "-" + dateSplit[0];
    return dateFormatted;
}

const nameOfMonth = new Date().toLocaleString(
  'default', {month: 'long'}
);

var months  = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
const monthNumber = months.indexOf(nameOfMonth) + 1;

export default {
    name: 'SideBar',
    props: {
        msg: String
    },
    data() {
        return {
            nameOfMonth,
            monthNumber,
            number: "",
            month: "",
            user: "",
            userEmail: "",
            bookingList: [{ attractionName: "", loanDate: "", passId: "", status: "", loanID: "" }],
            bookingId: "",
            api: {
                numberBooking: "http://localhost:8080/loan/getbookingcount/",
                bookingList: "http://localhost:8080/loan/",
                passStatus: "http://localhost:8080/passstatus/change",
                cancelBooking: "http://localhost:8080/loan/cancel"
            }
        }
    },

    created() {
        this.user = JSON.parse(localStorage.getItem('user'));
        this.userEmail= this.user.username
        console.log(this.user)
    },

    mounted() {
        this.numberBookings();
        this.getBookings();
    },

    methods: {
        numberBookings() {
            return axios
                .get(this.api.numberBooking, {
                    params: {
                        Authorization: "Bearer " + this.user.jwt,
                        userEmail: this.userEmail
                    }
                })
                .then((response) => {
                    console.log(response.data)
                    this.number = response.data;
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },

        getBookings() {
            return axios
                .get(this.api.bookingList + this.userEmail, {
                    params: {
                        Authorization: "Bearer " + this.user.jwt,
                    }
                })
                .then((response) => {
                    console.log(response.data)
                    this.bookingList = response.data;
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },

        sendInfo(loanID) {
            this.bookingId = loanID;
        },

        cancelBooking(bookingId) {
            console.log(bookingId)

            var loanDate = moment(dateFormat(bookingId.substr(0, 10)));

            var current = new Date();
            var currentDate = `${current.getDate()}/${current.getMonth() + 1}/${current.getFullYear()}`;
            var currentDate = moment(dateFormat(currentDate));

            var differenceDay = loanDate.diff(currentDate, 'days');
            if (differenceDay < 1) {
                this.$alert("You cannot cancel your booking less than 1 day before the trip");
            } else {
                return axios
                .get(this.api.cancelBooking, {
                    params: {
                        loanId: bookingId
                    }
                })
                .then((response) => {
                    console.log(response.data);
                    this.$alert("Booking cancelled successfully");
                    window.location.reload();
                })
                .catch((error) => {
                    console.log(error.response);
                });
            }
        }
    }
}

</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.vertical-nav {
    min-width: 10rem;
    margin-top: 3%;
    width: 100%;
    height: 53%;
    right: 0;
    box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.1);
    transition: all 0.4s;
    /* overflow: auto; */
}

.no-bullets {
    list-style-type: none;
    margin: 0;
    padding-top: 10px;
    padding-left: 10px;
    background-color: rgb(251, 234, 213);
}

.scroll {
    position: relative;
    height: 48%;
    overflow: auto;
}

.text-uppercase {
    letter-spacing: 0.1em;
}
</style>