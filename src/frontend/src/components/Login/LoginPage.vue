<template>
  <div class="body">
    <div class="row">
      <div class="col-md-6 mx-auto p-0">
        <div class="login-card">
          <div class="login-box">
            <div class="login-snip">
              <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1"
                class="tab">Login</label>
              <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
              <div class="login-space">
                <div class="login">
                  <div class="group">
                    <label for="email" class="label">Email</label>
                    <input id="email" type="email" class="input" placeholder="Enter your email" v-model="login.email">
                  </div>
                  <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" class="input" data-type="password"
                      placeholder="Enter your password" v-model="login.password">
                  </div>
                  <div class="group">
                    <input id="check" type="checkbox" class="check" v-model="checked">
                    <label for="check"><span class="icon"></span> Keep me Signed in</label>
                  </div>
                  <div class="group">
                    <input type="submit" class="button" value="Sign In" @click="submit">
                  </div>
                  <div class="hr"></div>
                  <div class="foot">
                    <a href="#">Forgot Password?</a>
                  </div>
                </div>
                <div class="sign-up-form">
                  <div class="group">
                    <label for="user" class="label">Name</label>
                    <input id="user" type="text" class="input" placeholder="Enter your name" v-model="signup.name">
                  </div>
                  <div class="group">
                    <label for="email" class="label">Email</label>
                    <input id="email" type="email" class="input" placeholder="Enter your email" v-model="signup.email">
                  </div>
                  <div class="group">
                    <label for="pass" class="label">Password</label>
                    <input id="pass" type="password" class="input" data-type="password"
                      placeholder="Create your password" v-model="signup.password">
                  </div>
                  <div class="group">
                    <label for="pass" class="label">Confirm Password</label>
                    <input id="pass" type="password" class="input" data-type="password"
                      placeholder="Confirm your password" v-model="signup.confirmed_password">
                  </div>
                  <div class="group">
                    <input type="submit" class="button" value="Get Activation Email" @click="register">
                  </div>
                  <div class="hr"></div>
                  <div class="foot">
                    <label for="tab-1">Already Member?</label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

<<<<<<< Updated upstream
=======
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

        <b-form-invalid-feedback id="input-1-live-feedback">This is a required field and must end with @nysi.org.sg or @sportsschool.edu.sg</b-form-invalid-feedback>
      </b-form-group>

      <div> </div>

      <b-form-group id="example-input-group-3" label="Password" label-for="example-input-3">
        <b-form-input id="example-input-3" name="example-input-3" v-model="$v.form.password.$model"
          :state="validateState('password')" aria-describedby="input-3-live-feedback"></b-form-input>

        <b-form-invalid-feedback id="input-3-live-feedback">This is a required field and the minimum length is 6.
        </b-form-invalid-feedback>
      </b-form-group>

      <b-form-group id="example-input-group-4" label-for="example-input-4">
        <b-form-checkbox id="checkbox-1" v-model="status" name="checkbox-1" value="remember_me"
          unchecked-value="forget_me">
          Remember me
        </b-form-checkbox>
      </b-form-group>

      <!-- <div>State: <strong>{{ status }}</strong></div> -->

      <b-button type="submit" variant="primary">Login</b-button>
      <!-- <b-button class="ml-2" @click="resetForm()">Reset</b-button> -->
    </b-form>
>>>>>>> Stashed changes
  </div>
</template>

<script>
<<<<<<< Updated upstream
=======
import axios from "axios";
import { validationMixin } from "vuelidate";
import { required, minLength } from "vuelidate/lib/validators";
import { helpers } from 'vuelidate/lib/validators';
const email_validation = helpers.regex('email', /(?:[a-z0-9]+@nysi.org.sg|[a-z0-9]+@sportsschool.edu.sg)/);

