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
            plugins: [ dayGridPlugin, interactionPlugin ],
            initialView: 'dayGridMonth',
            dateClick: this.handleDateClick,
        events: [
          { title: 'event 1', date: '2022-09-01' },
          { title: 'event 2', date: '2022-09-02' }
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