<template>
  <v-container>
  <v-row> </v-row>
  <v-card max-width="1600" class="mx-auto">

    <v-layout text-center wrap>
      <v-flex mb-4>
        <br />
        <h1 class="display-2 font-weight-bold mb-3">ระบบสั่งอาหารให้ผู้ป่าวย</h1>
      </v-flex>
    </v-layout>
    </v-card>
    <v-card max-width="1600" class="mx-auto">
    <v-row justify="center">
      <v-col cols="4">
        <v-form v-model="valid" ref="form">
            <v-row>

              <v-col cols="10">
                <v-select
                  label="เลือกอาหาร"
                  outlined
                  v-model="orderFood.foodId"
                  :items="food"
                  item-text="name"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required

                ><v-icon slot="prepend" color="black" >mdi-food</v-icon></v-text-field>
                </v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="10">
                <v-select
                  label="เลือกผู้ป่วย"
                  outlined
                  v-model="orderFood.bedId"
                  :items="patient"
                  item-text="addname"
                  item-value="id"
                  :rules="[(v) => !!v || 'Item is required']"
                  required
                ><v-icon slot="prepend" color="black" >mdi-folder-account</v-icon></v-text-field>
                </v-select>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="10">
                 <v-select
                    label="เลือกพนักงาน"
                       outlined
                       v-model="orderFood.emId"
                       :items="employee"
                       item-text="firstname"
                       item-value="id"
                       :rules="[(v) => !!v || 'Item is required']"
                       required
                    ><v-icon slot="prepend" color="black" >mdi-account-box</v-icon></v-text-field>
                    </v-select>
                 </v-col>
              </v-row>

             <v-row >
                <v-textarea
                      name="orderFood.details"
                      v-model="orderFood.details"
                      filled
                      label="รายละเอียดเพิ่มเติม"
                      auto-grow
                    ></v-textarea>
            </v-row>
            <v-row justify="center">
                <v-col cols="12">
                    <v-btn @click="saveOrder" :class="{ red: !valid, green: valid }">save</v-btn>
                    <v-btn style="margin-left: 15px;" @click="clear">clear</v-btn>
                </v-col>
             </v-row>
            <br />
        </v-form>
      </v-col>
    </v-row>
    </v-card>
  </v-container>
</template>

<script>
import http from "../http-common";

export default {
  name: "movieTable",
  data() {
    return {
    picker: new Date().toISOString().substr(0, 10),
      orderFood: {
        foodId: "",
        emId: "",
        bedId: "",
        details:""
      },
      food:[],
      patient:[],
      employee:[]

    };

  },
  methods: {
    /* eslint-disable no-console */

    // ดึงข้อมูล Movies ใส่ combobox
    getFood() {
      http
        .get("/food")
        .then(response => {
          this.food = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getPatient() {
      http
        .get("/register")
        .then(response => {
          this.patient = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getEmployee() {
          http
            .get("/personnel")
            .then(response => {
              this.employee = response.data;
              console.log(response.data);
            })
            .catch(e => {
              console.log(e);
            });
        },
    // function เมื่อกดปุ่ม submit
    saveOrder() {
      http
        .post(
          "/foodorder/"+
            this.orderFood.foodId +
            "/" +
            this.orderFood.bedId +
            "/" +
            this.orderFood.emId +
             "/" +
            this.orderFood.details
        )
        .then(response => {
          console.log(response);
          this.clear();
        })
        .catch(e => {
          console.log(e);
        });

       if(this.datePicker!="null"){
      this.submitted = true;
       }
    },
    clear() {
      this.$refs.form.reset();
    },
    refreshList() {
      this.getMovies();
      this.getTheatre();
      this.getTimes();
      this.getEmployee();
    }

    /* eslint-enable no-console */
  },
  created() {
      this.getFood();
      this.getPatient();
      this.getEmployee();
    }
};
</script>
