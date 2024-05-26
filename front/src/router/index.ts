import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import Main from '../views/Main.vue';
import RecordQuestionBank from "../views/RecordQuestionBank.vue";
import { userInfoStore } from '../store/user.ts'
const routes = [
    {
        path: '/',
        redirect: '/login',
        children: [
            {
                path: '/Main',
                name: 'Main',
                component: Main
            },
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
                meta: { requiresAuth0: true, roles: ['admin', 'user'] }
            },
            {
                path: '/page',
                name: 'Page',
                component: () => import('@/views/Page.vue'),
                meta: { requiresAuth: true, roles: ['admin', 'user'] }
            },
            {
                path: '/exam',
                name: 'Exam',
                component: () => import("@/views/Exam.vue"),
                meta: { requiresAuth: true, roles: ['admin', 'user'] }
            },
            {
                path: '/login',
                name: 'login',
                component: () => import('@/views/Signin.vue'),

            },
            {
                path: '/exam',
                name: 'Exam',
                component: () => import('@/views/Exam.vue'),
                meta: { requiresAuth: true, roles: ['admin', 'user'] }
            },
            {
                path: '/grade',
                name: 'Grade',
                component: () => import('@/views/Grade.vue'),
            },
            {
                path: '/displayPage',
                name: 'displayPage',
                component: () => import('@/views/displayPage.vue'),
            },
            {
                path: '/startExam',
                name: 'startExam',
                component: () => import('@/views/startExam.vue'),
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

//-前置守卫路由:登录校验
router.beforeEach((to, from, next) => {
    const store = userInfoStore()
    //-：获取是否登录的状态
    let isLogin = store.isLogin
    //-:访问的请求不是 login，不是reg 也没有登录
    if (to.name !== 'login' && !isLogin) {
        next({ name: 'login' })
    } else if (to.name == 'login' && isLogin) {//-:已经登录了，还在访问登录请求
        next({ name: 'Main' })
    } else {//否则，该干啥干啥
        next()
    }
})

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
