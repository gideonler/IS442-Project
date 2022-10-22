<template>
    <card>
      <h4 slot="header" class="card-title"><strong>Create New Attraction</strong></h4>
      <form>  
        <div class="row">
          <div class="col-md-12">
            <base-input type="text"
                      label="Corporate Pass Name"
                      placeholder="eg. Singapore Gardens"
                      v-model="passname">
            </base-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-12">
            <base-input type="text"
                      label="Place of Interest"
                      placeholder="eg. Gardens by the Bay"
                      v-model="interest">
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
                :aria-describedby="ariaDescribedby"
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
                placeholder="Upload Email Template Here..."
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <b-form-file
                v-model="pdf_file"
                :state="Boolean(pdf_file)"
                placeholder="Upload PDF Attachement Template Here..."
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>

        <div class="row">
            <div class="col-md-12">
                <label>Pass Image</label>
                <b-form-file
                v-model="email_file"
                :state="Boolean(email_file)"
                placeholder="Upload Photo Here..."
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>

        <div class="text-center">
          <b-button type="submit" variant="primary" class="float-right" @click.prevent="createAttraction">
            Create Attraction
          </b-button>
        </div>
        <div class="clearfix"></div>
        <div>
        <AttractionCreationConfirmation></AttractionCreationConfirmation>
      </div>
      </form>
    </card>
  </template>
  <script>
    import Card from '../Cards/Card.vue'
    import AttractionCreationConfirmation from './AttractionCreationConfirmation.vue'
  
    export default {
      components: {
        Card,
        AttractionCreationConfirmation
      },
      data () {
        return {
          //TODO: Replace with data
          passname: '',
          interest: '',
          replacementfee: '',
          email_file: '',
          pdf_file:'',
          selected_passtype: '',
            options: [
            { text: 'Physical Card', value: 'physical' },
            { text: 'E-card', value: 'digital' }
            ],

        }
      },
      methods: {
        isFormValid(){
        return this.passname!= '' &&
          this.interest!= '' &&
          this.replacementfee!= '' &&
          this.email_file!= ''&&
          this.pdf_file!='' &&
          this.selected_passtype!= ''
        },
        formReset(){
          this.passname= ''
          this.interest= ''
          this.replacementfee= ''
          this.email_file= ''
          this.pdf_file=''
          this.selected_passtype= ''
        },
        createAttraction () {
          //TODO: API Call to update database
          if(this.isFormValid()){
            this.$root.$refs.AttractionCreationConfirmation.showModal(this.passname);
          }
          this.formReset()
        },

      }
    }
  
  </script>
  <style>
  
  </style>
  