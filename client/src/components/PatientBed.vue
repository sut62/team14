<template >
  <v-card
    max-width="1000"
    class="mx-auto"
  >
<v-col cols="30">
      <v-row justify="center">
        <v-container   >
           <v-col cols="20" >
             <!-- ใส่สีก็ใส่ข้างหน้าจะเป็นสีพื้นหลัง class="cyan lighten-1 mx-auto my-12" -->
              <v-layout text-center wrap >
                <v-flex mb-0.1>
                  <label
                    
                    class="font-weight-black  pink--text  text-uppercas text-uppercas"
                    style="font-size:4.5em  "
                  >Add Patient Bed</label>
                </v-flex>
              </v-layout>
             </v-col>


            <!-- Personnel Combobox-->
            <v-row justify="center"  >
              
              <v-col cols="6"  >
                <label
                    class="red--text  font-size-10 "
                    style="font-size:0.7em  "
                 >     **
                </label>
                 <label
                    class="red--text  font-size-10 "
                    style="font-size:0.8em  "
                 >     จำเป็น
                </label>
                <label
                    class="black--text  font-size-10 "
                    style="font-size:1.0em  "
                >     
                </label>
               
                <v-select 
                 background-color=""
                  solo
                  label="Personnel"
                  outlined
                  v-model="patientBed.personnelId"
                  :items="personnels"
                  item-text="fullname"
                  item-value="id"
                  color="blue" 
                    menu-props="auto"
                  :rules="[]"
                  required
                ><v-icon slot="prepend" color="blue">mdi-account-multiple</v-icon></v-select>
              
              </v-col>
            </v-row>

  


            <!-- PatientZone Combobox-->
            <v-row justify="center">
              <v-col cols="6">    
                <label
                    class="red--text  font-size-10 "
                    style="font-size:0.7em  "
                 >     **
                </label>
                 <label
                    class="red--text  font-size-10 "
                    style="font-size:0.8em  "
                 >     จำเป็น
                </label>
                <v-select
                background-color=""
                 solo
                  label="BedZone"
                  outlined
                  v-model="patientBed.patientZoneId"
                  :items="patientZones"
                  item-text="zone_name"
                  item-value="zone_id"
                  color="blue" 
                  :rules="[]"
                  required
                ><v-icon slot="prepend" color="black">mdi-hotel</v-icon></v-select>
              </v-col>
            </v-row>



            <!-- PatientRoom Combobox-->
            <v-row justify="center">
              <v-col cols="6" >
                  <label
                    class="red--text  font-size-10 "
                    style="font-size:0.7em  "
                 >     **
                </label>
                 <label
                    class="red--text  font-size-10 "
                    style="font-size:0.8em  "
                 >     จำเป็น
                </label>
                <v-select
                background-color=""
                 solo
                  label="Room"
                  outlined
                  v-model="patientBed.patientRoomId"
                  :items="patientRooms"
                  item-text="room_name"
                  item-value="room_id"
                  color="blue" 
                  :rules="[]"
                  required
                ><v-icon slot="prepend" color="green">mdi-magnify-plus</v-icon></v-select>
              </v-col>
            </v-row>



            <!-- Physical Combobox-->   
            <v-row justify="center" >
              <v-col cols="6">
                   <label
                    class="red--text  font-size-10 "
                    style="font-size:0.7em  "
                 >     **
                </label>
                 <label
                    class="red--text  font-size-10 "
                    style="font-size:0.8em  "
                 >     จำเป็น
                </label>
                <v-select
                background-color=""
                  solo
                  label="Physical"
                  outlined
                  v-model="patientBed.physicalBedId"
                  :items="physicalBeds"
                  item-text="physBed_name"
                  item-value="physBed_id"
                  color="blue" 
                  :rules="[]"
                  required
                ><v-icon slot="prepend" color="orange">mdi-server-plus</v-icon>
                </v-select>
              </v-col>
            </v-row>
           
            <!-- Tel Text area -->
            <v-row justify="center">
              <v-col cols="6">               
                    <v-textarea
                      v-model="patientBed.detail"
                      id ="detailBed"
                      outlined
                      name="input-6-4"
                      label="อื่น ๆ"
                      placeholder="รายละเอียดเพิ่มเติม"
                      style="font-size:1.0em  "

                    ></v-textarea>
              </v-col>
            </v-row>

             <v-row justify ="center">
                <div v-if="validData">

                <v-alert dense border="left" type="warning">
                    Please check all information before saving!! 
                </v-alert>

                </div>
             </v-row>


            <!-- Button Save/Cancle-->
              <v-col cols="100"  >
                <div class="text-right">
                        <v-btn    @click="savePatientBed" :class="{ green: !valid, teal : valid }" class="white--text  " >save</v-btn>
                        <v-btn style="margin-left :10px;" @click="clearAll"  :class="{ grey: !valid, brown   : valid }" class="white--text  " >cancle</v-btn> 
                        <b-button style="margin-left :10px;">
                            <router-link to="/ViewBedData"><v-btn color="pink lighten-3"  class="white--text">View Data</v-btn></router-link>
                        </b-button>
                  </div>
             
              </v-col>        
       </v-container>
     </v-row>    
