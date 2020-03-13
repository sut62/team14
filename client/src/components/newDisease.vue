<template>
 
 <v-row justify="center">
  <v-col cols="9">
  <v-container class="light-green lighten-4" >
    <v-layout text-center wrap>
      <v-flex mb-4>
        <label class="display-2 font-weight-bold brown--text mb-3">Add New Disease</label>
      </v-flex>
            <v-btn @click="viewdisease" color="green"> ดูข้อมูลโรค </v-btn>
    </v-layout>
    
     <v-row justify="center">
       <v-icon slot="prepend" color="pink">mdi-hospital</v-icon>
        <v-col cols="30" sm="5">
          <v-text-field
            id ="10"
            v-model="disease.name"
            label="ชื่อโรค"
            clearable
          ></v-text-field>
        </v-col>
        </v-row>

        <v-row justify="center">
          <v-icon slot="prepend" color="blue"> mdi-pencil</v-icon>
        <v-col cols="12" sm="8">
          <v-text-field
            v-model="disease.symptom"
            id ="11"
            label="อาการ"
            clearable
          ></v-text-field>
        </v-col>
        </v-row>
    
        <v-row justify="center">
              <v-col cols="4">
                <v-select
                  label="ชนิดของโรค"
                  solo
                  background-color="lime lighten-3"
                  v-model="disease.typeID"
                  :items="types"
                  item-text="typename"
                  item-value="id"
                  
                ><v-icon slot="prepend" color="green"> mdi-hotel</v-icon></v-select>
              </v-col>
            </v-row>
      
        <v-row justify="center">
              <v-col cols="4">
                <v-select
                  label="ช่วงอายุที่พบบ่อย"
                  solo
                  background-color="lime lighten-3"
                  v-model="disease.lifespanID"
                  :items="lifespans"
                  item-text="age"
                  item-value="id"
                  
                ><v-icon slot="prepend" color="orange">mdi-human-male-female</v-icon></v-select>
              </v-col>
            </v-row>

             <v-row justify="center">
              <v-col cols="4">
                <v-select
                  label="แพทย์ผู้เพิ่มโรค"
                  solo
                  background-color="lime lighten-3"
                  v-model="disease.personnelID"
                  :items="personnels"
                  item-text="firstname"
                  item-value="id"
                  
                > <v-icon slot="prepend" color="red">mdi-account-check</v-icon></v-select>
              </v-col>
            </v-row>
              <v-row  >
              <v-col cols="12">
                <v-btn color ="blue" style="margin-left: 600px;" @click="saveDisease" > บันทึก </v-btn>
                <v-btn color ="red" style="margin-left: 20px;" @click="clear"> ยกเลิก </v-btn>
             </v-col>
            </v-row>
            
            
           
  </v-container>
 </v-col>
</v-row>
</template>


<script>
import http from "../http-common";

export default {
  name: "disease",
  data() {
    return {
        disease: {
        typeID: "",
        lifespanID: "",
        personnelID: "",
        name: "", 
        symptom:"",  
        
      },
         types:[],
         lifespans:[],
         personnels:[],
         
    };
  },
  methods: {
    viewdisease(){
      this.$router.push("/viewdis");
    },
 
   getTypes() {
      http
        .get("/type")
        .then(response => {
          this.types = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getLifespans() {
      http
        .get("/lifespan")
        .then(response => {
          this.lifespans = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
   
    getPersonnels() {
      http
        .get("/personnel")
        .then(response => {
          this.personnels = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    saveDisease() {
      http
        .post(
          "/disease/" +
          this.disease.name +
          "/"+
          this.disease.symptom +
          "/"+
           this.disease.typeID +
            "/" +
            this.disease.lifespanID +
            "/" +
            this.disease.personnelID ,
          this.disease
        ).then(response => {
          console.log(response);
            this.disease.name="",
            this.disease.symptom="",
            this.disease.typeID="",
            this.disease.lifespanID="",
            this.disease.personnelID="",
            this.$router.push('/Suc');
        })
       .catch(e => {
          console.log(e);
        });
      this.submitted = true;
    },
    clear() {
      this.disease.name="",
      this.disease.symptom="",
      this.disease.typeID="",
      this.disease.lifespanID="",
      this.disease.personnelID=""
    
    },
   
  },
  mounted() {
    this.getTypes();
    this.getLifespans();
    this.getPersonnels();
  }
};
</script> 
