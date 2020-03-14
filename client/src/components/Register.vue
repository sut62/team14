<template>
<v-container>
        <v-row align="center" justify="center">
          <v-col cols="12" sm="8" md="6">
            <v-card class="elevation-12">
              <v-toolbar color="teal accent-3" light flat>
                <v-chip class="display-1" color="white" outlined pill large >ระบบลงทะเบียนผู้ป่วย<v-icon right large>mdi-account-plus-outline</v-icon>
    </v-chip>
                <div class="flex-grow-1"></div>
              </v-toolbar>
              <v-form v-model="valid" ref="form" >
              <v-card-text>
                <v-select
                  label="ชื่อพนักงาน"
                  outlined
                  v-model="register.personnelID"
                  :items="personnels"
                  :item-text="personnels => personnels.firstname + ' ' + personnels.lastname"
                  item-value="id"
                ></v-select>
              <v-row>
                <v-col cols="12" sm="6">
                <v-text-field
                    id ="name"
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
                    id ="lastname"
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
                    id ="age"
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
                    id ="telephone"
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
                ><v-icon slot="prepend" color="black">mdi-scale-bathroom</v-icon></v-select>
            </v-row>
            <v-row >
            <v-col cols="12">
            <div v-if="alertFailed">
            <v-alert type="error" dense outlined>กรุณาใส่ข้อมูลให้ครบและถูกต้อง!!</v-alert></div></v-col>
            </v-row>
            <v-row >
            <v-col cols="12">
            <div v-if="alertSuccess">
            <v-alert type="success" dense outlined>บันทึกข้อมูลสำเร็จ</v-alert></div></v-col>
            </v-row>
                <v-row justify="center">
              <v-col cols="12">
                <v-btn @click="saveRegister" class="mr-3" color="orange">บันทึก<v-icon right>mdi-content-save</v-icon></v-btn>
                <v-btn @click="viewRegister" style="margin-left: 15px;">ดูข้อมูลผู้ป่วย</v-btn>
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
      valid: false,
      alertFailed: false,
      alertSuccess : false
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
        if (
        !this.register.personnelID ||
        !this.register.userName ||
        !this.register.age ||
        !this.register.telephone||
        !this.register.bloodtypeID||
        !this.register.genderID||
        !this.register.diseaseID||
        !this.register.patientbedID
      ) {
        this.clearAlert();
        this.alertFailed = true;
        this.alertSuccess = false;
        this.clear();
      } else {
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
                  this.alertSuccess = true;
                  this.alertFailed = false;
                  this.clear();
                  })
              .catch(e => {
                  console.log(e);
                  this.alertFailed = true;
                  this.alertSuccess = false;
                   });
              }

    },
    viewRegister(){
      this.$router.push('/viewregis');
    },
    clearAlert() {
      this.$refs.form.reset();
      this.alertFailed = false;
      this.alertSuccess = false;
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