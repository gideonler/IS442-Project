<template>
  <card>
    <h4 slot="header" class="card-title"><strong>Create New Passes</strong></h4>
    <form>  
      <div class="row">
        <div  class="col-md-6">
          <label>Corporate Pass</label>
          <b-form-select v-model="selected_attraction" 
            :options="attractions"
            required
          ></b-form-select>
        </div>
        <div class="col-md-6">
          <base-input type="number"
                    v-model= "no_passes"
                    label="No. of Cards"
                    min="1"
                    max="10"
                    placeholder="1"
                    >
          </base-input> 
          </div>
      </div>

      <div class="row">
          <div class="col-md-12">
              <label class="mt-0">Corporate Pass Numbers</label>
              <b-form-input
                  v-for="index in parseInt(no_passes)"
                  :key="index"
                  class="form-row mb-1"
                  type="email"
                  placeholder="Enter Corporate Pass Number"
                  size="sm"
                  required
                  ></b-form-input>
          </div>
      </div>

      <div class="text-center">
        <b-button type="submit" variant="primary" class="float-right" @click.prevent="createPass">
          Create Passes
        </b-button>
      </div>
      <div class="clearfix"></div>

      <div>
        <PassCreationConfirmation></PassCreationConfirmation>
      </div>
    </form>
  </card>
  
</template>
<script>
  import Card from '../Cards/Card.vue'
import PassCreationConfirmation from './PassCreationConfirmation.vue';


  export default {
    components: {
      Card,
      PassCreationConfirmation
    },
    data () {
      return {
        no_passes: 1,
        attractions: [
          { value: null, text: 'Select Corporate Pass' },
          { text: 'Mandai Wildlife Reserve', value: 'Mandai Wildlife Reserve,' },
          { text: 'Gardens By the Bay', value: 'Gardens By the Bay' }
          ],
        selected_attraction: null,
        selected: 'physical',
          options: [
          { text: 'Physical Card', value: 'physical' },
          { text: 'E-card', value: 'digital' }
          ]
      }
    },
    methods: {
      createPass () {
        //TODO: API Call to update database
        this.$root.$refs.PassCreationConfirmation.showModal(this.no_passes, this.selected_attraction);
      },

    }
  }

</script>
<style>

</style>
