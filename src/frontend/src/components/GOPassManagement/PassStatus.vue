<template>
    <div>
        <b-form @submit="onSubmit">
            <b-form-group id="input-group-1" label="Email address:" label-for="input-1">
                <b-form-input id="input-1" v-model="form.email" type="email" placeholder="Enter email" required>
                </b-form-input>
            </b-form-group>

            <b-button type="submit" variant="primary" class="mb-4">Submit</b-button>
        </b-form>

        <div class="card" v-if="bookingList.length > 1" v-for="(booking, b) in bookingList" :key="b">
            <h5 class="card-header">
                {{ booking.attractionName }}
            </h5>
            <div class="card-body">
                <p class="card-text">Loan Date: {{ booking.loanDate.split("T")[0] }}</p>
                <p class="card-text">Pass Number: {{ booking.passNo }}</p>
                <p class="card-text">Booking Status: {{ booking.status }}</p>
                <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="info" @click="sendInfo(booking.loanID)">
                    Edit Pass Status
                </b-button>
            </div>
        </div>

        <b-modal id="modal-1" title="Update Pass Status" alignment="center">
            <!-- <p class="my-4">Change pass status to:</p> -->
            <template #modal-footer>
                <b-button variant="success" @click="passStatus(bookingId, 'INOFFICE'); $bvModal.hide('modal-1')">
                    Returned</b-button>
                <b-button variant="primary" @click="passStatus(bookingId, 'ONLOAN'); $bvModal.hide('modal-1')">Collected
                </b-button>
            </template>
        </b-modal>
    </div>
</template>
  
<script>

import axios from "axios";

export default {
    data() {
        return {
            form: {
                email: '',
            },
            bookingList: [{ attractionName: "", loanDate: "", passNo: "", status: "", loanID: "" }],
            bookingId: "",
            api: {
                bookingList: "http://localhost:8080/loan/",
                passStatus: "http://localhost:8080/passstatus/change",
                emailCollected: "http://localhost:8080/email/collected",
            }
        }
    },
    methods: {
        onSubmit(event) {
            event.preventDefault()
            return axios
                .get(this.api.bookingList + this.form.email)
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

        passStatus(bookingId, status) {
            console.log(bookingId);
            console.log(status);
            return axios
                .put(this.api.passStatus, {
                    "loanId": bookingId,
                    "passStatus": status,
                })
                .then((response) => {
                    console.log(response.data)
                    this.$alert(response.data);
                    this.emailCollected(this.form.email);
                })
                .catch((error) => {
                    console.log(error.response);
                });
        },

        emailCollected(email) {
            console.log(email);
            return axios
                .post(this.api.emailCollected, {
                    "email": email,
                }, {
                    // headers: {
                    //     "Access-Control-Allow-Origin": true
                    // }
                })
                .then((response) => {
                    console.log(response.data)
                    this.$alert(response.data);
                })
                .catch((error) => {
                    console.log(error.response);
                });
        }
    },
}
</script>