<template>
    <div class="bg-color">
        <div class="row">
            <div class="col-md-6 p-0">
                <div class="login-card">
                    <div class="login-box">
                        <div class="login-snip">
                            <input id="tab-1" type="radio" name="tab" class="sign-in" checked>
                            <label for="tab-1" class="tab">Authentication</label>
                            <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2"
                                class="tab">Sign Up</label>
                            <div class="login-space">
                                <div class="login">
                                    <div class="group">
                                        <label for="link" class="label">Check your email for the activation link</label>
                                        <input id="link" type="text" class="input" placeholder="Paste activation link"
                                            v-model="signup.email">
                                        <div id="valid-link" class="validate"></div>
                                    </div>
                                    <div class="group">
                                        <label for="pass" class="label">Password</label>
                                        <input id="pass" type="password" class="input" placeholder="Enter your password"
                                            v-model="signup.password">
                                        <div id="valid-password" class="validate"></div>
                                    </div>
                                    <div class="group">
                                        <label for="confirmpass" class="label">Confirm Password</label>
                                        <input id="confirmpass" type="password" class="input"
                                            placeholder="Re-enter you password" v-model="signup.confirmpassword">
                                        <div id="valid-confirm-password" class="validate"></div>
                                    </div>
                                    <div class="group">
                                        <label for="number" class="label">Contact Number</label>
                                        <input id="number" type="number" class="input"
                                            placeholder="Enter your contact number" v-model="signup.contact">
                                        <div id="valid-contact" class="validate"></div>
                                    </div>
                                    <div class="group">
                                        <input type="submit" class="button" value="Verify" @click="verify">
                                    </div>
                                    <div class="group">
                                        <label for="link" id="countdown" class="label"></label>
                                    </div>
                                    <div class="group">
                                        <input type="submit" class="resendbtn btnDisable" id="myBtn"
                                            value="Resend activation link" @click="resend">
                                    </div>
                                    <div class="hr"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
  
<script>
var timeleft = 10;
var downloadTimer = setInterval(function () {
    // const btn = document.getElementById("myBtn")
    if (timeleft <= 0) {
        clearInterval(downloadTimer);
        // btn.disabled = false;
        document.getElementById("countdown").innerHTML = "";
    } else {
        // btn.disabled = true;
        document.getElementById("countdown").innerHTML = "Didn't receive the link? Resend in " + timeleft + " seconds";
    }
    timeleft -= 1;
}, 1000);

export default {
    data() {
        return {
            signup: {
                email: '',
                password: '',
                confirmpassword: '',
                contact: ''
            }
        }
    },
    methods: {
        verify(event) {
            event.preventDefault()
            console.log(this.signup)
            if (this.email == "") {
                document.getElementById('valid-link').innerHTML = 'Please enter a link.'
            }
            if (this.password == "") {
                document.getElementById('valid-password').innerHTML = 'Please enter a password.'
            }
            if (this.confirmpassword == "") {
                document.getElementById('valid-confirm-password').innerHTML = 'Please confirm your password.'
            }
            if (this.password != this.confirmpassword) {
                document.getElementById('valid-password').innerHTML = 'Passwords do not match.'
                document.getElementById('valid-confirm-password').innerHTML = 'Passwords do not match.'
            }
            if (this.contact == "") {
                document.getElementById('valid-contact').innerHTML = 'Please enter a contact number.'
                return
            }
            alert("button works but not validation :(")
        },
        resend(event) {
            event.preventDefault()
            alert("Please check your email for the activation link")
        }
    }
}
</script>
  
<style>
.bg-color {
    background-color: #d7d7d7;
    height: 100%;
    width: 100%;
    min-height: 100vh;
    font: 600 16px/18px 'Open Sans', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
}

<<<<<<< Updated upstream
.login-box {
    width: 100%;
    margin: auto;
    max-width: 525px;
    min-height: 670px;
    position: relative;
    background: url('../assets/images/river_safari.jpg') no-repeat center;
    box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
}

.login-snip {
    width: 100%;
    height: 100%;
    position: absolute;
    padding: 90px 70px 50px 70px;
    background: rgba(0, 77, 77, .9);
}
=======
    <b-form @submit.stop.prevent="onSubmit">
      <b-form-group id="example-input-group-1" label="Contact Number" label-for="example-input-1">
        <b-form-input id="example-input-1" name="example-input-1" v-model="$v.form.contact.$model"
          :state="validateState('contact')" aria-describedby="input-1-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-1-live-feedback">This is a required field and must only contain numerical
          values.</b-form-invalid-feedback>
      </b-form-group>
>>>>>>> Stashed changes

.login-snip .login

/* .login-snip .sign-up-form  */
    {
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    position: absolute;
    transform: rotateY(180deg);
    backface-visibility: hidden;
    transition: all .4s linear;
}

/* .login-snip .sign-in,
.login-snip .sign-up,
.login-space .group .check {
    display: none;
} */

<<<<<<< Updated upstream
.login-snip .tab,
.login-space .group .label,
.login-space .group .button {
    text-transform: uppercase;
}

