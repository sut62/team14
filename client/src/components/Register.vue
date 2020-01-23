<template>
<v-container>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-card class="elevation-12">
              <v-toolbar color="teal accent-3" light flat>
                <v-icon dark>mdi-hospital</v-icon>&nbsp;&nbsp;

                <span class="white--text">
                  <v-toolbar-title>ระบบลงทะเบียนผู้ป่วย</v-toolbar-title>
                </span>
                <div class="flex-grow-1"></div>
              </v-toolbar>
              <v-form v-model="valid" ref="form" >
              <v-card-text>
                <v-select
                  label="ชื่อพนักงาน"
                  outlined
                  v-model="register.personnelID"
                  :items="personnels"
                  item-text="fullname"
                  item-value="id"
                ></v-select>
              <v-row>
                <v-col cols="12" sm="6">
                <v-text-field
                    id ="5"
                    v-model="register.userName"
                    :items="userName"
                      item-text="addname"
                      item-value="addname"
                      label="ชื่อ"
                      shaped
                ><v-icon slot="prepend" color="blue">mdi-account-multiple-plus</v-icon></v-text-field>
        </v-col>
                <v-col cols="12" sm="6">
                <v-text-field
                    id ="6"
                    v-model="register.userLName"
                    :items="userLName"
                      item-text="addlastname"
                      item-value="addlastname"
                      label="นามสกุล"
                      shaped
                ></v-text-field>
        </v-col>
      </v-row>
                <v-row>    
                <v-text-field
                    id ="7"
                    v-model="register.age"
                    :items="age"
                      item-text="addage"
                      item-value="addage"
                      label="อายุ"
                      shaped
          ><v-icon slot="prepend" color="blue">mdi-spellcheck</v-icon></v-text-field>
              
            </v-row>
      <v-row>
                <v-text-field
                    id ="8"
                    v-model="register.telephone"
                    :items="telephone"
                      item-text="telephone"
                      item-value="telephone"
                      label="เบอร์โทรศัพท์"
                      shaped
          ><v-icon slot="prepend" color="blue">mdi-phone-in-talk</v-icon></v-text-field>
            </v-row>
            <v-row>
                <v-select
                  label="เพศ"
                  v-model="register.genderID"
                  :items="genders"
                  item-text="gen_name"
                  item-value="id"
                ><v-icon slot="prepend" color="purple">mdi-gender-transgender</v-icon></v-select>
            </v-row>
            <v-row>
                <v-select
                  label="หมู่เลือด"
                  v-model="register.bloodtypeID"
                  :items="bloodtypes"
                  item-text="blood_name"
                  item-value="id"
                ><v-icon slot="prepend" color="red">mdi-water</v-icon></v-select>
            </v-row>
            <v-row>
                <v-select
                  label="โรคที่ผู้ป่วยเป็น"
                  v-model="register.diseaseID"
                  :items="diseases"
                  item-text="name"
                  item-value="id"
                ><v-icon slot="prepend" color="green">mdi-biohazard</v-icon></v-select>
            </v-row>

            <v-row>
                <v-select
                  label="เตียงผู้ป่วย"
                  v-model="register.patientbedID"
                  :items="patientbeds"
                  item-text="show"
                  item-value="patientBed_id"
                ><v-icon slot="prepend" color="black">mdi-hotel</v-icon></v-select>
            </v-row>
                <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveRegister" class="mr-3" color="orange">บันทึก</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                <b-button style="margin-left: 15px;">
        </b-button>
        <br/>
              </v-col>
            </v-row>
            <br />
              </v-card-text>
              </v-form>
            </v-card>
            
          </v-col>
        </v-row>
        
        </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "register",
  data() {
    return {
     register: {
        userName: "",
        userLName: "",
        age:"",
        telephone: "",
        genderID: "",
        bloodtypeID: "",
        personnelID:"",
        diseaseID:"",
        patientbedID:""
      },
      userName:[],
      userLName:[],
      age:[],
      telephone:[],
      genders:[],
      bloodtypes:[],
      personnels:[],
      diseases:[],
      patientbeds:[],

      valid: false
    };
  },
  methods: {
    /* eslint-disable no-console */
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
    getGenders() {
      http
        .get("/gender")
        .then(response => {
          this.genders = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getBloodtypes() {
      http
        .get("/bloodtype")
        .then(response => {
          this.bloodtypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getDiseases() {
      http
        .get("/disease")
        .then(response => {
          this. diseases = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    getPatientBeds() {
      http
        .get("/patientBed")
        .then(response => {
          this. patientbeds = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    saveRegister() {
      http
        .post(
          "/register"+
           this.register.personnelID +
            "/" +
            this.register.userName +
            "/" +
            this.register.userLName +
            "/" +
            this.register.age +
            "/" +
             this.register.telephone +
            "/" +
            this.register.bloodtypeID +
            "/" +
            this.register.genderID +
            "/" +
            this.register.diseaseID +
            "/" +
            this.register.patientbedID,
          this.register
        )
        .then(response => {
          console.log(response);
          this.register.personnelID = "";
          this.register.userName = "";
          this.register.userLName = "";
          this.register.age = "";
          this.register.telephone = "";
          this.register.bloodtypeID = "";
          this.register.genderID = "";
          this.register.diseaseID = "";
          this.register.patientbedID = "";
        })
        .then(response => {
          console.log(response);
          this.$refs.form.reset();
          this.$router.push('/Suc');
        })
        .catch(e => {
          console.log(e);
          alert('Unsuccessful !!!')
        });
      
    },
    clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getGenders();
      this.getBloodtypes();
      this.getPersonnels();
      this.getDiseases();
      this.getPatientBeds();


    }
  },
  mounted() {
      this.getGenders();
      this.getBloodtypes();
      this.getPersonnels();
      this.getDiseases();
      this.getPatientBeds();

      

  }
};
</script>
