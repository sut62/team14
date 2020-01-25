
<template>
  <v-container>
    <v-row justify="center">
        <v-col cols="5">
             <br>
               <v-text-field v-model="id" label="กรอก ID" outlined></v-text-field>
        </v-col>
        <v-col cols="2">
             <br>
              <div class="my-2">
                <v-btn @click="findDis" color="green lighten-2" > ค้นหา  </v-btn> 
              </div>
        </v-col>
      <v-col cols="12">

    <v-toolbar color="green accent-1" light flat>
        <v-chip class="display-1" color="brown" outlined pill large > ข้อมูลโรค <v-icon right large>mdi-hospital</v-icon></v-chip>
        <v-spacer></v-spacer>
        <div class="flex-grow-1"></div>
    </v-toolbar>


    <v-data-table  :headers="headers" :items="items" :items-per-page="10" color="green" class="elevation-6"></v-data-table>

     <div class="text-right">
         <br>
                <router-link to="/dis"  ><v-btn  depressed large class="green lighten-5" outlined color="green accent-2"   >back</v-btn></router-link>
         <br/>
    </div>
    
      </v-col>
    </v-row>
  </v-container>
</template>
<script>
import http from "../http-common";

export default {
  name: "ViewDisease",
  data() {
    return {
      headers: [
        {
          text: "Disease ID",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "ชื่อโรค" , value: "name"},
        { text: "อาการ", value: "symptom"},
        { text: "ประเภทโรค", value: "type.typename"},
        { text: "ช่วงอายุที่พบบ่อย", value: "lifespan.age"},
        { text: "แพทย์ผู้เพิ่มโรค", value: "personnel.fullname"}
      ],
      items: [],
      id:""
    };
  },
  methods: {

    getDisease() {
      http
        .get("/disease")
        .then(response => {
          this.items = response.data;
          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    refreshList() {
      this.getDisease();
    },

    findDis() {
      http
        .get('/SearchDis/' + this.id )
        .then(response => {
          console.log(response);
          if (response.data != []) {
                this.items = [response.data];
                console.log(this.items);
                //this.$router.push('/findBed');
          } else {
          alert('ไม่พบข้อมูล')
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
    /* eslint-disable no-console */
  
  mounted() {
    this.getDisease();
  }
};
</script>
