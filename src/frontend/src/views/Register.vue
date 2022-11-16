<template>

<div style="margin-top: 5%; margin-left: 10%; margin-right: 10%">
<div><img src="../assets/logo.png" class=" mx-auto my-5 d-block"></div>
  <ul class="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
      <li class="nav-item" role="presentation">
        <a class="nav-link" id="tab-login" data-mdb-toggle="pill" href="#pills-login" role="tab"
          aria-controls="pills-login" aria-selected="false" @click="login()">Login</a>
      </li>
      <li class="nav-item" role="presentation">
        <a class="nav-link active" id="tab-register" data-mdb-toggle="pill" href="#pills-register" role="tab"
          aria-controls="pills-register" aria-selected="true">Register</a>
      </li>
    </ul>

    <b-form @submit.stop.prevent="onSubmit">
      <b-form-group id="example-input-group-1" label="Name" label-for="example-input-1">
        <b-form-input id="example-input-1" name="example-input-1" v-model="$v.form.name.$model"
          :state="validateState('name')" aria-describedby="input-1-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-1-live-feedback">This is a required field and must not be longer than 50
          characters.</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-3" label="Email" label-for="example-input-3">
        <b-form-input id="example-input-3" name="example-input-3" v-model="$v.form.email.$model"
          :state="validateState('email')" aria-describedby="input-3-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-3-live-feedback">This is a required field and must end with @nysi.org.sg or
          @sportsschool.edu.sg
        </b-form-invalid-feedback>
      </b-form-group>

      <b-button type="submit" variant="primary">Get Activation Link</b-button>

    </b-form>
  </div>
</template>

<style>
body {
  padding: 1rem;
}
</style>

<script>
import axios from "axios";
import { validationMixin } from "vuelidate";
import { required, maxLength, helpers } from "vuelidate/lib/validators";
const email_validation = helpers.regex('email', /(?:[a-z0-9]+@nysi.org.sg|[a-z0-9]+@sportsschool.edu.sg)/);

export default {
  mixins: [validationMixin],
  data() {
    return {
      form: {
        name: '',
        email: '',
      },
      api: {
        register: 'http://localhost:8080/auth/signup',
      },
    };
  },
  validations: {
    form: {
      name: {
        required,
        maxLength: maxLength(50)
      },
      email: {
        required,
        email_validation
      }
    }
  },
  methods: {
    validateState(name) {
      const { $dirty, $error } = this.$v.form[name];
      return $dirty ? !$error : null;
    },
    validateState(email) {
      const { $dirty, $error } = this.$v.form[email];
      return $dirty ? !$error : null;
    },

    onSubmit() {
      this.$v.form.$touch();
      if (this.$v.form.$anyError) {
        return;
      }

      return axios
        .post(this.api.register, {
          "name": this.form.name,
          "email": this.form.email,
        })
        .then((response) => {
          console.log(response.data);
          this.$router.push('/authentication')
        })
        .catch((error) => {
          console.log(error);
          console.log(error.response.request.response);
          if (error.response.request.response == "{\"message\":\"Error: User with this email is already registered. Please check email for verification link.\"}") {
            this.$alert("Error: User with this email is already registered. Please check email for verification link.")
          }
        });
    },
    login() {
      this.$router.push('/login')
    }
  }
};
</script>