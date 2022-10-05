<template>
    <div>
        <strong>Filter Booking Calendar</strong>
        <div class="row mb-3">
            <b-form-select class="col-md-6 mx-3" v-model="selected" :options="options" size="sm" ></b-form-select>
            <b-form-checkbox-group
            class="col-md-5 mx-1"
            id="availability"
            v-model="selected_avail"
            :options="options_avail"
            :aria-describedby="ariaDescribedby"
            ></b-form-checkbox-group>  
        </div>
    </div>
        
</template>


<script>
    export default {
        name: 'calendar-filter',
        data() {
            return {
            selected: null,
            selected_avail: [],
            //TODO: Get data from backend
            options: [
                { value: null, text: 'Please select a pass type' },
                { value: 'Mandai Wildlife Reserve', text: 'Mandai Wildlife Reserve' },
                { value: 'Duck Tours', text: 'Duck Tours' },
                { value: 'Singapore Flyer', text: 'Singapore Flyer' },
                { value: 'Art Science Museum', text: 'Art Science Museum', disabled: true }
            ],
            options_avail: [
            { text: 'Available', value: 'available' },
            { text: 'Waiting List', value: 'unavailable' }
            ]
            }
        },
        watch: {
        // whenever select changes, this function will run
        selected() {
            this.updateBooking(this.selected)
            }
        },
        methods: {
            updateBooking(pass_type) {
                this.$root.$refs.BookingPopUp.updatePassType(pass_type);
            }
        }
    }
  </script>