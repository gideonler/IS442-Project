<template>
  <div class="table-container">
    <h1>{{placeOfInterest}}</h1>
          <!-- User Interface controls -->
          <b-row>
        <b-col lg="6" class="my-1">
          <b-form-group
            label="Sort"
            label-for="sort-by-select"
            label-cols-sm="1"
            label-align-sm="left"
            label-size="sm"
            class="mb-0"
            v-slot="{ ariaDescribedby }"
          >
            <b-input-group size="sm">
              <b-form-select
                id="sort-by-select"
                v-model="sortBy"
                :options="sortOptions"
                :aria-describedby="ariaDescribedby"
                class="w-75"
              >
                <template #first>
                  <option value="">-- none --</option>
                </template>
              </b-form-select>
  
              <b-form-select
                v-model="sortDesc"
                :disabled="!sortBy"
                :aria-describedby="ariaDescribedby"
                size="sm"
                class="w-25"
              >
                <option :value="false">Asc</option>
                <option :value="true">Desc</option>
              </b-form-select>
            </b-input-group>
          </b-form-group>
        </b-col>
  
        <b-col lg="4" class="my-1 mx-5">
          <b-button variant="success" @click="handleAdd()">Create New Pass</b-button>
        </b-col>
  
        <b-col lg="6" class="my-1">
          <b-form-group
            label="Filter"
            label-for="filter-input"
            label-cols-sm="1"
            label-align-sm="left"
            label-size="sm"
            class="mb-0"
          >
            <b-input-group size="sm">
              <b-form-input
                id="filter-input"
                v-model="filter"
                type="search"
                placeholder="Type to Search"
              ></b-form-input>
  
              <b-input-group-append>
                <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
              </b-input-group-append>
            </b-input-group>
          </b-form-group>
        </b-col>
  
        <b-col lg="6" class="my-1">
          <b-form-group
            v-model="sortDirection"
            label="Filter On"
            description="Leave all unchecked to filter on all data"
            label-cols-sm="3"
            label-align-sm="right"
            label-size="sm"
            class="mb-0"
            v-slot="{ ariaDescribedby }"
          >
            <b-form-checkbox-group
              v-model="filterOn"
              :aria-describedby="ariaDescribedby"
              class="mt-1"
            >
              <b-form-checkbox value="placeOfInterestName">Name</b-form-checkbox>
              <b-form-checkbox value="passNo">Pass Number</b-form-checkbox>
              <b-form-checkbox value="passStatus">Pass Status</b-form-checkbox>
            </b-form-checkbox-group>
          </b-form-group>
        </b-col>
  
        <b-col sm="5" md="6" class="my-1">
          <b-form-group
            label="Per page"
            label-for="per-page-select"
            label-cols-sm="6"
            label-cols-md="4"
            label-cols-lg="2"
            label-align-sm="left"
            label-size="sm"
            class="mb-0"
          >
            <b-form-select
              id="per-page-select"
              v-model="perPage"
              :options="pageOptions"
              size="sm"
            ></b-form-select>
          </b-form-group>
        </b-col>
  
        <b-col sm="7" md="6" class="my-1">
          <b-pagination
            v-model="currentPage"
            :total-rows="totalRows"
            :per-page="perPage"
            align="fill"
            size="sm"
            class="my-0"
          ></b-pagination>
        </b-col>
      </b-row>
    <b-editable-table
      head-variant="dark"
      disableDefaultEdit
      :rowUpdate="rowUpdate"
      :editMode="'row'"
      bordered
      class="editable-table"
      v-model="items"
      :fields="fields"
      :current-page="currentPage"
      :per-page="perPage"
      :filter="filter"
      :filter-included-fields="filterOn"
      :sort-by.sync="sortBy"
      :sort-desc.sync="sortDesc"
      :sort-direction="sortDirection"
      stacked="md"
      show-empty
      small
      @filtered="onFiltered"
    >
      <template #cell(isActive)="data">
        <span v-if="data.value">Yes</span>
        <span v-else>No</span>
      </template>
      <template #cell(edit)="data">
        <div v-if="data.isEdit">
          <BIconX class="edit-icon" @click="handleSubmit(data, false)"></BIconX>
          <BIconCheck
            class="edit-icon"
            @click="handleSubmit(data, true)"
          ></BIconCheck>
        </div>
        <BIconPencil
          v-else
          class="edit-icon"
          @click="handleEdit(data, true)"
        ></BIconPencil>
      </template>
      <template #cell(delete)="data">
        <BIconTrash
          class="remove-icon"
          @click="handleDelete(data)"
        ></BIconTrash>
      </template>
    </b-editable-table>
  </div>
</template>

