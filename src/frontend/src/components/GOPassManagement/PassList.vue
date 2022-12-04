<template>
  <div class="users-admin">
    <div class="container">
      <div class="row">
        <div class="col">
          <b-form @submit="onSubmit">
            <b-form-group id="input-group-1" label="Enter Email address:" label-for="input-1">
              <b-form-input id="input-1" v-model="form.email" type="email" placeholder="Email Address" required>
              </b-form-input>
            </b-form-group>

            <b-button type="submit" variant="primary" class="mb-4">Submit</b-button>
          </b-form>
        </div>
      </div>

      <div class="row gx-3" v-if="bookingList[0].attractionName.length > 0">
        <!-- User Interface controls -->
        <b-row>


          <b-col lg="6" class="my-1">
            <b-form-group label="Filter" label-for="filter-input" label-cols-sm="1" label-align-sm="left"
              label-size="sm" class="mb-0">
              <b-input-group size="sm">
                <b-form-input id="filter-input" v-model="filter" type="search" placeholder="Type to Search">
                </b-form-input>

                <b-input-group-append>
                  <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                </b-input-group-append>
              </b-input-group>
            </b-form-group>
          </b-col>

          <b-col lg="6" class="my-1">
            <b-form-group v-model="sortDirection" label="Filter On"
              description="Leave all unchecked to filter on all data" label-cols-sm="3" label-align-sm="right"
              label-size="sm" class="mb-0" v-slot="{ ariaDescribedby }">
              <b-form-checkbox-group v-model="filterOn" :aria-describedby="ariaDescribedby" class="mt-1">
                <b-form-checkbox value="attractionName">Attraction</b-form-checkbox>
                <b-form-checkbox value="passStatus">Pass Status</b-form-checkbox>
                <b-form-checkbox value="bookingStatus">Booking Status</b-form-checkbox>
              </b-form-checkbox-group>
            </b-form-group>
          </b-col>

          <b-col sm="5" md="6" class="my-1">
            <b-form-group label="Per page" label-for="per-page-select" label-cols-sm="6" label-cols-md="4"
              label-cols-lg="2" label-align-sm="left" label-size="sm" class="mb-0">
              <b-form-select id="per-page-select" v-model="perPage" :options="pageOptions" size="sm"></b-form-select>
            </b-form-group>
          </b-col>

          <b-col sm="7" md="6" class="my-1">
            <b-pagination v-model="currentPage" :total-rows="totalRows" :per-page="perPage" align="fill" size="sm"
              class="my-0"></b-pagination>
          </b-col>
        </b-row>
        <b-editable-table width=100% head-variant="dark" disableDefaultEdit bordered class="editable-table"
          v-model="items" :fields="fields" :current-page="currentPage" :per-page="perPage" :filter="filter"
          :filter-included-fields="filterOn" :sort-by.sync="sortBy" :sort-desc.sync="sortDesc"
          :sort-direction="sortDirection" stacked="md" show-empty small @filtered="onFiltered">
          <template #cell(editPassStatus)="data">
            <div>
              <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="secondary"
                @click="sendInfo(data.item.loanID); getPassStatus(data.item.passId)">
                Edit Pass Status
              </b-button>
            </div>
          </template>
        </b-editable-table>


        <!-- 
                <table class="table text-nowrap">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col" id="Attraction">Attraction</th>
                            <th scope="col" id="LoanDate">Loan Date</th>
                            <th scope="col" id="PassID">Pass ID</th>
                            <th scope="col" id="PassStatus">Pass Status</th>
                            <th scope="col" id="BookingStatus">Booking Status</th>
                            <th scope="col" id="EditPassStatus">Edit Pass Status</th>
                        </tr>
                    </thead>
                    <tbody v-for="(booking, b) in bookingList" :key="b">
                        <tr>
                            <td scope="row">{{  booking.attractionName  }}</td>
                            <td>{{ booking.loanDate.split("T")[0] }}</td>
                            <td>{{ booking.passId }}</td>
                            <td>{{ booking.status }}</td>
                            <td>Present</td>
                            <td>
                                <b-button v-b-modal.modal-1 class="btn-sm mt-1" variant="secondary" @click="sendInfo(booking.loanID); getPassStatus(booking.passId)">
                                    Edit Pass Status
                                </b-button>
                            </td>
                        </tr>
                    </tbody>
                </table> -->
        <b-modal id="modal-1" title="Update Pass Status" alignment="center">
          <p class="card-text">Current Pass Status: {{ passLocation }}</p>
          <template #modal-footer>
            <b-button variant="secondary" @click="passStatus(bookingId, 'INOFFICE'); $bvModal.hide('modal-1')">
              Returned</b-button>
            <b-button variant="success" @click="passStatus(bookingId, 'ONLOAN'); $bvModal.hide('modal-1')">Collected
            </b-button>
          </template>
        </b-modal>
      </div>

      <div id="errorMsg" style="display:none;">
        <b-alert show variant="danger" dismissible>
          Email not found!
        </b-alert>
      </div>
    </div>
  </div>
