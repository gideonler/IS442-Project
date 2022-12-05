<template >
    <div >
        <b-carousel
        class="mx-5"
        id="all-passes-carousel"
        controls
        no-animation
        :interval="0"
    >
    <b-carousel-slide :key="index" v-for="index in no_active_card_groups">
        <template #img>
            <b-card-group deck >    
                <b-card style="max-width:33.33%;" :key="attraction.attractionName"  v-for="attraction in active_attractions.slice((index-1)*3,((index-1)*3)+3)" 
                :title="attraction.attractionName" img-height="200px" 
                :img-src="getImgUrl(attraction.imageFilename,attraction.attractionName)"  img-alt="Image" img-top>
                    <b-card-text>
                    Replacement Fee: ${{attraction.replacementFee}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{attraction.passType}}</small>
                    <br>
                    <b-button  variant="success" @click="book(attraction.attractionName)" class="mx-1">Book Attraction</b-button>

                </template>
                
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
     
</b-carousel>

    </div>
  </template>

  <script>
    import axios from 'axios'
   
    export default {
      data() {
        return {
            attraction_list: [],
            selected_attraction: '',
            api:{
                attractionmanagement: 'http://localhost:8080/attractionmanagement/'
            }
    
      }
    },
    methods: {
        getImgUrl(name, attractionname){
            try{
                return require(`../../../../main/resources/attractionicons/${attractionname}/${name}`)
            }catch{
                return require('../../../../main/resources/attractionicons/null/notfound.jpeg')
            }
        },
        book(attractionname){
            this.$root.$emit('select_attraction', attractionname)
            this.$router.push({name: 'Booking', params: {attractionname: attractionname}})
        },
    },
   
    computed: {
        active_attractions: function() {
            return this.attraction_list.filter(attraction => attraction.active== true)
        },
        no_active_card_groups: function(){
            return Math.ceil(this.active_attractions.length/3)
        },
      
    },
    created() {
     
        },
    async mounted() {
       await axios
            .get("http://localhost:8080/attraction/attractions")
            .then((response) => {
                var attraction_list = response.data;
                this.attraction_list= attraction_list
                console.log(this.attraction_list)
            })
            .catch((error) => {
                console.log(error);
            })
     
    },

    
    }
    
  </script>

