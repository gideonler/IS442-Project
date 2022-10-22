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
                <b-card :key="pass.passId" v-for="pass in pass_list" :title="pass.placeOfInterestName" img-height="200px" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
                <b-card-text>
                {{pass.placeOfInterestName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{pass.passStatus}}</small>
                    <br>
                    <b-button  variant="success" class="mx-1">Edit Details</b-button>
                    <b-button @click=viewPasses(pass.placeOfInterestName) class="mx-1">View Passes</b-button>

                </template>
            </b-card>
        </b-card-group>
        </template>
    </b-carousel-slide>
    <b-carousel-slide>
        <template #img>
            <b-card-group deck >    
                <b-card :key="pass.passId" v-for="(pass) in pass_list" :title="pass.placeOfInterestName" img-height="200px" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
                <b-card-text >
                {{pass.placeOfInterestName}}
                </b-card-text>
                <template #footer>
                    <small class="text-muted">{{pass.passStatus}}</small>
                    <br>
                    <b-button  variant="success" class="mx-1">Edit Details</b-button>
                <b-button class="mx-1">View Passes</b-button>

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
    </div>
    </div>
  </template>

  <script>
    import axios from 'axios'
    import PassDataTable from './PassDataTable.vue'
    
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
        }
    },
    components: {
        PassDataTable
    },
    mounted() {
        axios
            .get("http://localhost:8080/pass/passes")
            .then((response) => {
                var pass_list = response.data;
                this.pass_list= pass_list
            })
            .catch((error) => {
                console.log(error);
            })
    },
    
    }
  </script>

