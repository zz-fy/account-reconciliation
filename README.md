# account-reconciliation
帮朋友做的代售对账系统，比较简单，也可用来做项目模板用，持续更新 - SpringBoot+Spring Security+Mybatis Plus+Mysql+Redis+Jwt+Vue2+ElementUI

都是些CRUD的内容,没有什么操作难度,先放在github上,后面要是要帮忙改点东西好找代码

部署的话也比较简单是将vue项目打包放在后端当静态资源处理的哈

nohup java -jar behalf-0.0.1-SNAPSHOT.jar --spring.config.additional-location=config/application.yaml > app.log 2>&1 &

配置文件根据部署的机器设置业务环境地址
