import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
      token: '',
      userInfo: JSON.parse(localStorage.getItem("userInfo"))
    },
  // 相当于set
    mutations: {
      // 把后端传过来的token存在localstorage
      SET_TOKEN: (state, token) => {
        state.token = token
        localStorage.setItem("token", token)
      },
      // 把登录人的信息存在localstorage
      SET_USERINFO: (state, userInfo) => {
        state.userInfo = userInfo
        localStorage.setItem("userInfo", JSON.stringify(userInfo))
      },
      // 清空本地和localstarage的信息
      REMOVE_INFO: (state) => {
        state.userInfo = {}
        state.token = {}
        window.localStorage.clear()
      }
    },
//   获取当前用户信息info
//   相当于get
    getters: {
      getUser: state => {
        return state.userInfo
      }
    },
    actions: {},
    modules: {}
  })
  
