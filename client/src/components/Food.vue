<template >
  <v-container >
    <v-card
      max-width="1500"
      class="mx-auto">
    <v-layout text-center wrap>
      <v-flex mb-4>
        <h1 class="display-2 font-weight-bold mb-3">Food</h1>
      </v-flex>
    </v-layout>

    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form" >
          

          <div>
          <v-row>
            <v-col cols="10">
             <v-text-field
                outlined
                label="Food name"
                v-model="food.Name"
                  :items="Name"
                  item-text="name"
                  item-value="name"
                :rules="[(v) => !!v || 'Item is required']"
                required 
              ><v-icon slot="prepend" color="black" >mdi-food-variant</v-icon>
              </v-text-field>
            </v-col>
          </v-row>
            <v-row>
              <v-col cols="10">
                <v-select
                  label="Personnel"
                  outlined
                  v-model="food.personnel"
                  :items="Personnels"
                  item-text="firstname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-account-box</v-icon>
                </v-select>
              </v-col>
            </v-row>

             <v-row>
              <v-col cols="10">
                <v-select
                  label="Foodtype"
                  outlined
                  v-model="food.foodtype"
                  :items="Foodtypes"
                  item-text="type"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-silverware</v-icon>
                </v-select>
              </v-col>
            </v-row>


            <v-row>
              <v-col cols="10">
                <v-select
                  label="Meal"
                  outlined
                  v-model="food.meal"
                  :items="Meals"
                  item-text="mealtype"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-weather-sunny</v-icon>
                </v-select>
              </v-col>
            </v-row>

            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveFood" :class="{ red: !valid, green: valid }">save</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
      
              </v-col>
            </v-row>
            <br />
          </div>
        </v-form>
      </v-col>
    </v-row>
    </v-card>
  </v-container>

  


</template>

<script>
import http from "../http-common";

export default {
  name: "food",
  data() {
    return {
      food: {
        
        Name: "",
        personnel: "",
        foodtype: "",
        mealid: ""
      },
      Name:[],
      Personnels:[],
      Foodtypes:[],
      Meals:[],
      valid: false
    };
  },
  methods: {
/* eslint-disable */
    getPersonnels() {
      http
        .get("/personnel")
        .then(response => {
          this.Personnels = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getFoodtypes() {
      http
        .get("/foodtype")
        .then(response => {
          this.Foodtypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getMeals() {
      http
        .get("/meal")
        .then(response => {
          this.Meals = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveFood() {
      http
        .post(
          "/food/"+
            this.food.Name +
            "/" +
            this.food.personnel +
            "/" +
            this.food.foodtype +
            "/" +
            this.food.meal,
          this.food
        )
        .then(response => {
          console.log(response);
          this.$refs.form.reset();
        })
        
        .catch(e => {
          console.log(e);
        });
      
    },
    
    clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getPersonnels();
      this.getFoodtypes();
      this.getMeals();
    }
  },
  mounted() {
    this.getPersonnels();
    this.getFoodtypes();
    this.getMeals();
  }
};
</script>
