import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import * as echarts from 'echarts'
import SlideVerify from 'vue-monoplasty-slide-verify';
axios.defaults.baseURL = '/api';



Vue.prototype.$axios = axios;
Vue.prototype.$echarts = echarts

Vue.use(ElementUI)
Vue.use(SlideVerify);
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
