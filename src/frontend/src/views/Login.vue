<template>
  <div class="bg-color">
    <form class="needs-validation" novalidate>
      <div class="row">
        <div class="col-md-6 p-0">
          <div class="login-card">
            <div class="login-box">
              <div class="login-snip">
                <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1"
                  class="tab">Login</label>
                <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
                <div class="login-space needs-validation">
                  <div class="login">
                    <div class="group">
                      <label for="email" class="label">Email</label>
                      <input id="email" type="email" class="input" placeholder="Enter your email" v-model="login.email">
                      <div id="valid-email" class="validate"></div>
                    </div>
                    <div class="group">
                      <label for="pass" class="label">Password</label>
                      <input id="pass" type="password" class="input" data-type="password" placeholder="Enter your password" v-model="login.password">
                      <div id="valid-password" class="validate"></div>
                    </div>
                    <div class="group">
                      <input id="check" type="checkbox" class="check" v-model="checked">
                      <label for="check"><span class="icon"></span> Keep me signed in</label>
                    </div>
                    <div class="group">
                      <input type="submit" class="button" value="Sign In" @click="submit">
                    </div>
                    <div class="hr"></div>
                    <div class="foot">
                      <label for="tab-1">Forgot Password?</label>
                    </div>
                  </div>
                  <div class="sign-up-form">
                    <div class="group">
                      <label for="user" class="label">Name</label>
                      <input id="user" type="text" class="input" placeholder="Enter your name" v-model="register.name">
                      <div id="valid-name" class="validate"></div>
                    </div>
                    <div class="group">
                      <label for="email" class="label">Email</label>
                      <input id="reg-email" type="email" class="input" placeholder="Enter your email" v-model="register.regemail">
                      <div id="valid-reg-email" class="validate"></div>
                    </div>
                    <div class="group">
                      <input type="submit" class="button" value="Get Activation Email" @click="register">
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </form>
  </div>
</template>
  
<script>

export default {
  data() {
    return {
      login: {
        email: '',
        password: '',
        checked: []
      },
      register: {
        name: '',
        regemail: ''
      }
    }
  },
  methods: {
    submit(event) {
      event.preventDefault()
      console.log(this.login)
      if(this.email == "") {
        document.getElementById('valid-email').innerHTML = 'Please enter a valid email.'
      }
      if(this.password == "") {
        document.getElementById('valid-password').innerHTML = 'Please enter a valid password.'
        return
      }
    },
    register(event) {
      event.preventDefault()
      console.log(this.register)
      if(this.name == "") {
        document.getElementById('valid-name').innerHTML = 'Please enter a name.'
      }
      if(this.regemail == "") {
        document.getElementById('valid-reg-email').innerHTML = 'Please enter an email.'
        return
      }
      if(this.name != "" && this.regemail != "") {
        this.$router.push('/Authentication')
      }
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

.login-box {
  width: 100%;
  margin: auto;
  max-width: 525px;
  min-height: 670px;
  position: relative;
  /* background: url('../assets/images/river_safari.jpg') no-repeat center; */
  box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0 rgba(0, 0, 0, .19);
}

.login-snip {
  width: 100%;
  height: 100%;
  position: absolute;
  padding: 90px 70px 50px 70px;
  background: rgba(0, 77, 77, .9);
}

.login-snip .login,
.login-snip .sign-up-form {
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  position: absolute;
  transform: rotateY(180deg);
  backface-visibility: hidden;
  transition: all .4s linear;
}

.login-snip .sign-in,
.login-snip .sign-up,
.login-space .group .check {
  display: none;
}

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

.login-space .group {
  margin-bottom: 15px;
}

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

.login-space .group .check+label {
  color: rgb(206, 206, 206);
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

.validate{
  color: rgb(255, 68, 68);
  font-weight: normal;
  font-style: italic;
  padding-top: 5px;
}

::placeholder{
color: #b3b3b3;
} 
</style>