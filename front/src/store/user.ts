import { defineStore } from 'pinia'

export const userInfoStore = defineStore('userInfo', {
    state: () => ({
        //-登录状态
        isLogin: false,
        //-登录用户信息
        userInfo: {
            userName: null,
            userPhone: null
        }
    })
})
