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

## 智能生命周期的回调
接口 : `org.springframework.context.SmartLifecycle`

案例 :  `com.lkx.demo.springboot.componts.StartSmartLifeCycle`

当容器初始化完成之后，会回调`SmartLifecycle` 的 start方法，比较适合容器初始化完成之后需要执行异步任务。
当start的方法执行完毕之后，记得将`isRunning`方法设置为true代表已经执行成功了.
- isRunning 只有在false的时候start方法才会被触发

## 各种监听回调事件
包 : `com.lkx.demo.springboot.listener.applicationListener`
- EnvListener : 环境变量
- FailedEventListener : 启动失败
- PreparedEventListener: 在refresh开始前
- ReadyEventListener : 在refresh之后
- StartListener : 程序启动