</template>
  
<script>

import axios from 'axios'
import BEditableTable from "bootstrap-vue-editable-table";


export default {
  components: {
    BEditableTable,

  },
  data() {
    return {
      form: {
        email: '',
      },
      bookingList: [{ attractionName: "", loanDate: "", passId: "", status: "", loanID: "" }],
      bookingId: "",
      passLocation: "",
      api: {
        bookingList: "http://localhost:8080/loan/",
        passStatus: "http://localhost:8080/passstatus/change",
        emailCollected: "http://localhost:8080/email/collected",
        passDetails: "http://localhost:8080/pass/",
      },
      items: null,
      fields: [
        {
          key: "attractionName",
          label: "Attraction Name",
          type: "text",
          editable: false,
          class: "attraction-col",
          sortable: true,
        },
        {
          key: "loanDate",
          label: "Loan Date",
          type: "text",
          editable: false,
          class: "loan-date-col",
          sortable: true,
        },
        {
          key: "passId",
          label: "Pass ID",
          type: "text",
          editable: false,
          class: "pass-id-col",
          sortable: true,
        },
        {
          key: "passStatus",
          label: "Pass Status",
          type: "text",
          editable: false,
          class: "pass-status-col",
          sortable: true,
        },

        {
          key: "status",
          label: "Booking Status",
          type: "text",
          editable: false,
          class: "status-col",
          sortable: true,
        },
        {
          key: "editPassStatus", label: "",
          class: "edit-pass-col",
        },
      ],
      rowUpdate: {},
      totalRows: 1,
      currentPage: 1,
      perPage: 5,
      pageOptions: [5, 10, 15, { value: 100, text: "Show a lot" }],
      sortBy: '',
      sortDesc: false,
      sortDirection: 'asc',
      filter: null,
      filterOn: [],
    }
  },

  created() {
    this.passLocation = this.getPassStatus(this.passId);
    this.user = JSON.parse(localStorage.getItem('user'));
  },

  methods: {
    onSubmit(event) {
      event.preventDefault()
      return axios
        .get(this.api.bookingList + this.form.email, {
          params: {
            Authorization: "Bearer " + this.user.jwt,
          }
        })
        .then((response) => {
          console.log(response.data)
          this.bookingList = response.data;
          this.bookingList.forEach(function (value, i) {
            value["id"] = i + 1
          });
          this.items = this.bookingList
          console.log(this.items)
          this.totalRows = this.items.length
        })
        .catch((error) => {
          console.log(error.response);
          document.getElementById('errorMsg').style.display = "block";
        });
    },

    sendInfo(loanID) {
      console.log(loanID)
      this.bookingId = loanID;
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length
      this.currentPage = 1
    },

    passStatus(bookingId, status) {
      console.log(bookingId);
      console.log(status);
      return axios
        .put(this.api.passStatus, {
          "loanId": bookingId,
          "passStatus": status,
        },
          {
            params: {
              Authorization: "Bearer " + this.user.jwt,
            }
          })
        .then((response) => {
          console.log(response.data)
          this.$alert(response.data);
        })
        .catch((error) => {
          console.log(error.response);
        });
    },

    async getPassStatus(passId) {
      console.log(passId)
      await axios
        .get(this.api.passDetails + passId, {
          params: {
            Authorization: "Bearer " + this.user.jwt,
          }
        })
        .then((response) => {
          // console.log(response.data.passStatus)
          this.passLocation = response.data.passStatus;
          console.log(this.passLocation)
        })
        .catch((error) => {
          console.log(error.response);
        });
    },
  },
  computed: {
    sortOptions() {
      // Create an options list from our fields
      return this.fields
        .filter(f => f.sortable)
        .map(f => {
          return { text: f.label, value: f.key }
        })
    },
  },
}
</script>

<style>
.table {
  font-size: 13px;
}

.table-container {
  margin: 10px;
}

table.b-table.editable-table {
  width: 100%;
  margin-top: 10px;
  margin-bottom: 100px;
}

table.editable-table td {
  vertical-align: middle;
}

.editable-table .data-cell {
  padding: 8px;
  vertical-align: middle;
}


@media only screen and (min-width: 600px) {
  .attraction-col {
    width: 15%;
  }

  .loan-date-col {
    width: 15%;
  }

  .pass-id-col {
    width: 15%;
  }

  .pass-status-col {
    width: 15%;
  }

  .status-col {
    width: 20%;
  }

  .edit-pass-col {
    width: 20%;
  }
}
</style>