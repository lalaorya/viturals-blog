import Vue from 'vue'
import Vuex from 'vuex'

import mutations from 'store/mutations'
import actions from 'store/actions'
import getters from 'store/getters'

Vue.use(Vuex)

// const state = {
//   pv: '',
//   uv: '',
//   sideBar:true,
// }



// export default new Vuex.Store({
//   state, // 根级别的state
//   getters, // 根级别的getters
//   mutations, // 根级别的mutations
//   actions, // 根级别的actions
//   modules: {
//   }
// })

export default new Vuex.Store({
  state: {
    status: false,
  },
  // 相当于set
  mutations: {
    // 把后端传过来的token存在localstorage
    SET_STATUS: (status) => {
      state.status = status
    },
    // // 清空本地和localstarage的信息
    // REMOVE_INFO: (state) => {
    //   state.token = {}
    //   state.userInfo = {}
    //   window.localStorage.clear()
    // },
    
    
  },
  getters: {
    getStatus: state => {
      return state.status
    }
  },
})
