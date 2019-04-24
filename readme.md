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
