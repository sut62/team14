import Vue from 'vue'
import Router from 'vue-router';
import Personnel from '../components/Personnel';
import order from '../components/FoodOrder';
import Food from '../components/Food';
import PatientBed from '../components/PatientBed';
import logdis from '../components/LoginDisease';
import dis from '../components/newDisease';
import Register from '../components/Register';
import viewRegister from '../components/viewRegister';
import Loginregis from '../components/LoginMedical records';
import Home from '../components/Home';
import Loginnutr from '../components/LoginNutritionost';
import nutr from '../components/Nutritionost';
import nurselogin from '../components/NurseLogin';
import nurse from '../components/Nurse';
import LoginAdmin from '../components/LoginAdmin';
import ViewPersonnel from '../components/ViewPersonnel';
import Suc from '../components/Succcsess';
import BedSuccess from '../components/BedSuccess';
import ViewBedData from '../components/ViewBedData';
import viewOrder from '../components/ViewOrderData';
Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/per',
            component: Personnel
        },

        {
              path: '/order',
              component: order
        },
        {
            path: '/dis',
            component: dis
       },
       {
            path: '/logdis',
            component: logdis
        },
       {
            path: '/Food',
            component: Food
        },
        {
            path: '/Regis',
            component: Register
        },
        {
            path: '/bed',
            component: PatientBed

        },{
            path: '/',
            component: Home
        }
        ,{
            path: '/loginnutr',
            component: Loginnutr
        }
        ,{
            path: '/nutr',
            component: nutr
        },{
            path: '/nurselogin',
            component: nurselogin
        },{
            path: '/nurse',
            component: nurse
        }
        ,{
            path: '/loginregis',
            component: Loginregis
        }
        ,{
            path: '/Admin',
            component: LoginAdmin
        }
        ,{
            path: '/Suc',
            component: Suc
        }
        ,{
            path: '/viewregis',
            component: viewRegister
        }
        ,{
            path: '/viewpersonnel',
            component: ViewPersonnel
        },{
            path: '/bedsuccess',
            component: BedSuccess
        },{
            path: '/ViewBedData',
            component: ViewBedData
        },
        {
            path: '/vieworder',
            component: viewOrder
       },

    ]
});