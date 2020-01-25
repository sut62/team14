<template>
  <v-app>
    <v-app-bar app>
      <div>
       <v-row justify="center">

        <v-btn @click="home" class="ma-2" outlined color="black">Home</v-btn> 
        <v-btn @click="disease" class="ma-2" outlined color="purple">Disease</v-btn>
        <v-btn @click="nurse" class="ma-2" outlined color="pink">Nurse</v-btn>
        <v-btn @click="Regis" class="ma-2" outlined color="green">Register</v-btn>
        <v-btn @click="Login" class="ma-2" outlined color="orange">Nutritionost</v-btn> 
        </v-row>
        </div>
        <v-spacer></v-spacer>
        <v-btn @click="Person" class="ma-2" outlined color="blue">Loginpersonnel</v-btn>
    </v-app-bar>
 
       

    <v-content>
      <!-- <VideoRental/> -->
      <router-view />
    </v-content>
  </v-app>
</template>


<script>

export default {
  name: 'Home',
  methods:{
    home(){
      this.$router.push("/login");
    },
  Regis(){
      this.$router.push("/loginregis");
    },
    nurse(){
      this.$router.push("/nurselogin");
    },
    Login(){
      this.$router.push("/loginnutr");
    },
    disease(){
      this.$router.push("/logdis");
    }, 
     Person(){
      this.$router.push("/Admin");
    },

  }
};
</script>