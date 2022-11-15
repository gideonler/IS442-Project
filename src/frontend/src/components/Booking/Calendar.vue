<script>
    import '@fullcalendar/core/vdom' // solves problem with Vite
    import FullCalendar from '@fullcalendar/vue'
    import dayGridPlugin from '@fullcalendar/daygrid'
    import interactionPlugin from '@fullcalendar/interaction'
    import bootstrap5Plugin from '@fullcalendar/bootstrap5';
    import BookingPopUp from './BookingPopUp.vue';
    import WaitingListPopUp from './WaitingListPopUp.vue';
    import BookingConfirmation from './BookingConfirmation.vue';
    import WaitingListConfirmation from './WaitingListConfirmation.vue';
    import axios from 'axios';

    export default {
        name:'calendar-component',
        components: {
    FullCalendar,
    BookingPopUp,
    WaitingListPopUp,
    BookingConfirmation,
    WaitingListConfirmation
},
      data() {
        return {
          api: {
            get_avail_passes: "http://localhost:8080/loan/availpasses" ,
          },
          avail_passes: [],
          calendarOptions: {
            headerToolbar: {
              left: 'prev,next today',
              center: 'title',
              right: 'dayGridMonth'
            },
            plugins: [ dayGridPlugin, interactionPlugin, bootstrap5Plugin],
            selectable: true,
            // themeSystem: 'bootstrap5',
            initialView: 'dayGridMonth',
            views: {
              dayGridMonth: {
                
                // options apply to dayGridMonth, dayGridWeek, and dayGridDay views
              },
              timeGrid: {
                // options apply to timeGridWeek and timeGridDay views
              },
              week: {
                // options apply to dayGridWeek and timeGridWeek views
              },
              day: {
                // options apply to dayGridDay and timeGridDay views
              }},
            dateClick: this.handleDateClick,
            
            //TODO: replace this dummy data with data from the backend: 
            // events: [
            //   { title: 'unavailable', date: '2022-09-01' ,   display: 'background',    color: '#ff7f7f'},
            //   { title: 'unavailable', date: '2022-09-06' ,   display: 'background',    color: '#ff7f7f'},
            //   { title: 'unavailable', date: '2022-09-09' ,   display: 'background',    color: '#ff7f7f'},
            //   { title: 'available', date: '2022-09-02',   display: 'background'  }
            // ],
            events: [],
            showModal: false
          }
        }
      },
      created() {
        this.loadData();
    },
    methods: {
      async loadData(){
        await axios
          .get(this.api.get_avail_passes)
          .then((response) => {
            this.total_loans = response.data


            var selected_attraction= "Mandai Zoo"
            var availabilities= response.data[selected_attraction]
            var result= []


            const today = new Date()
            var tomorrow =  new Date()
            tomorrow.setDate(today.getDate() + 1)
            var last =  new Date()
            last.setDate(today.getDate() + 56)
            
            // var dd = String(tomorrow.getDate()).padStart(2, '0');
            // var mm = String(tomorrow.getMonth() + 1).padStart(2, '0'); //January is 0!
            // var yyyy = tomorrow.getFullYear();
            var avail_dates = []
            var daysOfYear = [];
            for (var d = tomorrow; d <= last; d.setDate(d.getDate() + 1)) {
                daysOfYear.push(new Date(d));
                var date= new Date(d)
                var dd = String(date.getDate()).padStart(2, '0');
                var mm = String(date.getMonth() + 1).padStart(2, '0'); //January is 0!
                var yyyy = date.getFullYear();
                avail_dates.push(yyyy+"-" +mm+ "-"+ dd)
            }

            console.log(avail_dates)
            var temp;
            for(date of avail_dates){
              if(this.containsKey(availabilities,date)){
                //all available
                if(availabilities[date]!=0){
                  temp= { title: 'available', 'date': date,   display: 'background', passes_left: availabilities[date]  }
                }
              }
              //all still available
              else{
                temp= { title: 'available', 'date': date,   display: 'background'}
              }
              result.push(temp)
            }
            var total_availabilities= this.total_loans[selected_attraction]

            console.log(result)
            this.calendarOptions.events= result;

          })
          .catch((error) => {
              if (error) {
                  console.log(error);
              }
          });
        
          
        },
        containsKey(obj, key ) {
        return Object.keys(obj).includes(String(key));
        },
        toggleWeekends: function() {
        this.calendarOptions.weekends = !this.calendarOptions.weekends // toggle the boolean!
        },
        handleDateClick: function(info){ 
          let availability=  info.dayEl.innerText.split("\n")[1];
          if (availability== 'available'){
            this.$root.$refs.BookingPopUp.showModal(info.dateStr);
          }else if (availability== 'unavailable'){
            this.$root.$refs.WaitingListPopUp.showModal(info.dateStr);
          }
        }
    }
    }
    </script>
    <template>
      <div>
        <FullCalendar :options="calendarOptions" />
        <BookingPopUp></BookingPopUp>
        <WaitingListPopUp></WaitingListPopUp>
        <BookingConfirmation></BookingConfirmation>
        <WaitingListConfirmation></WaitingListConfirmation>
        
      </div>
    </template>