import Vue from 'vue'
import Router from 'vue-router';
import Personnel from '../components/Personnel';
import order from '../components/FoodOrder';
import Food from '../components/Food';
import PatientBed from '../components/PatientBed';
import disease from '../components/newDisease';
import Register from '../components/Register';
import Longinregis from '../components/LonginMedical records';
import Home from '../components/Home';
import Loginnutr from '../components/LoginNutritionost';
import nutr from '../components/Nutritionost';

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
            path: '/disease',
            component: disease
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
            path: '/home',
            component: Home
        }
        ,{
            path: '/loginnutr',
            component: Loginnutr
        }
        ,{
            path: '/nutr',
            component: nutr
        }
        ,{
            path: '/longinregis',
            component: Longinregis
        }
    ]
});