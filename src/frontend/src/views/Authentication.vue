<template>

  <div>

    <b-form @submit.stop.prevent="onSubmit">
      <b-form-group id="example-input-group-1" label="Email" label-for="example-input-1">
        <b-form-input id="example-input-1" name="example-input-1" v-model="$v.form.email.$model"
          :state="validateState('email')" aria-describedby="input-1-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-1-live-feedback">This is a required field.</b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-2" label="Password" label-for="example-input-3">
        <b-form-input id="example-input-2" name="example-input-2" v-model="$v.form.password.$model"
          :state="validateState('password')" aria-describedby="input-2-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-2-live-feedback">This is a required field and the minimum length is 6.
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-3" label="Confirm Password" label-for="example-input-3">
        <b-form-input id="example-input-3" name="example-input-3" v-model="$v.form.cpassword.$model"
          :state="validateState('cpassword')" aria-describedby="input-3-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-3-live-feedback">This is a required field and the minimum length is 6.
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-4" label="Check your email for the activation link and paste it here"
        label-for="example-input-4">
        <b-form-input id="example-input-4" name="example-input-4" v-model="$v.form.activation.$model"
          :state="validateState('activation')" aria-describedby="input-4-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-4-live-feedback">This is a required field and the minimum length is 6.
        </b-form-invalid-feedback>
      </b-form-group>

      <div class="group">
        <label for="link" id="countdown" class="label"></label>
      </div>

      <input type="button" class="btn btn-outline-primary btnDisable" id="myBtn"
        value="Resend activation link" @click="resend">

      <br>
      <br>

      <b-button type="submit" variant="primary">Register</b-button>

    </b-form>
  </div>
</template>

<style>
body {
  padding: 1rem;
}

.btnDisable {
  cursor: wait;
}
</style>

<script>
import { validationMixin } from "vuelidate";
import { required, minLength } from "vuelidate/lib/validators";

var timeleft = 10;
var downloadTimer = setInterval(function () {
  const btn = document.getElementById("myBtn")
  if (timeleft <= 0) {
    clearInterval(downloadTimer);
    btn.disabled = false;
    document.getElementById("countdown").innerHTML = "";
  } else {
    btn.disabled = true;
    document.getElementById("countdown").innerHTML = "Didn't receive the link? Resend in " + timeleft + " seconds";
  }
  timeleft -= 1;
}, 1000);

export default {
  mixins: [validationMixin],
  data() {
    return {
      form: {
        email: null,
        password: null,
        cpassword: null,
        activation: null,
      },
    };
  },
  validations: {
    form: {
      email: {
        required
      },
      password: {
        required
      },
      cpassword: {
        required
      },
      activation: {
        required
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
    validateState(cpassword) {
      const { $dirty, $error } = this.$v.form[cpassword];
      return $dirty ? !$error : null;
    },
    validateState(activation) {
      const { $dirty, $error } = this.$v.form[activation];
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
    resend(event) {
      event.preventDefault()
      alert("Please check your email for the activation link")
    }
  }
};
</script>