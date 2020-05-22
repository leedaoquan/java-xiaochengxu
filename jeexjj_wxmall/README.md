#### 体验地址
1. [商城后台猛击http://47.93.60.219/wxmall](http://47.93.60.219/wxmall)

#### 项目介绍
jeexjj_wxmall是基于jeexjj轻量级快速开发框架开发的小程序商城，致力于行业全覆盖一体化微信小程序解决方案的开源框架，3分钟生成各行业小程序。
jeexjj是一款基于SSM的一款快速开发框架，能够根据数据库生成单表和一对多表的增删改查代码，使开发节省50%的开发工作量。
使用XJJ可以快速地开发出企业级的Web应用系统。 生成的代码统一规范、统一风格、统一结构便于管理维护。
前端页面使用当前最流行的ACE和bootstrap技术，自适应pc、移动端。
- 是基于jeexjj轻量级快速开发框架开发的小程序商城。
- 基于springboot的微服务模式，可平滑升级为微服务高并发架构。
- 致力于行业全覆盖一体化微信小程序解决方案的开源框架，3分钟生成各行业小程序。
- 了解jeexjj轻量级快速开发框架，请移步 [码云开源地址](https://gitee.com/zhanghejie/jeexjj) 或  [github开源地址](https://github.com/zhanghejie/jeexjj)。

#### 项目架构
- jeexjj_wxmall-admin 是后台管理端,基于springboot和jeexjj框架。
- jeexjj-wxmall-app-yx是防网易严选小程序源码.后续会出更多风格的小程序商城。
#### 技术选型
技术 | 名称 | 官网
----|------|----
JEEXJJ | 快速开发框架  | [https://gitee.com/zhanghejie/jeexjj](https://gitee.com/zhanghejie/jeexjj)
miniprogram | 微信小程序  | [https://developers.weixin.qq.com/miniprogram/dev/api/](https://developers.weixin.qq.com/miniprogram/dev/api/)


#### 项目页面效果
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/index1.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/index2.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/index3.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/cart.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/category.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/home.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/conpon.png)
![](https://gitee.com/jeexjj/jeexjj_wxmall/raw/master/doc/images/foot.png)

#### 安装教程

1. 开发环境 jdk1.8+、mysql5.7+、maven3.5+、myeclipse2017（idea）、[微信开发工具地址](https://developers.weixin.qq.com/miniprogram/dev/devtools/download.html)
2. 下载代码,初始化数据库doc/db/*db*.sql
3. 用myeclipse或idea导入maven项目jeexjj_wxmall-admin，注意jeexjj_wxmall-admin为java项目的根目录。而不要把jeexjj_wxmall做为项目的根目录。
4. 修改配置文件/jeexjj-wxmall-admin/src/main/resources/application.properties的部分配置信息，根据自已的环境设置
```
spring.datasource.url=数据库地址
spring.datasource.username=用户名
spring.datasource.password=密码
spring.resources.static-locations=项目的static目录位置，例：file:D:/jeexjj_wxmall/jeexjj-wxmall-admin/src/main/resources/static

wx.app-id=自己的小程序测试号
wx.app-secret=自己的小程序secret.
```
5. 运行 jeexjj-wxmall-admin/src/main/java/com/xjj/Application.java 的main方法，启动项目。
6. 访问地址 http://localhost:8882/wxmall-admin，输入admin/123456登陆商城后台。
7. 用微信开发工具打开小程序项目jeexjj-wxmall-app-yx，并运行，具休步骤不再赘述。
8. 附开发工具下载地址：https://pan.baidu.com/s/1BXnWGkASzmYDroIYtJbCBg

#### 致谢
本项目基于或参考以下项目：
> 1. [litemall](https://gitee.com/linlinjava/litemall)又一个小程序商城
> 2. [nideshop-mini-program](https://github.com/tumobi/nideshop-mini-program)开源微信小程序商城

#### JEEXJJ开源项目

 名称 | 地址
------|----
快速开发框架  | [https://gitee.com/zhanghejie/jeexjj](https://gitee.com/zhanghejie/jeexjj)
小程序商城  | [https://gitee.com/jeexjj/jeexjj_wxmall](https://gitee.com/jeexjj/jeexjj_wxmall)
VUE仿锤子商城  | [https://gitee.com/zhanghejie/jeexjj_mall](https://gitee.com/zhanghejie/jeexjj_mall)
防CAS单点登陆  | [https://gitee.com/zhanghejie/jeexjj_sso](https://gitee.com/zhanghejie/jeexjj_sso)