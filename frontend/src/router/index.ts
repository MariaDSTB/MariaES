import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue';
import Main from '../views/Main.vue';
import RecordQuestionBank from "../views/RecordQuestionBank.vue";
import { userInfoStore } from '../store/user.ts'
const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        redirect: '/login',

    },
    {
        path: '/login',
        name: 'login',
        component: (): Promise<typeof import("*.vue")> => import('@/views/Signin.vue'),
    },
    {
        path: '/Main',
        name: 'Main',
        component: Main,
        children: [
            {
                path: '/HomePage',
                name: 'HomePage',
                component: HomePage,
            },
            {
                path: '/RecordQuestionBank',
                name: 'RecordQuestionBank',
                component: RecordQuestionBank,
            },
            // {
            //     path: '/page',
            //     name: 'Page',
            //     component: (): Promise<typeof import("*.vue")> => import('@/views/Page.vue'),
            // },
            {
                path: '/exam',
                name: 'Exam',
                component: (): Promise<typeof import("*.vue")> => import("@/views/Exam.vue"),
            },
            {
                path: '/exam',
                name: 'Exam',
                component: (): Promise<typeof import("*.vue")> => import('@/views/Exam.vue'),
            },
            {
                path: '/grade',
                name: 'Grade',
                component: (): Promise<typeof import("*.vue")> => import('@/views/Grade.vue'),
            },
            {
                path: '/displayPage',
                name: 'displayPage',
                component: (): Promise<typeof import("*.vue")> => import('@/views/displayPage.vue'),
            },
            {
                path: '/startExam',
                name: 'startExam',
                component: (): Promise<typeof import("*.vue")> => import('@/views/startExam.vue'),
            }
        ]
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes
});

// -前置守卫路由:登录校验
router.beforeEach((to, _, next) => {
    const store = userInfoStore()
    //-:访问的请求不是 login，不是reg 也没有登录
    if (to.name !== 'login' && store.isLoggedIn) {
        next({ name: 'login' })
    } else if (to.name == 'login' && store.isLoggedIn) {//-:已经登录了，还在访问登录请求
        next({ name: 'Main' })
    } else {//否则，该干啥干啥
        next()
    }
})
export default router;