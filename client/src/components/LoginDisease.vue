<template>
  <div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <v-card class="mx-auto" max-width="400">
      <v-card-title class="align-end fill-height">Login for Doctor</v-card-title>
      <br />
      <v-row justify="center">
        <v-form ref="form" v-model="valid" lazy-validation style="width: 70%">
          <v-text-field v-model="username" type = "text" label="Username" outlined><v-icon large slot="prepend" color="purple">mdi-account</v-icon></v-text-field>
          <v-text-field v-model="password" type = "password" label="Password" outlined ><v-icon large  slot="prepend" color="purple">mdi-key-variant</v-icon></v-text-field>
        </v-form>
      </v-row>

      <v-row justify="center">
        <v-btn color="purple" @click="login">Login</v-btn>

      </v-row>
      <br />

    </v-card>
  </div>
</template>


<script>
import http from "../http-common";

export default {
  /* eslint-disable */
  data() {
    return {
        username: '',
        password: '',
    };
  },
  methods: {
      login() {
          http.get('/loginDoctor/'+this.username+ '/' + this.password)
          .then(response => {
              if (response.data != []) {
                   this.$router.push('/dis');
               } else {
                   alert('Please try again')
                   this.$refs.form.reset();this.clear()
                }
          })
      },
      goHome() {
          this.$router.push('/');
      },
  } //gg
};
</script>