<template>
  <v-app>
    <v-app-bar app>
      <div>
       <v-row justify="center">

        <v-btn @click="home" class="ma-2" outlined color="black">Home</v-btn>
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
     Person(){
      this.$router.push("/Admin");
    },

  }
};
</script>