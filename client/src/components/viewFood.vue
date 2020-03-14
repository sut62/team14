<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
          <v-toolbar color=blue light flat>
                <v-chip class="display-1" color="white" outlined pill large >ข้อมูลเมนูอาหาร</v-chip>
    <v-spacer></v-spacer>
                <div class="flex-grow-1"></div>
              </v-toolbar>
        <v-data-table  :headers="headers" :items="items" :items-per-page="10" color="green" class="elevation-6">
        </v-data-table>
        <v-row justify="center">
        <router-link to="/Food"><v-btn class="ma-2" outlined color="blue darken-1">Back</v-btn></router-link>
        </v-row>
        <br>
        <br/>
         
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "FoodData",
  data() {
    return {
      headers: [
        {
          text: "Food ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "ชื่ออาหาร", value: "name"},
        { text: "ชื่อพนักงาน" , value: "createdby.firstname"},
        { text: "นามสกุลพนักงาน" , value: "createdby.lastname"},
        { text: "ประเภทอาหาร", value: "typeby.type"},
        { text: "มื้ออาหาร", value: "mealby.mealtype"}
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getFood() {
      http
        .get("/food")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getFood();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getFood();
  }
};
</script>
