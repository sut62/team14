import Vue from 'vue'
import Router from 'vue-router';
import Personnel from '../components/Personnel';
import order from '../components/FoodOrder';
import Food from '../components/Food';
import PatientBed from '../components/PatientBed';
import logdis from '../components/LoginDisease';
import dis from '../components/newDisease';
import Register from '../components/Register';
import Loginregis from '../components/LoginMedical records';
import Home from '../components/Home';
import Loginnutr from '../components/LoginNutritionost';
import nutr from '../components/Nutritionost';
import nurselogin from '../components/NurseLogin';
import nurse from '../components/Nurse';

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
    ]
});