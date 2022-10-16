<template>
    <card>
      <h4 slot="header" class="card-title"><strong>Create New Attraction</strong></h4>
      <form>  
        <div class="row">
          <div class="col-md-12">
            <base-input type="text"
                      label="Corporate Pass Name"
                      placeholder="eg. Singapore Gardens"
                      v-model="mandai.passname">
            </base-input>
          </div>
        </div>
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
          <div class="col-md-6">
            <label>Pass Replacement Fee ($)</label>
            <b-input-group prepend="$">
                <b-form-input
                    type="number"
                    min="0"
                    v-model="mandai.replacementfee"
                ></b-form-input>
            </b-input-group>
          </div>
          <div class="col-md-6">
            <label>Pass Type</label>
            <b-form-radio-group
                id="radio-group-1"
                v-model="selected"
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
            Create Attraction
          </b-button>
        </div>
        <div class="clearfix"></div>
      </form>
    </card>
  </template>
  <script>
    import Card from '../Cards/Card.vue'
    import { validationMixin } from "vuelidate";
    import { required, minLength } from "vuelidate/lib/validators";

  
    export default {
      mixins: [validationMixin],
      components: {
        Card
      },
      data () {
        return {
          validations: {
            form: {
              food: {
                required
              },
              name: {
                required,
                minLength: minLength(3)
              }
            }
          },
          no_passes: 1,
          mandai: {
            passname: 'Mandai Wildlife Reserve',
            interest: 'Singapore Zoo',
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
        validateState(name) {
        const { $dirty, $error } = this.$v.form[name];
        return $dirty ? !$error : null;
        },
        resetForm() {
          this.form = {
            name: null,
            food: null
          };

          this.$nextTick(() => {
            this.$v.$reset();
          });
        },
        updateProfile () {
          this.$v.form.$touch();
          if (this.$v.form.$anyError) {
            return;
          }
          alert('Your data: ' + JSON.stringify(this.user))
        },

      }
    }
  
  </script>
  <style>
  
  </style>
  