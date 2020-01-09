<template>
  <div>
    <br />
    <br />
    <br />
    <br />
    <br />
    <br />
    <v-card class="mx-auto" max-width="400">
      <label
                    class="font-weight-black  green--text  text-uppercas text-uppercas"
                    style="font-size:2em  "
                  > Longin Medical Records </label>
      <br />
      <v-row justify="center">
        <v-form ref="form" v-model="valid" lazy-validation style="width: 70%">
          <v-text-field v-model="username" type = "text" label="Username" outlined><v-icon large slot="prepend" color="blue">mdi-account</v-icon></v-text-field>
          <v-text-field v-model="password" type = "password" label="Password" outlined ><v-icon large  slot="prepend" color="blue">mdi-key-variant</v-icon></v-text-field>
        </v-form>
      </v-row>

      <v-row justify="center">
        <v-btn @click="login">Login</v-btn>

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
          http.get('/LonginMedicalRecords/'+this.username+ '/' + this.password)
          .then(response => {
              if (response.data != []) {
                   this.$router.push('/Regis');
               } else {
                   alert('Please try again')
                   this.$refs.form.reset();this.clear()
                }
          })
      },
      goHome() {
          this.$router.push('/');
      },
  }
};
</script>