>>>>>>> Stashed changes
export default {
  data() {
    return {
<<<<<<< Updated upstream
      login: {
        email: '',
        password: '',
        checked: []
=======
      form: {
        email: '',
        password: '',
      },
      status: 'forget_me'
    };
  },
  validations: {
    form: {
      email: {
        required,
        email_validation
>>>>>>> Stashed changes
      },
      signup: {
        name: '',
        email: '',
        password: '',
        confirmed_password: ''
      }
    }
  },
  methods: {
    submit(event) {
      event.preventDefault()
      console.log(this.login)
      // if(!this.email) {
      //   alert("Please enter your email")
      //   return
      // } else if(!this.password) {
      //   alert("Please enter your password")
      //   return
      // }
    },
<<<<<<< Updated upstream
    register(event) {
      event.preventDefault()
      console.log(this.signup)
      alert("Please check your email for the activation link")

=======
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
      this.$router.push('/booking');

    },
    register() {
      this.$router.push('/register');
>>>>>>> Stashed changes
    }
  }
}
</script>

<style>
.body{
	color:#6a6f8c;
	background:#c8c8c8;
	font:600 16px/18px 'Open Sans',sans-serif;
  margin-top: 5%;
  /* display: flex;
  justify-content: center;
  align-items: center; */
}

.login-box{
	width:100%;
	margin:auto;
	max-width:525px;
	min-height:670px;
	position:relative;
	background:url('../../assets/images/river_safari.jpg') no-repeat center;
	box-shadow:0 12px 15px 0 rgba(0,0,0,.24),0 17px 50px 0 rgba(0,0,0,.19);
}
.login-snip{
	width:100%;
	height:100%;
	position:absolute;
	padding:90px 70px 50px 70px;
	background:rgba(0, 77, 77,.9);
}
.login-snip .login,
.login-snip .sign-up-form{
	top:0;
	left:0;
	right:0;
	bottom:0;
	position:absolute;
	transform:rotateY(180deg);
	backface-visibility:hidden;
	transition:all .4s linear;
}
.login-snip .sign-in,
.login-snip .sign-up,
.login-space .group .check{
	display:none;
}
.login-snip .tab,
.login-space .group .label,
.login-space .group .button{
	text-transform:uppercase;
}
.login-snip .tab{
	font-size:22px;
	margin-right:15px;
	padding-bottom:5px;
	margin:0 15px 10px 0;
	display:inline-block;
	border-bottom:2px solid transparent;
}
.login-snip .sign-in:checked + .tab,
.login-snip .sign-up:checked + .tab{
	color:#fff;
	border-color:#1161ee;
}
.login-space{
	min-height:345px;
	position:relative;
	perspective:1000px;
	transform-style:preserve-3d;
}
.login-space .group{
	margin-bottom:15px;
}
.login-space .group .label,
.login-space .group .input,
.login-space .group .button{
	width:100%;
	color:#fff;
	display:block;
}
.login-space .group .input,
.login-space .group .button{
	border:none;
	padding:15px 20px;
	border-radius:25px;
	background:rgba(255,255,255,.1);
}
.login-space .group input[data-type="password"]{
	text-security:circle;
	-webkit-text-security:circle;
}
.login-space .group .label{
	color:#aaa;
	font-size:12px;
}
.login-space .group .button{
	background:#1161ee;
}
.login-space .group label .icon{
	width:15px;
	height:15px;
	border-radius:2px;
	position:relative;
	display:inline-block;
	background:rgba(255,255,255,.1);
}
.login-space .group label .icon:before,
.login-space .group label .icon:after{
	content:'';
	width:10px;
	height:2px;
	background:#fff;
	position:absolute;
	transition:all .2s ease-in-out 0s;
}
.login-space .group label .icon:before{
	left:3px;
	width:5px;
	bottom:6px;
	transform:scale(0) rotate(0);
}
.login-space .group label .icon:after{
	top:6px;
	right:0;
	transform:scale(0) rotate(0);
}
.login-space .group .check:checked + label{
	color:#fff;
}
.login-space .group .check:checked + label .icon{
	background:#1161ee;
}
.login-space .group .check:checked + label .icon:before{
	transform:scale(1) rotate(45deg);
}
.login-space .group .check:checked + label .icon:after{
	transform:scale(1) rotate(-45deg);
}
.login-snip .sign-in:checked + .tab + .sign-up + .tab + .login-space .login{
	transform:rotate(0);
}
.login-snip .sign-up:checked + .tab + .login-space .sign-up-form{
	transform:rotate(0);
}

*,:after,:before{box-sizing:border-box}
.clearfix:after,.clearfix:before{content:'';display:table}
.clearfix:after{clear:both;display:block}
a{color:inherit;text-decoration:none}


.hr{
	height:2px;
	margin:60px 0 50px 0;
	background:rgba(255,255,255,.2);
}
.foot{
	text-align:center;
}
.login-card{
	width: 500px;
}

::placeholder{
color: #b3b3b3;
} 
</style>