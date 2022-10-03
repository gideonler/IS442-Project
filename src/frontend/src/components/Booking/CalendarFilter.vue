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
                { value: 'a', text: 'Mandai Wildlife Reserve' },
                { value: 'b', text: 'Duck Tours' },
                { value: 'c', text: 'Singapore Flyer' },
                { value: 'd', text: 'Art Science Museum', disabled: true }
            ],
            options_avail: [
            { text: 'Available', value: 'available' },
            { text: 'Waiting List', value: 'unavailable' }
            ]
            }
        }
        }
  </script>