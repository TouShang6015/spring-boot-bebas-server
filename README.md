# springboot-bebas

## 简介

### 框架介绍

前端采用vue2 + elementUI，后端采用springboot2.7，安全框架使用springSecurity，redis作为缓存数据库...

javaWeb快速开发框架，持久层使用mybatisPlus，封装BaseController进一步简化单表的增删改查，无需看到控制器中代码生成的冗余代码。springboot使用2.7+版本，结合了新版本的特性修改了部分配置。整体模块分层明确、耦合度低，跨模块调用无需强依赖模块，使开发者只需要专注业务代码即可。

>  后台框架从零搭建，前端使用的优秀开源框架若依（作者前端不擅长）

### 平台特点

- 简化单表增删改查，通过传递指定规则参数完成条件查询，无需添加动态sql
- SpringSecurity动态权限控制，统一接口路由管理，无需重启项目可实现接口的访问控制
- 统一配置管理，框架迁移减小代码改动
- 自定义注解实现异步任务执行，代码解耦
- 模块间通过接口形式实现跨模块调用，无需强依赖
- 全局事务配置
- 主键缓存
- TreeBuild类，简化树模型构建
- 策略器模式实现部门、角色权限范围管理，文件上传


最终controller代码

```java
/**
 * 通知公告表 控制器
 *
 * @author WuHao
 * @date 2022-05-25 22:41:42
 */
@RestController
@RequestMapping(ApiPrefixConstant.Modules.BASE + "/sysnotice")
@Api(value = "SysNoticeModel",tags = "通知公告")
public class SysNoticeController extends BaseController<ISysNoticeService,SysNoticeModel> {

  @Resource
  @Override
  public void setService(ISysNoticeService service) {
    super.service = service;
  }

}
```

### 目录结构

```
SpringBoot-bebas
├── modules -- 业务模块管理
├    ├── module-base -- base模块
├    ├── module-generate -- 代码生成模块
├    ├── module-quartz -- 定时任务模块
├── org-common -- 公共组件包
├── startapp -- 启动模块
```

### 总体架构

- Java 1.8
- MySql 8.0
- SpringBoot 2.7.4
- SpringSecurity
- Mybatis-Plus 3.5.2
- swagger 3.0.0
- dozermapper 6.5.2
- mapstruct 1.5.3.Final
- yitter 1.0.6 分布式id
- ......

### 内置功能

- 网站配置管理
- 系统管理
  - 用户管理
  - 部门管理
  - 岗位管理
  - 字典管理
  - 通知公告
  - 素材管理
- 权限管理
  - 角色管理
  - 菜单管理
  - 接口路由管理
- 日志管理
- 系统工具
  - 表单构建
  - 代码生成
  - 接口文档
  - 定时任务

> 部分功能未完善，持续更新中

后续需要完善的内容：

|   功能    |                描述                 |
| :-------: | :---------------------------------: |
| excel导出 | 使用easyExcel框架，实现导入到出功能 |
|  国际化   |          前后端国际化配置           |
| 主键缓存  |    并发下主键缓存保证数据一致性     |
|  .......  |                                     |

## 使用文档

> 项目后续有功能未完善，bebasWh-common、core包放在了远程仓库中，后续功能基本完善后会添加

### 项目本地部署

部署相关的文档以及文件在根目录下的`_doc`文件中

1、maven的setting.xml添加以下内容

```shell
	<servers>
		<server>
			<id>rdc-releases</id>
			<username>63493a921b97d790e6149f6a</username>
			<password>VPD=8J2lg3DY</password>
		</server>
		<server>
			<id>rdc-snapshots</id>
			<username>63493a921b97d790e6149f6a</username>
			<password>VPD=8J2lg3DY</password>
		</server>
	</servers>
```

2、 将sql文件导入至数据库

3、 startapp模块中，修改application.yml  中的配置信息

> local为本地环境，test为测试环境，在父工程的pom中修改profiles标签

```xml
<!-- 如需要指定local环境，则在local标签下添加以下配置 -->
<!-- 标签不能重复 -->
<activation>
  <activeByDefault>true</activeByDefault>
</activation>
```

- mysql 连接信息

```yml
  # 数据库mysql
  datasource:
    druid:
      driver-class-name: com.mysql.cj.jdbc.Driver
      type: com.alibaba.druid.pool.xa.DruidXADataSource
      url: jdbc:mysql://localhost:3306/导入后的数据库名?useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai&autoReconnect=true&characterEncoding=utf8&allowPublicKeyRetrieval=true
      username: 你的mysql用户名
      password: 你的mysql密码
```

- redis连接信息

```
  # redis配置
  redis:
    host: 127.0.0.1
    port: 6379
    database: 10
    password:
    timeout: 10s
```

4、等待maven加载完成

5、 startapp模块下，启动StartApplication

``` shell
默认端口7001
```

 **我的个人博客** ： [equinox-blog](http://www.equinoxblog.site)

## 运行截图
> 首页

![图片.png](http://static.equinoxblog.site/articles/34a56d1d8035e5884f9f538680ec79f2.png)
> 网站信息配置

![图片.png](http://static.equinoxblog.site/articles/49344f5f2c552f0a86c61c66ed7dee90.png)
> 动态路由权限配置

![图片.png](http://static.equinoxblog.site/articles/fb9418437412aff424c1dadac26fa98c.png)
> 用户管理

![图片.png](http://static.equinoxblog.site/articles/7d820dfac984bd55162d7e04476f5877.png)
> 素材管理

![图片.png](http://static.equinoxblog.site/articles/963df79ec312ffc3089d3bf918109b73.png)
> 角色管理

![图片.png](http://static.equinoxblog.site/articles/1e89485eadc6b31532e78177b383ea1c.png)
> 代码生成

![图片.png](http://static.equinoxblog.site/articles/62e7dc3f5f5ce3d1ac1b545368eba2cc.png)
