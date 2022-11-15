<template>
    <!-- Vertical navbar -->
    <div class="vertical-nav bg-white fixed-right" id="sidebar">

        <p class="bg-light text-gray font-weight-bold text-uppercase px-3 py-2 mb-0">Bookings</p>

        <p class="px-3 mb-0"> You have {{ number }} bookings left for {{ month }} </p>

        <p class="bg-light text-gray font-weight-bold text-uppercase px-3 py-3 mb-0">Current Bookings</p>

        <div data-bs-spy="scroll" data-bs-target="#navbar-example2" data-bs-offset="0" class="scrollspy px-3"
            tabindex="0">
            <ul class="nav flex-column bg-white mb-0 overflow-auto">
                <li class="nav-item">
                    <div class="mt-3 ms-2 me-auto">
                        <div class="row">
                            <div class="col fw-bold centered">Booking 2</div>
                            <div class="col">
                                <b-button v-b-modal.modal-1 class="btn-sm">Cancel booking</b-button>
                            </div>
                        </div>
                        <ul class="no-bullets">
                            <li>Destination: Gardens By The Bay</li>
                            <li>Date: 31st October 2022</li>
                            <li>Number of tickets: 2</li>
                        </ul>
                    </div>

                </li>
            </ul>
        </div>


        <b-modal id="modal-1" title="Booking Cancellation" alignment="center">
            <p class="my-4">Are you sure you want to cancel your booking?</p>
            <template #modal-footer>
                <b-button variant="danger">No</b-button>
                <b-button variant="success" @click="cancelBooking()">Yes</b-button>
            </template>
        </b-modal>


        <!-- <p class="bg-light text-gray font-weight-bold text-uppercase px-3 py-3 mt-2 mb-0">Note</p>

        <ul class="nav flex-column bg-white mb-0">
            <li class="nav-item">
                <div class="ms-2 me-auto">
                    <div class="fw-bold">Information</div>
                    <ul>
                        <li>Passes can be collected 1 day before the trip from the General Office</li>
                        <li>Passes must be returned to the General Office the day after the trip</li>
                    </ul>
                </div>
            </li>
            <li class="nav-item">
                <div class="mt-3 ms-2 me-auto">
                    <div class="fw-bold">Contact</div>
                    <ul>
                        <li>General Office Phone: 12345678</li>
                    </ul>
                    <ul>
                        <li> Report loss and found <a href="#"> here </a></li>
                    </ul>
                    <ul>
                        <li>For futher queries, please contact
                            <p class="text-primary text-decoration-underline">ineedhelp@sss.edu.sg</p>
                        </li>
                    </ul>
                </div>
            </li>
        </ul> -->

    </div>
    <!-- End vertical navbar -->
</template>
  
<script>
import axios from "axios";



export default {
    name: 'SideBar',
    props: {
        msg: String
    },
    data() {
        return {
            bookingDetails: [],
            api: {
                numberBooking: "http://localhost:8080/getbooking",
                cancelBooking: "http://localhost:8080/loan/cancel"
            }
        }
    },
    methods: {
        numberBookings() {
            return axios
            .get(this.api.numberBooking + "/" + this.userEmail)
            .then((response) => {
                console.log(response.data)
                this.number = response.data;
            })
        },

        cancelBooking() {
            axios
            .get(this.api.cancelBooking, {
                "loanId": this.loanId
            })
            .then((response) => {
                console.log(response.data);
            })
        }
    }
}


</script>
  
  <!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.vertical-nav {
    min-width: 10rem;
    width: 20rem;
    height: 100%;
    position: fixed;
    right: 0;
    box-shadow: 3px 3px 10px rgba(0, 0, 0, 0.1);
    transition: all 0.4s;
    overflow: auto;
}

/* for toggle behavior */
#sidebar.active {
    margin-left: -17rem;
}

#content.active {
    width: 100%;
    margin: 0;
}

@media (max-width: 768px) {
    #sidebar {
        margin-left: -17rem;
    }

    #sidebar.active {
        margin-left: 0;
    }

    #content {
        width: 100%;
        margin: 0;
    }

    #content.active {
        margin-left: 17rem;
        width: calc(100% - 17rem);
    }
}

.personal {
    transform: translate(10px, 10px);
}

.centered {
    transform: translateY(-50%, 50%);
}

.modal-dialog {
    position: fixed;
    top: 50% !important;
    left: 50%;
    transform: translate(-50%, -50%);
}
.no-bullets {
    list-style-type: none;
}

.scrollspy {
    position: relative;
}

/* body {
    background: #599fd9;
    background: -webkit-linear-gradient(to right, #599fd9, #c2e59c);
    background: linear-gradient(to right, #599fd9, #c2e59c);
    min-height: 100vh;
    overflow-x: hidden;
} */

.separator {
    margin: 3rem 0;
    border-bottom: 1px dashed #fff;
}

.text-uppercase {
    letter-spacing: 0.1em;
}

.text-gray {
    color: #aaa;
}
</style>