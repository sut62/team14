<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
          <v-toolbar color="yellow" light flat>
                <v-chip class="display-1" color="black" outlined pill large ><v-icon right large>mdi-food</v-icon><v-icon right large>mdi-chevron-right</v-icon>View order of food
    </v-chip>
    <v-spacer></v-spacer>
                <div class="flex-grow-1"></div>
              </v-toolbar>
        <v-data-table  :headers="headers" :items="items" :items-per-page="10" color="green" class="elevation-6">
        </v-data-table>
        <br>
        <v-row justify="center">
        <b-button justify="center" >
        <router-link to="/order"><v-btn color="yellow" >back</v-btn></router-link>
        </b-button>
        </v-row>
        <br/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "PersonnelData",
  data() {
    return {
      headers: [
        {
          text: "ORDER ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "อาหารที่สั่ง", value: "food.name"},
        { text: "ชื่อของผู้ป่วย", value: "patient.addname" },
        { text: "นามสกุลของผู้ป่วย", value: "patient.addlastname" },
        { text: "ชื่อผู้สั่ง", value: "orderBy.firstname" },
        { text: "นามสกุลผู้สั่ง", value: "orderBy.lastname" },
        { text: "รายละเอียด", value: "details" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getOrder() {
      http
        .get("/foodorder")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getPersonnel();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getOrder();
  }
};
</script>
