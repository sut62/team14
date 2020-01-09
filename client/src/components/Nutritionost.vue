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
      <br />

        <v-row justify="center">
        <b-button>
        <router-link to="/Food"><v-btn class="ma-2" outlined color="blue darken-1">Food</v-btn></router-link>
        </b-button>



        <b-button>
        <router-link to="/food"><v-btn style="margin-left: 15px;" class="ma-2" outlined color="blue darken-1">FoodOrder</v-btn></router-link>
        </b-button>
        </v-row>
        <br />

        

    </v-card>
  </v-container>
</template>

<script>

</script>
