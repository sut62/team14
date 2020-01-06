import Vue from 'vue'
import Router from 'vue-router';
import Personnel from '../components/Personnel';
import order from '../components/FoodOrder';
<<<<<<< HEAD
import Food from '../components/Food';

=======
import disease from '../components/newDisease';
>>>>>>> 5d95ec3da72da31b81ce9da4babd78437c810aa7

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
<<<<<<< HEAD
        },{
=======
        },
        {
            path: '/disease',
            component: disease
       }
>>>>>>> 5d95ec3da72da31b81ce9da4babd78437c810aa7
        
            path: '/Food',
            component: Food
        },
    ]
});