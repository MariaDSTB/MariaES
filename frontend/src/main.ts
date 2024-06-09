import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import "./assets/main.css"
import { createPinia } from 'pinia'

const app = createApp(App)
app.use(createPinia())
// 使用axios
app.config.globalProperties.$axios = axios
// 使用router
app.use(router)
app.use(ElementPlus)
app.mount('#app')