.login-snip .tab {
    font-size: 22px;
    margin-right: 15px;
    padding-bottom: 5px;
    margin: 0 15px 30px 0;
    display: inline-block;
    border-bottom: 2px solid transparent;
}
=======
        <b-form-invalid-feedback id="input-3-live-feedback">This is a required field and must be the same as password.
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-4" label="Check your email for the token and paste it here"
        label-for="example-input-4">
        <b-form-input id="example-input-4" name="example-input-4" v-model="$v.form.activation.$model"
          :state="validateState('activation')" aria-describedby="input-4-live-feedback"></b-form-input>
>>>>>>> Stashed changes

.login-snip .sign-in:checked+.tab,
.login-snip .sign-up:checked+.tab {
    color: #fff;
    border-color: #eeea11;
}

.login-space {
    min-height: 345px;
    position: relative;
    perspective: 1000px;
    transform-style: preserve-3d;
}

<<<<<<< Updated upstream
.login-space .group {
    margin-bottom: 15px;
}
=======
      <input type="button" class="btn btn-outline-primary btnDisable" id="myBtn" value="Resend activation link"
        @click="resend">
>>>>>>> Stashed changes

.login-space .group .label,
.login-space .group .input,
.login-space .group .button {
    width: 100%;
    color: #fff;
    display: block;
}

.login-space .group .input,
.login-space .group .button {
    border: none;
    padding: 15px 20px;
    border-radius: 25px;
    background: rgba(255, 255, 255, .1);
}

.login-space .group input[data-type="password"] {
    text-security: circle;
    -webkit-text-security: circle;
}

.login-space .group .label {
    color: rgb(209, 209, 209);
    font-size: 12px;
}

.login-space .group .button {
    background: #e0b50c;
}

<<<<<<< Updated upstream
.login-space .group label .icon {
    width: 15px;
    height: 15px;
    border-radius: 2px;
    position: relative;
    display: inline-block;
    background: rgba(255, 255, 255, 0.603);
}

.login-space .group label .icon:before,
.login-space .group label .icon:after {
    content: '';
    width: 10px;
    height: 2px;
    background: #fff;
    position: absolute;
    transition: all .2s ease-in-out 0s;
}

.login-space .group label .icon:before {
    left: 3px;
    width: 5px;
    bottom: 6px;
    transform: scale(0) rotate(0);
}

.login-space .group label .icon:after {
    top: 6px;
    right: 0;
    transform: scale(0) rotate(0);
}

.login-space .group .check:checked+label {
    color: #fff;
}

.login-space .group .check:checked+label .icon {
    background: #1161ee;
}

.login-space .group .check:checked+label .icon:before {
    transform: scale(1) rotate(45deg);
}

.login-space .group .check:checked+label .icon:after {
    transform: scale(1) rotate(-45deg);
}

.login-snip .sign-in:checked+.tab+.sign-up+.tab+.login-space .login {
    transform: rotate(0);
}

.login-snip .sign-up:checked+.tab+.login-space .sign-up-form {
    transform: rotate(0);
}

*,
:after,
:before {
    box-sizing: border-box
}

.clearfix:after,
.clearfix:before {
    content: '';
    display: table
}

.clearfix:after {
    clear: both;
    display: block
}

a {
    color: inherit;
    text-decoration: none
}

.hr {
    height: 2px;
    margin: 60px 0 50px 0;
    background: rgba(255, 255, 255, .2);
}

.foot {
    text-align: center;
}

.login-card {
    width: 500px;
}

.resendbtn {
    width: 100%;
    display: block;
    padding: 10px 15px;
    border-radius: 25px;
    border: none;
}

/* .btnEnable {
    background-color: #E6F9D2;
    border: 1px solid #97DE4C;
    color: #232323;
    cursor: pointer;
} */

.btnDisable {
    background-color: #ffffff;
    /* border: 1px solid #DD3939; */
    color: #232323;
    cursor: wait;
}

.validate {
    color: rgb(255, 68, 68);
    font-weight: normal;
    font-style: italic;
    padding-top: 5px;
}

::placeholder {
    color: #b3b3b3;
}
</style>
=======
<script>
import { validationMixin } from "vuelidate";
import { required, numeric, sameAs, minLength } from "vuelidate/lib/validators";


export default {
  mixins: [validationMixin],
  data() {
    return {
      form: {
        contact: null,
        password: null,
        cpassword: null,
        activation: null,
      },
    };
  },
  validations: {
    form: {
      contact: {
        required,
        numeric
      },
      password: {
        required,
        minLength: minLength(6)
      },
      cpassword: {
        required,
        minLength: minLength(6),
        sameAsPassword: sameAs('password')
      },
      activation: {
        required
      }
    }
  },
  methods: {
    validateState(contact) {
      const { $dirty, $error } = this.$v.form[contact];
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
      this.$router.push('/booking')
    },
    resend(event) {
      event.preventDefault()
      alert("Please check your email for the activation link")
    }
  }
};

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

</script>
>>>>>>> Stashed changes
