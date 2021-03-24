import axios from 'axios'
import router from '@/router'
import Element from "element-ui"
// axios.defaults.baseURL = 'https://api.datealive.top'
axios.defaults.baseURL = 'http://localhost:8050'
// axios.defaults.withCredentials = true
// axios.defaults.crossDomain = true;
import {Message} from "element-ui"
// 点击标签时，取消之前正在执行的请求，使得切换标签时，页面得到的是最后请求的结果，而不是响应最慢的结果。
let CancelToken = axios.CancelToken
// 请求拦截器
axios.interceptors.request.use(config => {
    console.log("前置拦截")
    // 可以统一设置请求头

    const userJson = window.localStorage.getItem('userInfo') || '{}'
    console.log(userJson);
    // const user = JSON.parse(userJson)
    // if (userJson !== '{}' && user.roles !== 'admin' && config.method !== 'get') {
    //     config.cancelToken = new CancelToken(function executor(cancel) {
    //         cancel('演示模式，不允许操作')
    //     })
    //     return config
    // }
    const token=window.localStorage.getItem('token') || '{}'
    if(token !== '{}'){
        config.headers.authorization = token // 请求头带上token
    }
    console.log(config);
    return config
})
axios.interceptors.response.use(response => {
    const res = response.data;
    console.log("后置拦截")
    console.log(res);
    // 当结果的code是否为200的情况
    if (res.code === 200) {
        return response
    } else {
        // 弹窗异常信息
        console.log(res.msg);
        Message({
            message: response.data.msg,
            type: 'error',
            // 显示时间两秒
            duration: 2 * 1000
        })
        // 直接拒绝往下面返回结果信息
        return Promise.reject(response.data.msg)
    }
},
    error => {
       console.log('err==>' + error.response)// for debug
            // if (error.response.status === 400) {
            //     // store.commit('REMOVE_INFO');
            //     router.push({
            //         path: '/login'
            //     });
                // Message({
                //     message: "用户名错误",
                //     type: 'error',
                //     // 显示时间两秒
                //     duration: 2 * 1000
                // })
            //     error.message = '请重新登录';
            // }
            // if (error.response.status === 403) {
            //     error.message = '权限不足，无法访问';
            // }
            // 弹窗异常信息
            Message({
                message: Message.error(error.data),
                type: 'error',
                // 显示时间两秒
                duration: 2 * 1000
            })
             
        return Promise.reject(error)
    })
export default axios