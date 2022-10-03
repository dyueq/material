import Vue from 'vue'
import VueRouter from 'vue-router'
import Main from '../views/Main.vue'
import Login from '../views/Login.vue'
import CommonMain from '../components/CommonMain'


const originPush = VueRouter.prototype.push

VueRouter.prototype.push = function push(location) {
  return originPush.call(this, location).catch(err => err)
}


Vue.use(VueRouter)

const routes = [
  {
    path: '/main',
    name: 'main',
    component: Main,
    children: [
      {
        path: "/home",
        name: "home",
        component: CommonMain
      },
      {
        path: "/manager",
        name: "manager",
        component: () => import('@/views/user/Manager'),
      },
      {
        path: "/record",
        name: "record",
        component: () => import('@/views/user/Record'),
      },
      {
        path: "/news",
        name: "news",
        component: () => import('@/views/news/News')
      },
      {
        path: "/material",
        name: "material",
        component: () => import('@/views/material/Material')
      },
      {
        path: "/in",
        name: "in",
        component: () => import('@/views/material/RecordIn')
      },
      {
        path: "/out",
        name: "out",
        component: () => import('@/views/material/RecordOut')
      },
      {
        path: "/admin/user",
        name: "userManage",
        component: () => import('@/views/admin/UserManage')
      },
      {
        path: "/admin/order",
        name: "order",
        component: () => import('@/views/admin/OrderManage')
      },
      {
        path: "/admin/dispatch",
        name: "dispatch",
        component: () => import('@/views/admin/MaterialDispatch')
      },
      {
        path: "/admin/news",
        name: "newsManage",
        component: () => import('@/views/admin/NewsManage')
      },
    ]
  },
  {
    path: "/",
    name: "login",
    component: Login
  },
  {
    path: "/regist",
    name: 'regist',
    component: () => import('@/views/Regist')
  }

]

const router = new VueRouter({
  routes
})

export default router
