<template>
    <div style="padding-bottom: 10%; margin-bottom: 10%;">
        <div class="row">
            <div class="card col-md-12" style="background-color: #808588">
                <div class="card-header" style="background-color: #808588">
                    <h3 class="card-title font-weight-bold">Pass Status</h3>
                </div>
                <div class="card-body">
                    <ul>
                        <li> Passes can be <u>collected 1 day before</u> the intended visit date</li>
                        <li> Passes must be <u>returned 1 day after</u> the intended visit date, with the exception of
                            Friday
                            and Saturday</li>
                        <li> Cancellation of loan requests are allowed <u>at least 1 day before</u> the intended visit
                            date
                        </li>
                    </ul>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col">
                <h2>All Bookings</h2>
            </div>
        </div>

        <div>
            <table class="table text-nowrap">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" id="Name">Attraction Name</th>
                        <th scope="col" id="Email">Loan Date</th>
                        <th scope="col" id="UserType">Pass ID</th>
                        <th scope="col" id="OutstandingFees">Booking Status</th>
                        <th scope="col" id="UserStatus">Pass Status</th>
                        <th scope="col" id="ChangeStatus">Report Loss</th>
                    </tr>
                </thead>
                <tbody v-if="bookingList[0].attractionName.length > 0">
                    <tr v-for="(booking, i) in bookingList" :key="i">
                        <td scope="row">{{ booking.attractionName }}</td>
                        <td>{{ booking.loanDate.split("T")[0] }}</td>
                        <td>{{ booking.passId }}</td>
                        <td>{{ booking.status }}</td>
                        <td class="text-nowrap">
                            <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="secondary"
                                @click="getPassStatus(booking.passId); previousBorrower(booking.passId, booking.loanDate)">
                                Check Pass Status
                            </b-button>
                        </td>
                        <td class="text-nowrap">
                            <b-button v-b-modal.modal-2 class="btn-sm mt-1 float-right" variant="danger"
                                @click="sendInfo(booking.loanID); getReplacementFee(booking.attractionName)">
                                Report Pass Loss
                            </b-button>
                        </td>
                    </tr>
                </tbody>
            </table>

            <b-modal id="modal-1" title="Pass Status" alignment="center">
                <p>Pass Status: {{ passStatus }}</p>
                <p v-if="borrower.email.length > 0">Previous Day Borrower:</p>
                <ul v-if="borrower.email.length > 0">
                    <li> Email: {{ borrower.email }} </li>
                    <li> Contact No: {{ borrower.contactNo }} </li>
                    <li> Name: {{ borrower.name }} </li>
                </ul>
                <template #modal-footer>
                    <b-button variant="primary" @click="$bvModal.hide('modal-1')">Ok</b-button>
                </template>
            </b-modal>

            <b-modal id="modal-2" title="Report Pass Loss" alignment="center">
                <p class="my-4">Replacement fee for this pass is ${{ replacementFee }}</p>
                <template #modal-footer>
                    <b-button variant="secondary" @click="$bvModal.hide('modal-2')">Cancel</b-button>
                    <b-button variant="danger" @click="reportLoss(bookingId); $bvModal.hide('modal-2')">Report Loss
                    </b-button>
                </template>
            </b-modal>
        </div>

        <!-- <div class="row d-flex justify-content-around" v-if="bookingList[0].attractionName.length > 0">
            <div class="card bg-light col-md-5" v-for="(booking, b) in bookingList" :key="b">
                <h4 class="card-header font-weight-bold">
                    {{ booking.attractionName }}
                </h4>
                <div class="card-body">
                    <p class="card-text">Loan Date: {{ booking.loanDate.split("T")[0] }}</p>
                    <p class="card-text">Pass ID: {{ booking.passId }}</p>
                    <p class="card-text">Booking Status: {{ booking.status }}</p>
                    <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="secondary"
                        @click="getPassStatus(booking.passId); previousBorrower(booking.passId, booking.loanDate)">
                        Check Pass Status
                    </b-button>
                    <b-button v-b-modal.modal-2 class="btn-sm mt-1 float-right" variant="danger"
                        @click="sendInfo(booking.loanID); getReplacementFee(booking.attractionName)">
                        Report Pass Loss
                    </b-button>
                </div>
            </div>
        </div>
        <b-modal id="modal-1" title="Pass Status" alignment="center">
            <p>Pass Status: {{ passStatus }}</p>
            <p v-if="borrower.email.length > 0">Previous Day Borrower:</p>
            <ul v-if="borrower.email.length > 0">
                <li> Email: {{ borrower.email }} </li>
                <li> Contact No: {{ borrower.contactNo }} </li>
                <li> Name: {{ borrower.name }} </li>
            </ul>
            <template #modal-footer>
                <b-button variant="primary" @click="$bvModal.hide('modal-1')">Ok</b-button>
            </template>
        </b-modal>

        <b-modal id="modal-2" title="Report Pass Loss" alignment="center">
            <p class="my-4">Replacement fee for this pass is ${{ replacementFee }}</p>
            <template #modal-footer>
                <b-button variant="secondary" @click="$bvModal.hide('modal-2')">Cancel</b-button>
                <b-button variant="danger" @click="reportLoss(bookingId); $bvModal.hide('modal-2')">Report Loss
                </b-button>
            </template>
        </b-modal> -->
    </div>
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout';
import axios from "axios";

