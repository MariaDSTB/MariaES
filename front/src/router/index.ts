import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import Main from '../views/Main.vue';
import RecordQuestionBank from "../views/RecordQuestionBank.vue";
import { userInfoStore } from '../store/user.ts'
const routes = [
    {
        path: '/',
        redirect: '/login',

    },
    {
        path: '/login',
        name: 'login',
        component: () => import('@/views/Signin.vue'),
    },
    {
        path: '/Main',
        component: Main,
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

// -前置守卫路由:登录校验
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
// -后置守卫路由：权限校验
// router.afterEach((to, from) => {
//     const store = userInfoStore()
//     let isLogin = store.isLogin
//     let roles = store.userInfo.roles
//     if (to.meta.requiresAuth && !isLogin) {
//         next({ name: 'login' })
//     } else if (to.meta.roles && to.meta.roles.length && !to.meta.roles.some(role => roles.includes(role))) {
//         next({ name: 'Main' })
//     } else {
//         next()
//     }
// })
export default router;
