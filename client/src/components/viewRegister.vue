<template>
  <v-container>
    <v-row justify="center">
      <v-col cols="12">
          <v-toolbar color="deep-orange accent-2" light flat>
                <v-chip class="display-1" color="white" outlined pill large >ข้อมูลการลงทะเบียนผู้ป่วย<v-icon right large>mdi-file-document-outline</v-icon>
    </v-chip>
    <v-spacer></v-spacer><v-spacer></v-spacer><v-spacer></v-spacer><v-spacer></v-spacer>
     <v-btn @click="Register" class="mr-3" color="white">ลงทะเบียนผู้ป่วย<v-icon right>mdi-content-save</v-icon></v-btn>
                <div class="flex-grow-1"></div>
              </v-toolbar>
        <v-data-table  :headers="headers" :items="items" :items-per-page="10" color="green" class="elevation-6">
        </v-data-table>
        <br>
        <br/>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import http from "../http-common";
export default {
  name: "RegisterData",
  data() {
    return {
      headers: [
        {
          text: "รหัสการลงทะเบียน",
          align: "left",
          sortable: false,
          value: "id"
        },
        { text: "รหัสพนักงาน" , value: "createdby.id"},
        //{ text: "ชื่อพนักงาน" ,align: "right", value: "createdby.firstname"},
        //{ sortable: false,align: "left", value: "createdby.lastname"},
        { text: "ชื่อผู้ป่วย", value: "addname"},
        { text: "นามสกุลผู้ป่วย", value: "addlastname"},
        { text: "อายุ", value: "addage"},
        { text: "เบอร์โทรศัพท์", value: "telephone"},
        { text: "เพศ", value: "gendername.gen_name" },
        { text: "หมู่เลือด", value: "bloodtypename.blood_name" },
        { text: "โรคของผู้ป่วย", value: "desname.name" },
        { text: "เตียงผู้ป่วย", value: "patname.show" }
      ],
      items: [{

      }],
    };
  },
  methods: {
    /* eslint-disable no-console */
    getRegister() {
      http
        .get("/register")
        .then(response => {
          this.items = response.data;

          console.log(this.items);
        })
        .catch(e => {
          console.log(e);
        });
    },
    Register(){
      this.$router.push("/regis");
    },
    refreshList() {
      this.getRegister();
    }
    /* eslint-disable no-console */
  },
  mounted() {
    this.getRegister();
  }
  
};
</script>