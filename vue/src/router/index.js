import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import ProfileSettings from '../views/ProfileSettings.vue'
import EditSettings from '../views/EditSettings.vue'
import Bolus from '../views/Bolus.vue'
import store from '../store/index'
import Report from '../views/Report.vue'
import ActivityHistory from '../views/ActivityHistory.vue'
import AdminReport from '../views/AdminReport.vue'
import FoodGenerator from '../views/FoodGenerator.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/profile_settings',
      name: "profile_settings",
      component: ProfileSettings,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/:id/profile_settings",
      name: "edit_settings",
      component: EditSettings,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/bolus",
      name: "bolus",
      component: Bolus,
      meta: {
        requiresAuth: true
      }
    },

      {
        path: "/:id/report",
        name: "report",
        component: Report,
        meta: {
          requiresAuth: true
        }
    },

    {
        path: "/:id/activity",
        name: "activity",
        component: ActivityHistory,
        meta: {
          requiresAuth: true
        }

    },
    {
        path: "/activity",
        name: "admin-report",
        component: AdminReport,
        meta: {
          requiresAuth: true
        }

    },
    {
      path: "/:carbs/food",
      name: "food",
      component: FoodGenerator,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
