import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import Main from '../views/Main.vue';
import RecordQuestionBank from "../views/RecordQuestionBank.vue";
const routes = [
    {
        path: '/',
        name: 'Main',
        component: Main,
        redirect:"/HomePage",
        children: [
            {
                path: '/HomePage',
                name: 'HomePage',
                component: HomePage,
                meta: { requiresAuth: true, roles: ['admin', 'user'] }
            },
            {
                path: '/RecordQuestionBank',
                name: 'RecordQuestionBank',
                component: RecordQuestionBank,
                meta: { requiresAuth: true, roles: ['admin', 'user'] }
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

// 添加全局前置导航守卫
// router.beforeEach((to, from, next) => {
//     // 假设您有一个全局方法来检查用户是否已登录和角色
//     const isAuthenticated = true; // 这里应该是调用一个检查登录状态的方法
//     const userRole = 'user'; // 这里应该是从状态管理（如Vuex）中获取用户角色
//
//     if (to.meta.requiresAuth && !isAuthenticated) {
//         // 如果用户未登录，重定向到登录页面
//         return next('/login');
//     }
//
//     if (to.meta.roles && !to.meta.roles.includes(userRole)) {
//         // 如果用户角色不符合要求，重定向到首页或其他页面
//         return next({ name: 'Main' });
//     }
//     // 如果用户已登录且角色符合要求，继续导航
//     next();
// });
export default router;
