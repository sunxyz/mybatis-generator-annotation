# mybatis-generator-annotation
**介绍：**
这是一个关于mybatis的简单的生成工具 它可以帮助你完成一些简单的crud操作
**使用：**
它的配置非常简单
你只需要找到resource/config.yml稍作更改即可使用
下面是一个简单的config.yml的配置 它可以满足日常简单的开发需要
```
#数据源配置
url: jdbc:mysql://localhost:3306/test
userName: root
password: 123456
#实体类配置    
entityPackage: cn.sunxyz.test.enitity
mapperPackage: cn.sunxyz.test.mapper
#生成代码的保存路径
filePath: d:/generator/src/
table2ClazzMap:
#表和对象映射
    user: User
    admin: Admin
```
完成以上配置后 打开 App类 运行即可 
反馈与联系：sunxzg@gmail.com       


en

**Introduction：**
This is a simple tool to generate mybatis it can help you do some simple crud operation
**use：**
Its configuration is very simple
You just need to find the resource / config.yml minor changes can be used
Here is a simple config.xml configuration it simple to meet the daily needs of the development

```
#Data Source Configuration
url: jdbc:mysql://localhost:3306/test
userName: root
password: 123456
#Entity class configuration    
entityPackage: cn.sunxyz.test.enitity
mapperPackage: cn.sunxyz.test.mapper
#Save path generated code
filePath: d:/generator/src/
table2ClazzMap:
#Tables and maps
    user: User
    admin: Admin
```

After completing the above configurations can be run to open the App class
Feedback & Contact：sunxzg@gmail.com       
