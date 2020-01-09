<template >
  <v-container >
    <v-card
      max-width="900"
      class="mx-auto"
      >
    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ระบบลงทะเบียนผู้ป่วย</h1>
        <hr/>
      </v-flex>
    </v-layout>
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form" >
          <div>
            <v-row>
              <v-col cols="15">
                <v-select
                  background-color="blue lighten-5"
                  label="ชื่อพนักงาน"
                  outlined
                  v-model="register.personnelID"
                  :items="personnels"
                  item-text="firstname"
                  item-value="id"
                  required
                ><v-icon slot="prepend" color="blue">mdi-hospital-building</v-icon></v-select>
              </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" sm="6">
                <v-text-field
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
              <v-col cols="12" sm="10">
                <v-text-field
                    v-model="register.age"
                    :items="age"
                      item-text="addage"
                      item-value="addage"
                      label="อายุ"
                      shaped
          ><v-icon slot="prepend" color="blue">mdi-spellcheck</v-icon></v-text-field>
        </v-col>
              
            </v-row>
      <v-row>
              <v-col cols="12" sm="10">
                <v-text-field
                    v-model="register.telephone"
                    :items="telephone"
                      item-text="telephone"
                      item-value="telephone"
                      label="เบอร์โทรศัพท์"
                      shaped
          ><v-icon slot="prepend" color="blue">mdi-phone-in-talk</v-icon></v-text-field>
        </v-col>
              
            </v-row>
            <v-row>
              <v-col cols="15">
                <v-select
                  label="เพศ"
                  outlined
                  v-model="register.genderID"
                  :items="genders"
                  item-text="gen_name"
                  item-value="id"
                  required
                ><v-icon slot="prepend" color="purple">mdi-gender-transgender</v-icon></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="15">
                <v-select
                  label="หมู่เลือด"
                  outlined
                  v-model="register.bloodtypeID"
                  :items="bloodtypes"
                  item-text="blood_name"
                  item-value="id"
                  required
                ><v-icon slot="prepend" color="red">mdi-water</v-icon></v-select>
              </v-col>
            </v-row>
            <v-row>
              <v-col cols="15">
                <v-select
                  label="โรคที่ผู้ป่วยเป็น"
                  outlined
                  v-model="register.diseaseID"
                  :items="diseases"
                  item-text="name"
                  item-value="id"
                  required
                ><v-icon slot="prepend" color="green">mdi-biohazard</v-icon></v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="15">
                <v-select
                  label="เตียงผู้ป่วย"
                  outlined
                  v-model="register.patientbedID"
                  :items="patientbeds"
                  item-text="show"
                  item-value="patientBed_id"
                  required
                ><v-icon slot="prepend" color="black">mdi-hotel</v-icon></v-select>
              </v-col>
            </v-row>
            <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveRegister" :class="{ orange: !valid, green: valid }">submit</v-btn>
                <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                <b-button style="margin-left: 15px;">
                <router-link to="/viewEm"><v-btn color="blue darken-1">Show</v-btn></router-link>
        </b-button>
        <br/>
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
          alert('Successful !!!')
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
