    <template>
    <div>
        
        <b-card no-body>
    <b-tabs card>
      <b-tab title="Deactivated" style="background-color: #f7f7f8;" active>
            <b-carousel
        class="mx-5"
        id="all-passes-carousel"
        controls
        no-animation
    >
    <b-carousel-slide>
        <template #img>
            <b-card-group deck >    
                <b-card :key="pass.passId" v-for="pass in pass_list" :title="pass.placeOfInterestName" img-height="200px" img-src="../../assets/images/gardens_by_the_bay.jpeg" img-alt="Image" img-top>
                <b-card-text>
                {{pass.placeOfInterestName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{pass.passStatus}}</small>
                    <br>
                    <b-button  @click="editDetails('Night Safari', 'Night Safari', 30, '','','')" variant="success" class="mx-1">Edit Details</b-button>
                    <b-button @click=viewPasses(pass.placeOfInterestName) class="mx-1">View Passes</b-button>

                </template>
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
    <b-carousel-slide>
        <template #img>
            <b-card-group deck >    
                <b-card :key="pass.passId" v-for="(pass) in pass_list" :title="pass.placeOfInterestName" img-height="200px" img-src="../../assets/images/duck_tour.jpeg" img-alt="Image" img-top>
                <b-card-text >
                {{pass.placeOfInterestName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{pass.passStatus}}</small>
                    <br>
                    <b-button @click="editDetails('Night Safari', 'Night Safari', 30, '','','')" variant="success" class="mx-1">Edit Details</b-button>
                    <b-button @click="viewPasses('hello')" class="mx-1">View Passes</b-button>

                </template>
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
    </b-carousel>
      </b-tab>
      <b-tab title="In Office">
        <b-card-text>Tab contents 2</b-card-text>
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
            pass_list: [],
    
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
    mounted() {
        this.pass_list= [
{
"passId": "Night Safari3",
"passNo": "3",
"placeOfInterestName": "Night Safari",
"passStatus": "INOFFICE"
},
{
"passId": "Night Safari2",
"passNo": "2",
"placeOfInterestName": "Night Safari",
"passStatus": "DEACTIVATED"
},
{
"passId": "Mandai Zoo4",
"passNo": "4",
"placeOfInterestName": "Mandai Zoo",
"passStatus": "INOFFICE"
}
]
        // axios
        //     .get("http://localhost:8080/pass/passes")
        //     .then((response) => {
        //         var pass_list = response.data;
        //         this.pass_list= pass_list
        //     })
        //     .catch((error) => {
        //         console.log(error);
        //     })
    },
    
    }
  </script>

