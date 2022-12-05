<template>
    <div>
        <strong>Select an Attraction</strong>
        <div class="row mb-3">
            <b-form-select class="col-md-6 mx-3" v-model="selected" :options="options" size="sm" ></b-form-select> 
        </div>
    </div>
        
</template>


<script>
    import axios from 'axios';
    export default {
        name: 'calendar-filter',
        data() {
            return {
            selected: null,
            selected_avail: [],
            attraction_names: [],
          current_attractions: [],
            //TODO: Get data from backend
            api: {
            get_attractions: "http://localhost:8080/attraction/attractions"
          },
            options: [],
            }
        },
        watch: {
        // whenever select changes, this function will run
        selected() {
            //emit whenever selected
            this.updateBooking(this.selected)
            this.$emit("filter-attraction", this.selected)
            }
        },
        created() {
            this.loadData();
            this.selected= this.$route.params.attractionname;
            console.log(this.selected)
            this.$root.$on('select_attraction', data => {
                console.log(data)

                this.selected= data;
            });
        },
        methods: {
            updateBooking(pass_type) {
                this.$root.$refs.BookingPopUp.updatePassType(pass_type);
                this.$root.$refs.WaitingListPopUp.updatePassType(pass_type);
            },
            async loadData(){
                await axios
                .get(this.api.get_attractions)
                .then((response) => {
                    this.current_attractions = response.data
                })
                .catch((error) => {
                    if (error) {
                        console.log(error);
                    }
                });

                var options= [
                { value: null, text: 'Please select an attraction' }]

                for(var attraction of this.current_attractions){
                    var attractionName = attraction["attractionName"]
                    options.push({"value": attractionName, "text": attractionName})
                // this.attraction_names.push(attraction["attractionName"])
                }
                this.options= options;

            }
        }
    }
  </script>