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
            events: [
              { title: 'unavailable', date: '2022-09-01' ,   display: 'background',    color: '#ff7f7f'},
              { title: 'unavailable', date: '2022-09-06' ,   display: 'background',    color: '#ff7f7f'},
              { title: 'unavailable', date: '2022-09-09' ,   display: 'background',    color: '#ff7f7f'},
              { title: 'available', date: '2022-09-02',   display: 'background'  }
            ],
            showModal: false
          }
        }
      },
    methods: {
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