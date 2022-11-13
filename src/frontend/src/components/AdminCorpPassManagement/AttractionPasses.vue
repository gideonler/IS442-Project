    <template >
    <div >
        
        <b-card no-body>
    <b-tabs card>
      <b-tab title="Active" style="background-color: #f7f7f8;" active>
            <b-carousel
        class="mx-5"
        id="all-passes-carousel"
        controls
        no-animation
    >
    <b-carousel-slide  :key="index" v-for="index in no_active_card_groups">
        <template #img>
            <b-card-group deck >    
                <b-card style="max-width:33.33%;" :key="attraction.attractionName"  v-for="attraction in active_attractions.slice((index-1)*3,((index-1)*3)+3)" 
                :title="attraction.attractionName" img-height="200px" 
                :img-src="getImgUrl(attraction.imageFilename,attraction.attractionName)"  img-alt="Image" img-top>
                    <b-card-text>
                {{attraction.attractionName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{attraction.passType}}</small>
                    <br>
                    <b-button  @click="editDetails(attraction.attractionName, attraction.replacementFee, attraction.templateFilename,attraction.attachmentPDFFilename, attraction.imageFilename, attraction.passType)" variant="success" class="mx-1">Edit Details</b-button>
                    <b-button @click=viewPasses(attraction.attractionName) class="mx-1">View Passes</b-button>
                </template>
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
    </b-carousel>
      </b-tab>

      <b-tab title="Inactive" style="background-color: #f7f7f8;" >
            <b-carousel
        class="mx-5"
        id="all-passes-carousel"
        controls
        no-animation
    >
    <b-carousel-slide  :key="index" v-for="index in no_inactive_card_groups">
        <template #img>
            <b-card-group deck >    
                <b-card style="max-width:33.33%;" :key="attraction.attractionName"  v-for="attraction in inactive_attractions.slice((index-1)*3,((index-1)*3)+3)" 
                :title="attraction.attractionName" img-height="200px" 
                :img-src="getImgUrl(attraction.imageFilename,attraction.attractionName)" :img-alt="attraction.imageFilename" img-top>
                    <b-card-text>
                {{attraction.attractionName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{attraction.passType}}</small>
                    <br>
                    <b-button  @click="editDetails(attraction.attractionName, attraction.replacementFee, attraction.templateFilename,attraction.attachmentPDFFilename, attraction.imageFilename, attraction.passType)" variant="success" class="mx-1">Edit Details</b-button>
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
        <EditAttractionModal v-on:update-attraction="forceRerender"></EditAttractionModal>
    </div>
    </div>
  </template>

  <script>
    import axios from 'axios'
    import PassDataTable from './PassDataTable.vue'
    import EditAttractionModal from './EditAttractionModal.vue'
    
    export default {
      data() {
        return {
            attraction_list: [],
            componentKey: 0,
    
      }
    },
    methods: {
        forceRerender() {
            console.log('hellooo')
            
            this.$emit("update-attraction")
            
      },
        forceUpdate() {
            console.log('helo')
            this.$forceUpdate();  
        },
        viewPasses(placeofinterest){
            this.$router.push({ path: `/corporate-pass-management/passes/${placeofinterest}` })
        },
        editDetails(interest, replacementfee, email_file, pdf_file, img, selected_passtype){
            this.$root.$refs.EditAttractionModal.populateForm(interest, replacementfee, email_file, pdf_file, img, selected_passtype)
            this.$root.$refs.EditAttractionModal.showModal();
        },
        getImgUrl(name, attractionname){
            try{
                return require(`../../../../main/resources/attractionicons/${attractionname}/${name}`)
            }catch{
                return require('../../../../main/resources/attractionicons/null/notfound.jpeg')
            }
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
    created() {
        this.$root.$refs.AttractionPasses= this;
        },
    mounted() {
       axios
            .get("http://localhost:8080/attraction/attractions")
            .then((response) => {
                var attraction_list = response.data;
                this.attraction_list= attraction_list
            })
            .catch((error) => {
                console.log(error);
            })
    },
    
    }
  </script>

