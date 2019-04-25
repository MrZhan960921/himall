## 环境配置

### 更换Centos7的yum源
1）下载repo文件 wget http://mirrors.aliyun.com/repo/Centos-7.repo

2）备份并替换系统的repo文件

cp Centos-7.repo /etc/yum.repos.d/

cd /etc/yum.repos.d/

mv CentOS-Base.repo CentOS-Base.repo.bak

mv Centos-7.repo CentOS-Base.repo

3）执行yum源更新命令

yum clean all

yum makecache

yum update

### vsftpd安装

### nginx配置

复杂均衡，转发，反向代理

## 项目初始化

通过org.apache.maven.archetypes:maven-archetype-webapp模板构建web项目

### 使用mybatis generator生成dao层和xml文件

1. pom文件引入mybatis generator插件
2. 添加generatorConfig配置文件来配置属性
3. 添加数据库配置
4. 在maven插件中运行mybatis generator

### ssm相关配置

见代码

## 用户模块

通用响应类，因为@ResponseBody会将java类转成json返回给前端，所以要实现Serializable接口。对于一些不想返回的属性要结合@JsonIgonre之类的注解

实现枚举类
```java
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }
}
```

mybatis中传递多个参数要用@Param注解，映射为map，xml中parameterType=map

### 忘记密码功能

忘记密码回答完正确问题要返回一个token，访问修改密码接口必须带上这个token，否则会有横向越权问题。

将token放入本地Guava缓存中

Guava缓存可以看做是一个map

## 商品模块

### Properties文件读取

### jodatime处理时间(很强大)
放一串代码感受下
```java
DateTime dateTime_1 = dateTime.plusMonths(1).plusDays(3).plusHours(-8);//随便++--日期，想怎么操作怎么操作
```


