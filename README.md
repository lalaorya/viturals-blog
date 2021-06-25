# virtualblog个人博客项目

本博客使用`SpringBoot`+`Vue`实现，前后端分离。

前端有两个项目，一个是前台项目，一个是后台管理系统，使用`vue`开发。后端有一个项目，使用`SpringBoot`开发，持久层使用`MyBati-Plus`框架，认证授权使用`Shiro`，缓存使用`Redis`.

前端模板来自 [zcblog](https://github.com/progzc/zcblog)，在此基础上做了部分修改，特此感谢~

目前本站还在开发中，并不是稳定版本，后续会陆续修复~~

本项目已开源,如果对你有帮助,请留下star⭐支持一下

## 相关数据文档：

- 数据库SQL文件：[SQL脚本](https://github.com/sang-Mu/viturals-blog/blob/master/db/blog.sql)
- **博客后端开发文档**：[后端开发文档](https://github.com/sang-Mu/viturals-blog/blob/master/backend-docs/%E4%B8%AA%E4%BA%BA%E5%8D%9A%E5%AE%A2%E5%90%8E%E7%AB%AF%E5%BC%80%E5%8F%91%E6%96%87%E6%A1%A3.md)

## 项目预览

- 前台：https://jsdi.top
- 后台：https://jsdi.top/admin  账号：admin 密码：admin

## 技术栈

- 前端：`Vue`+`elementUI`+`IViewUI`+`MavonEditor`
- 后端：`SpringBoot`+`MyBati-Plus`+`MySQL`+`Redis`+`Shiro`+`JWT`+其他工具

## 博客项目启动步骤

1. 克隆项目到本地
2. 启动本地redis和mysql
3. 安装前端前台client项目相关依赖，使用`npm install`命令
4. 安装前端博客管理manage项目相关依赖，使用`npm install`命令
5. 启动后端springBoot系统
6. 自行修改数据库和redis的配置
7. 在`blog-client`模块下执行命令`npm run serve`
8. 在`blog-manage`模块下执行命令`npm run serve`

## 效果展示

- 前台

  ![image-20210414153514137](https://i.loli.net/2021/04/14/b5D1fehEQvGgs8o.png)

  ----

  <img src="https://i.loli.net/2021/04/14/tip2wXug3MSKNcP.png" alt="image-20210414153247179" style="zoom:120%;" />

  ----

  ![image-20210414203904663](https://i.loli.net/2021/04/14/eqj6iLPtSrJWcVE.png)

  ---

  ![image-20210414153406327](https://i.loli.net/2021/04/14/D4IjYfr1Ls8xPuh.png)

  ---

  ![image-20210414153430066](https://i.loli.net/2021/04/14/G2VXnv1biuAgyFR.png)

- 后台管理系统：

  ![image-20210414202751773](https://i.loli.net/2021/04/14/WKvOgzU2rj8yBJq.png)

  ----

  ![image-20210414202813443](https://i.loli.net/2021/04/14/yXOKqYCJT9DwVQE.png)

  ---

  ![image-20210414202839748](https://i.loli.net/2021/04/14/gh7TMekuJ4a6fVc.png)

  ---

  ![image-20210414202854866](https://i.loli.net/2021/04/14/jpKOnJv15l6fk4C.png)

  ----

  ![image-20210414202910849](https://i.loli.net/2021/04/14/XpDqFfKnZztEdLB.png)

  ---

  ![image-20210414202926520](https://i.loli.net/2021/04/14/iRFLAq9zZcH6sUf.png)

  ---

  ![image-20210414202940449](https://i.loli.net/2021/04/14/RwsZSmNxdEeg16W.png)

  ---

  ![image-20210414203001704](https://i.loli.net/2021/04/14/2iy1rkacFSRHt7d.png)

## TODO

- [ ] 博客分类、动态以及友链页面的开发

- [ ] 博客和后台管理系统搜索功能，使用Elastic Search实现

- [x] 首屏访问时间过慢，待优化
- [ ] 使用`hibernate validator`对后端接口进行参数校验
- [x] 后台架构的优化(有些controller中业务代码太多)
- [x] 升级HTTPS，提高站点安全性

## 最近更新~

*2021.06.10 ~ 2021.06.15*

- 域名配置和前端CDN加速
- 从HTTP升级到了HTTPS
- 前端和后端分别配置Gzip压缩，缩小部分css、js文件的体积，优化首屏访问时间