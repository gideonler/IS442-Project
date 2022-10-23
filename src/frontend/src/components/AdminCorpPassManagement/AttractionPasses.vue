    <template>
    <div>
        
        <b-card no-body>
    <b-tabs card>
      <b-tab title="In Office" style="background-color: #f7f7f8;" active>
            <b-carousel
        class="mx-5"
        id="all-passes-carousel"
        controls
        no-animation
    >
    <b-carousel-slide  :key="index" v-for="index in no_active_card_groups">
        <template #img>
            <b-card-group deck >    
                <b-card style="max-width:33.33%;" :key="attraction.attractionName"  v-for="attraction in active_attractions.slice((index-1)*3,((index-1)*3)+3)" :title="attraction.attractionName" img-height="200px" img-src="../../assets/images/gardens_by_the_bay.jpeg" img-alt="Image" img-top>
                    <b-card-text>
                {{attraction.attractionName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{attraction.passType}}</small>
                    <br>
                    <b-button  @click="editDetails('Night Safari', 'Night Safari', 30, '','','')" variant="success" class="mx-1">Edit Details</b-button>
                    <b-button @click=viewPasses(attraction.attractionName) class="mx-1">View Passes</b-button>
                </template>
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
    </b-carousel>
      </b-tab>

      <b-tab title="Deactivated" style="background-color: #f7f7f8;" active>
            <b-carousel
        class="mx-5"
        id="all-passes-carousel"
        controls
        no-animation
    >
    <b-carousel-slide  :key="index" v-for="index in no_inactive_card_groups">
        <template #img>
            <b-card-group deck >    
                <b-card style="max-width:33.33%;" :key="attraction.attractionName"  v-for="attraction in inactive_attractions.slice((index-1)*3,((index-1)*3)+3)" :title="attraction.attractionName" img-height="200px" img-src="../../assets/images/gardens_by_the_bay.jpeg" img-alt="Image" img-top>
                    <b-card-text>
                {{attraction.attractionName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{attraction.passType}}</small>
                    <br>
                    <b-button  @click="editDetails('Night Safari', 'Night Safari', 30, '','','')" variant="success" class="mx-1">Edit Details</b-button>
                    <b-button @click=viewPasses(attraction.attractionName) class="mx-1">View Passes</b-button>
                </template>
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
    </b-carousel>
      </b-tab>

    </b-tabs>
  </b-card>


   
  <div hidden>
        <PassDataTable></PassDataTable>
        <EditAttractionModal></EditAttractionModal>
    </div>
    </div>
  </template>

  <script>
    // import axios from 'axios'
    import PassDataTable from './PassDataTable.vue'
    import EditAttractionModal from './EditAttractionModal.vue'
    
    export default {
      data() {
        return {
            attraction_list: [],
    
      }
    },
    methods: {
        viewPasses(placeofinterest){
            this.$root.$refs.PassDataTable.viewPass(placeofinterest)
            this.$router.push({ path: '/corporate-pass-management/passes' })
        },
        editDetails(passname, interest, replacementfee, email_file, pdf_file, selected_passtype){
            this.$root.$refs.EditAttractionModal.populateForm(passname, interest, replacementfee, email_file, pdf_file, selected_passtype)
            this.$root.$refs.EditAttractionModal.showModal();
        }
    },
    components: {
        PassDataTable,
        EditAttractionModal
    },
    computed: {
        active_attractions: function() {
            return this.attraction_list.filter(attraction => attraction.active== true)
        },
        inactive_attractions: function() {
            return this.attraction_list.filter(attraction => attraction.active== false)
        },
        no_active_card_groups: function(){
            return Math.ceil(this.active_attractions.length/3)
        },
        no_inactive_card_groups: function(){
            return Math.ceil(this.inactive_attractions.length/3)
        }
    },
    mounted() {
    //    axios
    //         .get("http://localhost:8080/attraction/attractions")
    //         .then((response) => {
    //             var attraction_list = response.data;
    //             this.attraction_list= attraction_list
    //         })
    //         .catch((error) => {
    //             console.log(error);
    //         })

        this.attraction_list= [
{
"attractionName": "Night Safari",
"replacementFee": 26.0,
"passType": "ELECTRONICPASS",
"active": false
},
{
"attractionName": "Mandai Zoo",
"replacementFee": 30.0,
"passType": "ELECTRONICPASS",
"active": true
},
{
"attractionName": "Bing Bong",
"replacementFee": 50.0,
"passType": "PHYSICALPASS",
"active": true
},
{
"attractionName": "Gardens By the Bae",
"replacementFee": 26.0,
"passType": "ELECTRONICPASS",
"active": false
},
{
"attractionName": "Duck Goes Quack ",
"replacementFee": 30.0,
"passType": "ELECTRONICPASS",
"active": true
},
{
"attractionName": "Billabong",
"replacementFee": 50.0,
"passType": "PHYSICALPASS",
"active": true
}
]
    },
    
    }
  </script>

