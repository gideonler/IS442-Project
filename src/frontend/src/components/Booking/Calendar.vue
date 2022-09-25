<script>
    import '@fullcalendar/core/vdom' // solves problem with Vite
    import FullCalendar from '@fullcalendar/vue'
    import dayGridPlugin from '@fullcalendar/daygrid'
    import interactionPlugin from '@fullcalendar/interaction'
    
    export default {
        name:'calendar-component',
      components: {
        FullCalendar // make the <FullCalendar> tag available
      },
      data() {
        return {
          calendarOptions: {
            headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth'
    },
            plugins: [ dayGridPlugin, interactionPlugin ],
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
        events: [
          { title: 'unavailable', date: '2022-09-01' ,   display: 'background',    color: '#ff7f7f'},
          { title: 'unavailable', date: '2022-09-06' ,   display: 'background',    color: '#ff7f7f'},
          { title: 'unavailable', date: '2022-09-09' ,   display: 'background',    color: '#ff7f7f'},

          { title: 'available', date: '2022-09-02',   display: 'background'  }
        ]
          }
        }
      },
    methods: {
        toggleWeekends: function() {
        this.calendarOptions.weekends = !this.calendarOptions.weekends // toggle the boolean!
        }
    }
    }
    </script>
    <template>
      <FullCalendar :options="calendarOptions" />
      
    </template>