</v-col>
  </v-card>
</template>

<script>
import http from "../http-common";

export default {
  name: "patientBed",
  data() {
    return {
      patientBed: {
        personnelId: "",
        patientZoneId: "",
        patientRoomId: "",
        physicalBedId: "",
        detail: ""
      },
      personnels:[],
      patientZones:[],
      patientRooms:[],
      physicalBeds:[],
      validData: false,
       loading: false,
      selection: 1,
     valid: false,

    };
  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล personnel ใส่ combobox
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
    // ดึงข้อมูล patientZone ใส่ combobox
    getPatientZones() {
      http
        .get("/patientZone")
        .then(response => {
          this.patientZones= response.data;
       
        })
        .catch(e => {
          console.log(e);
        });
    },
    // ดึงข้อมูล patientRoom ใส่ combobox
    getPatientRooms() {
      http
        .get("/patientRoom")
        .then(response => {
          this.patientRooms= response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
      // ดึงข้อมูล physicalBed ใส่ combobox
    getPhysicalBeds() {
      http
        .get("/physicalBed")
        .then(response => {
          this.physicalBeds = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
            this.submitted = true;
    },

    // function เมื่อกดปุ่ม submit
    savePatientBed() {
      http
        .post(
          "/patientBed/" +this.patientBed.personnelId +
          "/" +
          this.patientBed.patientZoneId + 
          "/" +
          this.patientBed.patientRoomId +
         "/"+ 
            this.patientBed.physicalBedId+
            "/" + 
            this.patientBed.detail
        )
       
         .then(response => {
          console.log(response);
          this.patientBed.personnelId = "";
          this.patientBed.patientZoneId = "";
          this.patientBed.patientRoomId = "";
          this.patientBed.physicalBedId = "";
          this.patientBed.detail = "";
          this.$router.push('/bedsuccess');
        })
        .catch(e => {
          console.log(e);
          this.validData=true;
          this.clear();
        });
       
     
    },
    clear() {
  
        this.patientBed.personnelId = "";
        this.patientBed.patientZoneId = "";
        this.patientBed.patientRoomId = "";
        this.patientBed.physicalBedId = "";
        this.patientBed.detail = "";
    },
      clearAll() {
  
        this.patientBed.personnelId = "";
        this.patientBed.patientZoneId = "";
        this.patientBed.patientRoomId = "";
        this.patientBed.physicalBedId = "";
        this.patientBed.detail = "";
        this.validData=false;
    }
    /* eslint-enable no-console */
   
  },
  mounted() {
    this.getPersonnels();
    this.getPatientZones();
    this.getPatientRooms();
    this.getPhysicalBeds();
  }
};
</script>
