<template>
    <div style="padding-bottom: 30px">
        <div class="row">
            <div class="card col-md-12">
                <div class="card-header">
                    <h3 class="card-title">Pass Status</h3>
                </div>
                <div class="card-body">
                    <ul>
                        <li> Passes can be collected 1 day before the intended visit date</li>
                        <li> Passes must be returned 1 day after the intended visit date, with the exception of Friday
                            and Saturday</li>
                        <li> Cancellation of loan requests are allowed at least 1 day before the intended visit date
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="row d-flex">
            <div class="card col-md-5 mr-5" v-if="bookingList[0].attractionName.length > 0" v-for="(booking, b) in bookingList" :key="b">
                <h5 class="card-header">
                    {{ booking.attractionName }}
                </h5>
                <div class="card-body">
                    <p class="card-text">Loan Date: {{ booking.loanDate.split("T")[0] }}</p>
                    <p class="card-text">Pass ID: {{ booking.passId }}</p>
                    <p class="card-text">Booking Status: {{ booking.status }}</p>
                    <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="info"
                        @click="getPassStatus(booking.passId); previousBorrower(booking.passId, booking.loanDate)">
                        Check Pass Status
                    </b-button>
                    <b-button v-b-modal.modal-2 class="btn-sm ml-1 mt-1" variant="danger"
                        @click="sendInfo(booking.attractionName, booking.passNo)">
                        Report Pass Loss
                    </b-button>
                </div>
            </div>
        </div>
        <b-modal id="modal-1" title="Pass Status" alignment="center">
            <p>Pass Status: {{ passStatus }}</p>
            <p>Previous Day Borrower:</p>
            <ul>
                <li> {{ borrower.email }} </li>
                <li> {{ borrower.contactNo }} </li>
                <li> {{ borrower.name }} </li>
            </ul>
            <template #modal-footer>
                <b-button variant="success" @click="$bvModal.hide('modal-1')">Ok</b-button>
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
</template>


<script>
import DashboardLayout from '../layouts/DashboardLayout';
import axios from "axios";


export default {
    name: "Pass-Details",
    created() {
        this.$emit("update:layout", DashboardLayout);
    },

    data() {
        return {
            userEmail: this.user.username,
            bookingList: [{ attractionName: "", loanDate: "", passId: "", status: "", loanID: "" }],
            bookingId: "",
            passStatus: "",
            previousDate: "",
            // passDetails: [{ passId: "", passNo: "", attractionName: "", passStatus: "" }],
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

    created() {
        this.user = JSON.parse(localStorage.getItem('user'));
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

        sendInfo(attractionName, passNo) {
            this.attractionName = attractionName;
            this.getReplacementFee(this.attractionName);
        },

        reportLoss(bookingId) {
            return axios
                .post(this.api.reportLoss, {
                    "loanId": bookingId,
                },
                    {
                        headers: {
                            'Access-Control-Allow-Origin': '*',
                            'Access-Control-Allow-Headers': "Origin, X-Requested-With, Content-Type, Accept",
                            'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE, OPTIONS',
                        },
                    })
                .then((response) => {
                    console.log(response.data);
                    this.$alert("Pass reported lost successfully");
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
                    // this.passDetails = response.data;
                    this.replacementFee = response.data.replacementFee;
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },

        async getPassStatus(passId) {
            console.log(passId)
            if (passId != null) {
                await axios
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
                    console.log(response.data)
                    this.borrower = response.data;
                    if (response.data == "No borrowers the day before you. Please check back the Friday before your loan to see if there is a borrower. Otherwise, collect your pass(es) from the General Office.") {
                        this.$alert("No borrowers the day before you. Please check back the Friday before your loan to see if there is a borrower. Otherwise, collect your pass(es) from the General Office.");
                        this.borrower = { email: "None", contactNo: "None", name: "None" };
                    }
                })
                .catch((error) => {
                    console.log(error.response);
                });
        }
    }
};

</script>


