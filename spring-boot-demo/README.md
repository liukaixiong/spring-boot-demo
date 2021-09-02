# SpringBoot学习目录
## 如何在IDEA中提示自定义的属性变量
参考DemoConfig

## 启动时候各个监听的执行顺序
参考 : com.lkx.demo.springboot.listener.applicationListener


## SpringBoot admin ...

## 属性转换


## 端点暴露
`com.lkx.demo.springboot.endpoint`

结合配置文件
```yaml
management:
  endpoint:
    my:
      enabled: true   # 开启指定端点
    beans:
      enabled: true
    shutdown:
      enabled: true
  endpoints:
    web:
      exposure:
        include:
          - "*"  # 代表所有
          - shutdown
      exclude:
        - loggers
      base-path: /actuator
      path-mapping:
        my: /myMap    # 将@Endpoint(id = "my")中的my对应的路径做映射，这个时候原本的my就不可用了
```