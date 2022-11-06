<template>

  <div>
    <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
      <li class="nav-item" role="presentation">
        <a class="nav-link active" id="tab-login" data-mdb-toggle="pill" href="#" role="tab"
          aria-controls="pills-login" aria-selected="true">Login</a>
      </li>
      <li class="nav-item" role="presentation">
        <a class="nav-link" id="tab-register" data-mdb-toggle="pill" href="" role="tab"
          aria-controls="pills-register" aria-selected="false" @click="register()">Register</a>
      </li>
    </ul>

    <b-form @submit.stop.prevent="onSubmit">
      <b-form-group id="example-input-group-1" label="Email" label-for="example-input-1">
        <b-form-input id="example-input-1" name="example-input-1" v-model="$v.form.email.$model"
          :state="validateState('email')" aria-describedby="input-1-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-1-live-feedback">This is a required field.</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-3" label="Password" label-for="example-input-3">
        <b-form-input id="example-input-3" name="example-input-3" v-model="$v.form.password.$model"
          :state="validateState('password')" aria-describedby="input-3-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-3-live-feedback">This is a required field and the minimum length is 6.
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-4" label-for="example-input-4">
        <b-form-checkbox id="checkbox-1" v-model="status" name="checkbox-1" value="accepted"
          unchecked-value="not_accepted">
          Remember me
        </b-form-checkbox>
      </b-form-group>

      <!-- <div>State: <strong>{{ status }}</strong></div> -->

      <!-- 
      <b-form-group id="example-input-group-2" label="Food" label-for="example-input-2">
        <b-form-select
          id="example-input-2"
          name="example-input-2"
          v-model="$v.form.food.$model"
          :options="foods"
          :state="validateState('food')"
          aria-describedby="input-2-live-feedback"
        ></b-form-select>

        <b-form-invalid-feedback id="input-2-live-feedback">This is a required field.</b-form-invalid-feedback>
      </b-form-group> -->

      <b-button type="submit" variant="primary">Sign In</b-button>
      <!-- <b-button class="ml-2" @click="resetForm()">Reset</b-button> -->
    </b-form>
  </div>
</template>

<style>
body {
  padding: 1rem;
}
</style>

<script>
import { validationMixin } from "vuelidate";
import { required, minLength } from "vuelidate/lib/validators";

export default {
  mixins: [validationMixin],
  data() {
    return {
      form: {
        email: null,
        password: null,
      },
      status: 'not_accepted'
    };
  },
  validations: {
    form: {
      email: {
        required
      },
      password: {
        required,
        minLength: minLength(6)
      }
    }
  },
  methods: {
    validateState(email) {
      const { $dirty, $error } = this.$v.form[email];
      return $dirty ? !$error : null;
    },
    validateState(password) {
      const { $dirty, $error } = this.$v.form[password];
      return $dirty ? !$error : null;
    },
    // resetForm() {
    //   this.form = {
    //     email: null,
    //     food: null
    //   };

    //   this.$nextTick(() => {
    //     this.$v.$reset();
    //   });
    // },
    onSubmit() {
      this.$v.form.$touch();
      if (this.$v.form.$anyError) {
        return;
      }

      alert("Form submitted!");
    },
    register() {
      this.$router.push('/register');
    }
  }
};
</script>