import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'

const app = createApp(App)
console.log(app);
// 使用axios
app.config.globalProperties.$axios = axios
// 使用router
app.use(router)
app.use(ElementPlus)
app.mount('#app')