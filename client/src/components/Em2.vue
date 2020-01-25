<template >
  <v-container >
    <v-card
      max-width="1500"
      class="mx-auto"
      color="blue"
      >
      <br />
    <v-layout text-center wrap>
      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">สำหรับพนักงาน</h1>
      </v-flex>
    </v-layout>

    </v-card>
    
    <v-card
      max-width="1500"
      class="mx-auto">
      <div>
       <v-row justify="center">
        <v-btn @click="disease" class="ma-2" outlined color="purple">Disease</v-btn>
        <v-btn @click="nurse" class="ma-2" outlined color="pink">Nurse</v-btn>
        <v-btn @click="Regis" class="ma-2" outlined color="green">Register</v-btn>
        <v-btn @click="Food" class="ma-2" outlined color="orange">Food</v-btn>
        <v-btn @click="order" class="ma-2" outlined color="red">orderFood</v-btn>
        </v-row>
        </div>

        

    </v-card>
  </v-container>
</template>

<script>

export default {
  name: 'Home',
  methods:{
    Regis(){
      this.$router.push("/regis");
    },
    nurse(){
      this.$router.push("/bed");
    },
    Food(){
      this.$router.push("/Food");
    },
    disease(){
      this.$router.push("/dis");
    },
     order(){
      this.$router.push("/order");
    },

  }
};
</script>
