<template>
  <v-container>
    <v-row justify="center">
        
        <v-col cols="5">
             <br>
               <v-text-field v-model="bedID" label="BED ID" outlined></v-text-field>
        </v-col>
        <v-col cols="2">
             <br>
              <div class="my-2">
                <v-btn @click="findBedOK" color="pink lighten-2" >Search</v-btn>
                
              </div>
        </v-col>
      <v-col cols="12">



    <v-toolbar color="pink accent-1" light flat>
        <v-chip class="display-1" color="white" outlined pill large >Hospital Bed<v-icon right large>mdi-hospital</v-icon></v-chip>
        <v-spacer></v-spacer>
        <div class="flex-grow-1"></div>
    </v-toolbar>


    <v-data-table  :headers="headers" :items="items" :items-per-page="10" color="green" class="elevation-6"></v-data-table>

     <div class="text-right">
         <br>
            <b-button>
                <router-link to="/bed"  ><v-btn  depressed large class="pink lighten-5" outlined color="pink accent-2"   >back</v-btn></router-link>
            </b-button>
         <br/>
    </div>
    
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "patientBed",
  data() {
    return {
      headers: [
        {
          text: "BED ID",
          align: "left",
          sortable: false,
          value: "patientBed_id"
        },
        { text: "ROOM", value: "atRoom.room_name"},
        { text: "ZONE", value: "atZone.zone_name"},
        { text: "PHYSICAL", value: "physicalBed.physBed_name"},
        { text: "CREATED BY", value: "createdBy.fullname"},//createdBy is relation in my major Entity &&  fullname is name of entity you can use
        { text: "DETAIL", value: "detail" },
        { text: "SHOW", value: "show" }
      ],
      items: [],
      patientBed: {
        patientBedId: "",
        zoneId: ""
      },
      bedID:""
    };
  },
methods: {
    /* eslint-disable no-console */
    getPatientBed() {
      http
        .get("/patientBed")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getPatientBed();
    },
      findBedOK() {
      http
        .get('/SearchPatientBed/' + this.bedID )
        .then(response => {
          console.log(response);
          if (response.data != []) {
                this.items = [response.data];
                console.log(this.items);
                //this.$router.push('/findBed');
          } else {
                this.items = [];
                console.log(this.items);
            this.$refs.form.reset();this.clear()
          }
        })
        .catch(e => {
          console.log(e);
        });

    },
    /* eslint-disable no-console */
  },

    
  mounted() {
    this.getPatientBed();
  }
};
</script>