export default {
    name: "Pass-Details",
    created() {
        this.$emit("update:layout", DashboardLayout);
        this.user = JSON.parse(localStorage.getItem('user'));
        this.userEmail = this.user.username;
    },

    data() {
        return {
            user: "",
            userEmail: "",
            bookingList: [{ attractionName: "", loanDate: "", passId: "", status: "", loanID: "" }],
            bookingId: "",
            passStatus: "",
            previousDate: "",
            replacementFee: "",
            borrower: { email: "", contactNo: "", name: "" },
            api: {
                bookingList: "http://localhost:8080/loan/",
                reportLoss: "http://localhost:8080/loan/lost",
                passDetails: "http://localhost:8080/pass/",
                attractionDetails: "http://localhost:8080/attraction/",
                previousBorrower: "http://localhost:8080/loan/previousborrower"
            }
        };
    },

    mounted() {
        this.getBookingList(this.userEmail);
        this.getPassStatus(this.passId);
        this.previousBorrower(this.passId, this.loanDate);
    },

    methods: {
        getBookingList() {
            return axios
                .get(this.api.bookingList + this.userEmail)
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
            console.log(this.bookingId)
        },

        reportLoss(bookingId) {
            console.log(bookingId)
            return axios
                .get(this.api.reportLoss, {
                    params: {
                        loanId: bookingId
                    }
                })
                .then((response) => {
                    console.log(response.data);
                    this.$alert("Pass reported lost successfully");
                    window.location.reload();
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },

        getReplacementFee(attractionName) {
            console.log(attractionName);
            return axios
                .get(this.api.attractionDetails + attractionName + "/details")
                .then((response) => {
                    console.log(response.data);
                    this.replacementFee = response.data.replacementFee;
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },

        getPassStatus(passId) {
            console.log(passId)
            if (passId != null) {
                axios
                    .get(this.api.passDetails + passId,
                        {
                            headers: {
                                'Access-Control-Allow-Origin': '*',
                                'Access-Control-Allow-Headers': "Origin, X-Requested-With, Content-Type, Accept",
                                'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                            },
                        }
                    )
                    .then((response) => {
                        console.log(response.data.passStatus)
                        this.passStatus = response.data.passStatus;
                    })
                    .catch((error) => {
                        console.log(error.response);
                    });
            }
        },
        previousBorrower(passId, date) {
            var yesterday = new Date(date);
            yesterday.setDate(yesterday.getDate() - 1);
            var yesterdaySplit = yesterday.toISOString().split(' ');
            var yesterdayDate = yesterdaySplit[0].split('T');
            yesterdayDate = yesterdayDate[0];

            console.log(passId)
            console.log(yesterdayDate)

            return axios
                .get(this.api.previousBorrower,
                    {
                        params: {
                            passId: passId,
                            date: yesterdayDate
                        }
                    },
                )
                .then((response) => {
                    this.borrower = response.data.body;
                    console.log(response.data)
                    if (response.data.body.contactNo == null) {
                        this.borrower.contactNo = "N/A";
                    }
                    if (response.data == "No borrowers the day before you. Please check back the Friday before your loan to see if there is a borrower. Otherwise, collect your pass(es) from the General Office.") {
                        console.log(response.data)
                        this.$alert("No borrowers the day before you. Please check back the Friday before your loan to see if there is a borrower. Otherwise, collect your pass(es) from the General Office.");
                        this.borrower.email = "",
                            this.borrower.contactNo = "",
                            this.borrower.name = "";
                    }
                })
                .catch((error) => {
                    console.log(error.response);
                });
        }
    }
};

</script>

<style>
td {
    text-align: left;
}
</style>
