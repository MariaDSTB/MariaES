import { defineStore } from 'pinia'

export const userInfoStore = defineStore('userInfo', {
    state: () => ({
        isLogin: false,
        userInfo: {
            userId: 'user',
            roles: ['user'],
        },
    }),
    getters: {
        hasLogin: (state) => {
            return state.isLogin;
        },
    },
    actions: {
        setUserInfo(flag: boolean, userId: string, roles: string[]) {
            this.isLogin = flag;
            this.userInfo = { userId, roles }
        }
    },
})
