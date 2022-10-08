<template>
    <card>
      <h4 slot="header" class="card-title">Edit Pass Information</h4>
      <form>  
        <div class="row">
          <div class="col-md-12">
            <base-input type="text"
                      label="Place of Interest"
                      placeholder="eg. Gardens by the Bay"
                      v-model="mandai.interest">
            </base-input>
          </div>
        </div>
        <div class="row">
          <div class="col-md-5">
            <label>Pass Type</label>
            <b-form-radio-group
                id="radio-group-1"
                v-model="selected"
                :options="options"
                :aria-describedby="ariaDescribedby"
                name="radio-options"
            ></b-form-radio-group>
          </div>
          <div class="col-md-3">
            <base-input type="number"
                      label="No. of Cards"
                      min="1"
                      placeholder="1"
                      v-model="mandai.number">
            </base-input>
          </div>
          <div class="col-md-4">
            <label>Replacement Fee ($)</label>
            <b-input-group prepend="$">
                <b-form-input
                    type="number"
                    min="0"
                    v-model="mandai.replacementfee"
                ></b-form-input>
            </b-input-group>
          </div>
        </div>
        <div class="row">
            <div class="col-md-12 corporate-pass-list">
                <label>Corporate Pass Numbers</label>
                <b-form-input
                    class="form-row"
                    id="input-1"
                    type="email"
                    placeholder="Enter Corporate Pass Number"
                    size="sm"
                    required
                    ></b-form-input>
            </div>
        </div>


        <div class="row">
            <div class="col-md-12">
                <label>Templates</label>
                <b-form-file
                v-model="file1"
                :state="Boolean(file1)"
                placeholder="Upload Email Template Here..."
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <b-form-file
                v-model="file2"
                :state="Boolean(file2)"
                placeholder="Upload PDF Attachement Template Here..."
                drop-placeholder="Drop file here..."
                ></b-form-file>
            </div>
        </div>
        <div class="text-center">
          <b-button type="submit" variant="primary" class="float-right" @click.prevent="updateProfile">
            Update Pass
          </b-button>
        </div>
        <div class="clearfix"></div>
      </form>
    </card>
  </template>
  <script>
    import Card from '../Cards/Card.vue'
  
    export default {
      components: {
        Card
      },
      data () {
        return {
          mandai: {
            interest: 'Mandai Wildlife Reserve',
            number: '',
            replacementfee: '',
            template: ''
          },
          selected: 'physical',
            options: [
            { text: 'Physical Card', value: 'physical' },
            { text: 'E-card', value: 'digital' }
            ]
        }
      },
      methods: {
        updateProfile () {
          alert('Your data: ' + JSON.stringify(this.user))
        },

      }
    }
  
  </script>
  <style>
  
  </style>
  