import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementUI from 'element-ui';
// import {Button, Dropdown, DropdownItem, DropdownMenu, Form, FormItem, InfiniteScroll, Input} from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import dataV from '@jiaminghi/data-view'
// 引入vue-awesome
import Icon from 'vue-awesome/components/Icon'
import 'vue-awesome/icons/index.js'
// 引入全局css
import './assets/scss/style.scss'
// import * as echarts from 'echarts'
import JsonViewer from 'vue-json-viewer'


// Vue.use(Dropdown);
// Vue.use(DropdownMenu);
// Vue.use(DropdownItem);
// Vue.use(Input);
// Vue.use(Form);
// Vue.use(FormItem);
// Vue.use(Button);
// Vue.use(InfiniteScroll);

Vue.use(JsonViewer)


Vue.use(ElementUI)
Vue.use(dataV)
// Vue.prototype.$echarts = echarts
// 全局注册图标
Vue.component('icon', Icon)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')