<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="firstName" class="sr-only">First name:</label>
        <input
          type="text"
          id="firstName"
          class="form-control"
          placeholder="First name"
          v-model="user.firstName"
          required
          autofocus
        />
      <label for="lastName" class="sr-only">Last name:</label>
        <input
          type="text"
          id="lastName"
          class="form-control"
          placeholder="Last name"
          v-model="user.lastName"
          required
          autofocus
        />
      
      <label for="email" class="sr-only">Email:</label>
        <input
          type="email"
          id="email"
          class="form-control"
          placeholder="Email"
          v-model="user.email"
          required
          autofocus
        />

      <label for="password" class="sr-only">Password:</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
          title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters"
          v-model="user.password"
          required
        />
        <label for="confirmPassword" class="sr-only">Confirm password:</label>
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        firstName: '',
        lastName: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        email: ''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              //testing
                authService.login(this.user)
                  .then(response => {
                    if (response.status == 200) {
                      this.$store.commit("SET_AUTH_TOKEN", response.data.token);
                      this.$store.commit("SET_USER", response.data.user);
                      this.$router.push("/profile_settings");
                    }
                  })
              //testing

              // this.$router.push({
              //   path: '/login',
              //   query: { registration: 'success' },
              // });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
           // this.registrationErrorMsg = 'Bad Request: Validation Errors';
              this.registrationErrorMsg = 'User already exists.'
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
  button {
    border-radius: 85px;
  }
</style>
