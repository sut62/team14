import Vue from 'vue'
import Router from 'vue-router';
import Personnel from '../components/Personnel';
import order from '../components/FoodOrder';
import disease from '../components/newDisease';

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
       }
        
    ]
});