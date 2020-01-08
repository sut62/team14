<template>
  <v-app>
    <v-app-bar app>
      <div>
       <v-row justify="center">
        <v-btn @click="home" class="ma-2" outlined color="black">Home</v-btn>
        <v-btn @click="food" class="ma-2" outlined color="orange">Food</v-btn>
        <v-btn @click="bed" class="ma-2" outlined color="pink">Hospital  Bed</v-btn>
        <v-btn @click="disease" class="ma-2" outlined color="purple">Disease</v-btn>
        <v-btn @click="Regis" class="ma-2" outlined color="green">Register</v-btn>
        <v-btn @click="order" class="ma-2" outlined color="grey">Order  Food</v-btn>
        
        </v-row>
        </div>
        <v-spacer></v-spacer>
        <v-btn @click="Person" class="ma-2" outlined color="blue">ManagePersonnel</v-btn>
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
      this.$router.push("/home");
    },
    Regis(){
      this.$router.push("/Regis");
    },
    bed(){
      this.$router.push("/bed");
    },
    food(){
      this.$router.push("/Food");
    },
     order(){
      this.$router.push("/order");
    },
     disease(){
      this.$router.push("/disease");
    },
     Person(){
      this.$router.push("/per");
    },

  }
};
</script>