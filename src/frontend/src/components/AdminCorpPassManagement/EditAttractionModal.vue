<template>
    <b-modal 
    id="edit-attraction-modal" 
    ref="edit-attraction-modal"
    body-bg-variant="light"
    hide-footer
    header-border-variant="light"
    header-bg-variant="light"
    >
        <card>
      <h4 slot="header" class="card-title"><strong>Edit Attraction</strong></h4>
      <form>  
        <div class="row">
          <div class="col-md-12">
            <base-input type="text"
                      label="Place of Interest"
                      v-model="attractionname"
                      disabled
                      >
            </base-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-6">
            <label>Pass Replacement Fee ($)</label>
            <b-input-group prepend="$">
                <b-form-input
                    type="number"
                    min="0"
                    v-model="replacementfee"
                ></b-form-input>
            </b-input-group>
          </div>
          <div class="col-md-6">
            <label>Pass Type</label>
            <b-form-radio-group
                id="radio-group-1"
                v-model="selected_passtype"
                :options="options"
                name="radio-options"
            ></b-form-radio-group>
          </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <label>Templates</label>
                <b-form-file
                v-model="email_file"
                :state="Boolean(email_file)"
                :placeholder="'Current Template: ' + email_name"
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <b-form-file
                v-model="pdf_file"
                :state="Boolean(pdf_file)"
                :placeholder="'Current Template: ' + pdf_name"
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <label>Pass Image</label>
                <b-form-file
                v-model="image"
                :state="Boolean(image)"
                :placeholder="'Current Image: ' + image_name"
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>

        <div class="text-center">
          <b-button type="submit" variant="success" class="float-right" @click.prevent="editAttraction">
            Edit Attraction
          </b-button>
        </div>
        <div class="clearfix"></div>
        <div>
        <AttractionEditConfirmation v-on:update-attraction="forceRerender"></AttractionEditConfirmation>
      </div>
      </form>
    </card>
    </b-modal>
  </template>
  <script>
    import Card from '../Cards/Card.vue'
    import AttractionEditConfirmation from './AttractionEditConfirmation.vue'
    import axios from 'axios';
  
    export default {
      components: {
        Card,
        AttractionEditConfirmation
      },
      data () {
        return {
          //TODO: Replace with data
          attractionname: '',
          replacementfee: '',
          email_file: null,
          pdf_file:null,
          selected_passtype: '',
          email_name: '',
          pdf_name: '',
          image_name: '',
          image: null,
            options: [
            { text: 'Physical Card', value: 'PHYSICALPASS' },
            { text: 'E-card', value: 'ELECTRONICPASS' }
            ],
          api: {
            edit_attraction: "" ,
            upload_files:  "",
            upload_image: "",
            },
        }
      },
      watch: {
        attractionname() {
            this.api.edit_attraction =  "http://localhost:8080/attractionmanagement/" + this.attractionname + "/edit"
            this.api.upload_files =  "http://localhost:8080/attractionmanagement/" + this.attractionname + "/uploadfiles"
            this.api.upload_image =  "http://localhost:8080/attractionmanagement/" + this.attractionname + "/uploadimage"
        },
      },
      methods: {
        forceRerender() {
            this.$emit("update-attraction")
            
      },
        isFormValid(){
        return this.attractionname!= '' &&
          this.selected_passtype!= ''
        },
        formReset(){
          this.attractionname= ''
          this.replacementfee= ''
          this.email_file= ''
          this.pdf_file=''
          this.image!='' 
          this.selected_passtype= ''
        },
        async editAttraction () {
          console.log(this.api)
          //call api to update
          if(this.isFormValid()){
          //1. update attraction details

          if(this.replacementfee==''){
            this.replacementfee= 53.50
          }
          await axios 
            .put(this.api.edit_attraction, 
              {
                "attraction" : this.attractionname,
                "replacementFee" : parseFloat(this.replacementfee),
                "passType" : this.selected_passtype
              }
              )
            .then((response) => {
              console.log(response);
            }); 
           
          //2. upload templates
          if(this.email_file!=null){
            let filesData = new FormData();
          filesData.append('emailtemplate', this.email_file);
          
            await axios 
            .put(this.api.upload_files, 
            filesData,
              {
                headers: {
                'Content-Type': 'multipart/formdata'      
              }
            }
              )
            .then((response) => {
              console.log(response);
            }); 
          }
       
          if(this.pdf_file!=null){
            let filesData = new FormData();
          filesData.append('attachment', this.pdf_file);
          
            await axios 
            .put(this.api.upload_files, 
            filesData,
              {
                headers: {
                'Content-Type': 'multipart/formdata'      
              }
            }
              )
            .then((response) => {
              console.log(response);
            }); 
          }

            //3. upload image
            if(this.image!=null){

            let imageData = new FormData();
            imageData.append('image', this.image);

            await axios 
            .put(this.api.upload_image, 
               imageData,
              {
                headers: {
                'Content-Type': 'multipart/formdata'      
              }
              }
              )
            .then((response) => {
              console.log(response);
            }); 
           
          }
            this.$root.$refs.AttractionEditConfirmation.showConfirmModal(this.attractionname);
          }
          // this.formReset()
        },
        populateForm(attractionname, replacementfee, email_file, pdf_file, image, selected_passtype){
            this.attractionname= attractionname
            this.replacementfee=  replacementfee
            this.email_name= email_file
            this.pdf_name= pdf_file
            this.image_name= image
            this.selected_passtype= selected_passtype
        },
        showModal() {
          this.$refs['edit-attraction-modal'].show()
        },
        hideModal() {
          this.$refs['edit-attraction-modal'].hide()
        },

      },
      created(){
        this.$root.$refs.EditAttractionModal = this;
      }
    }
  
  </script>
  <style>
  
  </style>
  