<script>
import BEditableTable from "bootstrap-vue-editable-table";
import {
  BIconTrash,
  BIconPencil,
  BIconX,
  BIconCheck,
  BButton,
} from "bootstrap-vue";
export default {
  components: {
    BEditableTable,
    BIconX,
    BIconTrash,
    BIconPencil,
    BIconCheck,
    BButton,
  },
  data() {
    return {
      placeOfInterest: '',
      fields: [
        { key: "delete", label: "" },
        {
          key: "name",
          label: "Name",
          type: "text",
          editable: true,
          placeholder: "Enter Name...",
          class: "name-col",
          validate: this.validateName
        },
        {
          key: "department",
          label: "Department",
          type: "select",
          editable: true,
          class: "department-col",
          options: [
            { value: 1, text: "HR" },
            { value: 2, text: "Engineer" },
            { value: 3, text: "VP" },
            { value: 4, text: "CEO" },
          ],
        },
        {
          key: "age",
          label: "Age",
          type: "range",
          min: "0",
          max: "100",
          editable: true,
          placeholder: "Enter Age...",
          class: "age-col",
        },
        {
          key: "dateOfBirth",
          label: "Date Of Birth",
          type: "date",
          editable: true,
          class: "date-col",
          locale: "en",
          "date-format-options": {
            year: "numeric",
            month: "numeric",
            day: "numeric",
          },
        },
        {
          key: "isActive",
          label: "Is Active",
          type: "checkbox",
          editable: true,
          class: "is-active-col",
        },
        { key: "edit", label: "" },
      ],
      items: [
        {
          id: 1,
          age: 40,
          name: "Dickerson",
          department: 1,
          dateOfBirth: "1984-05-20",
          isActive: true,
        },
        {
          id: 2,
          age: 21,
          name: "Larsen",
          department: 2,
          dateOfBirth: "1972-07-25",
          isActive: false,
        },
        {
          id: 3,
          age: 89,
          name: "Geneva",
          department: 3,
          dateOfBirth: "1981-02-02",
          isActive: false,
        },
        {
          id: 1,
          age: 40,
          name: "Dickerson",
          department: 1,
          dateOfBirth: "1984-05-20",
          isActive: true,
        },
        {
          id: 2,
          age: 21,
          name: "Larsen",
          department: 2,
          dateOfBirth: "1972-07-25",
          isActive: false,
        },
        {
          id: 3,
          age: 89,
          name: "Geneva",
          department: 3,
          dateOfBirth: "1981-02-02",
          isActive: false,
        },
        {
          id: 4,
          age: 38,
          name: "Jami",
          department: 4,
          dateOfBirth: "1964-10-19",
          isActive: true,
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
    };
  },
  methods: {
    handleAdd() {
      const newId = Date.now();
      this.rowUpdate = {
        edit: true,
        id: newId,
        action: "add",
        data: {
          id: newId,
          age: null,
          name: "",
          department: 1,
          dateOfBirth: null,
          isActive: false,
        },
      };
    },
    handleSubmit(data, update) {
      this.rowUpdate = {
        edit: false,
        id: data.id,
        action: update ? "update" : "cancel",
      };
    },
    handleEdit(data) {
      this.rowUpdate = { edit: true, id: data.id };
    },
    handleDelete(data) {
      this.rowUpdate = { id: data.id, action: "delete" };
    },
    validateName(value) {
        if (value === '') {
          return {
            valid: false,
            errorMessage: 'Please enter name'
          }
        }
        return {valid: true};
      },
      viewPass(placeofinterest){
          //TODO: replacee
          this.placeOfInterest= placeofinterest;
        },
        info(item, index, button) {
          this.infoModal.title = `Row index: ${index}`
          this.infoModal.content = JSON.stringify(item, null, 2)
          this.$root.$emit('bv::show::modal', this.infoModal.id, button)
        },
        resetInfoModal() {
          this.infoModal.title = ''
          this.infoModal.content = ''
        },
        onFiltered(filteredItems) {
          // Trigger pagination to update the number of buttons/pages due to filtering
          this.totalRows = filteredItems.length
          this.currentPage = 1
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
        }
      },
  created(){
    this.$root.$refs.PassDataTable= this;
  },
  mounted(){
    this.totalRows = this.items.length
  }
};
</script>

<style>
.table-container {
  margin: 10px;
}

table.editable-table {
  margin-top: 10px;
}

table.editable-table td {
  vertical-align: middle;
}

.editable-table .data-cell {
  padding: 8px;
  vertical-align: middle;
}

.editable-table .custom-checkbox {
  width: 50px;
}

.remove-icon {
  color: red;
  cursor: pointer;
  font-size: 20px;
}

.edit-icon {
  color: rgb(4, 83, 158);
  cursor: pointer;
  font-size: 20px;
}

.name-col {
  width:  20%;
}

.department-col {
  width: 20%;
}

.age-col {
  width:  10%;
}

.date-col {
  width:  20%;
}

.is-active-col {
  width:  10%;
}
</style>