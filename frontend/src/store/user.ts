import { defineStore } from 'pinia'

export const userInfoStore = defineStore('userInfo', {
    state: () => ({
        isLogin: false,
        userInfo: {
            userId: '',
            roles: ['']
        }
    }),
    getters: {
        isLoggedIn: (state) => {
            return state.isLogin;
        },
    },
    actions: {
        setIsLogin(isLogin: boolean) {
            this.isLogin = isLogin
        },
        setUserInfo(userId: string, roles: string[]) {
            this.userInfo.userId = userId
            this.userInfo.roles = roles
        },
        cleanLogin() {
            this.isLogin = false;
        }
    }
})
