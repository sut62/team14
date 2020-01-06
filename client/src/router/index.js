import Vue from 'vue'
import Router from 'vue-router';
import Personnel from '../components/Personnel';
<<<<<<< HEAD
import order from '../components/FoodOrder';
=======
import Food from '../components/Food';

>>>>>>> close #32 add UI for food system

Vue.use(Router);

export default new Router({
    mode: 'history',
    base: process.env.BASE_URL,
    routes: [{
            path: '/per',
            component: Personnel
        },
        {
<<<<<<< HEAD
             path: '/order',
             component: order
        }
        
=======
            path: '/Food',
            component: Food
        },
>>>>>>> close #32 add UI for food system
    ]
});