<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
          <v-toolbar color="indigo lighten-1" light flat>
                <v-chip class="display-1" color="white" outlined pill large >ข้อมูลบุคลากรในโรงพยาบาล<v-icon right large>mdi-hospital</v-icon>
    </v-chip>
    <v-spacer></v-spacer>
                <div class="flex-grow-1"></div>
              </v-toolbar>
        <v-data-table  :headers="headers" :items="items" :items-per-page="10" color="green" class="elevation-6">
        </v-data-table>
        <br>
        <b-button>
        <router-link to="/per"><v-btn color="blue lighten-5">back</v-btn></router-link>
        </b-button>
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
          text: "User ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "ชื่อ", value: "firstname"},
        { text: "นามสกุล", value: "lastname"},
        { text: "เบอร์โทรศัพท์", value: "telephone"},
        { text: "วันเกิด", value: "birthday"},
        { text: "ระดับการศึกษา", value: "level.educationlevel_name" },
        { text: "สถานภาพการสมรส", value: "status.maritalstatus_name" },
        { text: "ตำแหน่ง", value: "posit.position_name" }
      ],
      items: []
    };
  },
  methods: {
    /* eslint-disable no-console */
    getPersonnel() {
      http
        .get("/personnel")
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
    this.getPersonnel();
  }
};
</script>
