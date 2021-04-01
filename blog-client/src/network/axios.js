import axios from 'axios'
import router from '@/router'
axios.defaults.baseURL = 'http://localhost:8050'
// 点击标签时，取消之前正在执行的请求，使得切换标签时，页面得到的是最后请求的结果，而不是响应最慢的结果。
let CancelToken = axios.CancelToken
// 请求拦截器
axios.interceptors.request.use(config => {
    console.log("前置拦截")
    // // 可以统一设置请求头
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
        // 直接拒绝往下面返回结果信息
        return Promise.reject(response.data.msg)
    }
},
    error => {             
        return Promise.reject(error)
    })
export default axios