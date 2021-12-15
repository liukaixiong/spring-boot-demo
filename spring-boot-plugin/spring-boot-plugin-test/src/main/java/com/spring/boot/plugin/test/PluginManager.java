package com.spring.boot.plugin.test;

import com.spring.api.ISendService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 插件管理器
 *
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/15 - 9:55
 */
public class PluginManager implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<String, AnnotationConfigApplicationContext> pluginManagerCache = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void registerPlugin(String pluginName, String pluginPath, String... packagePath) throws Exception {
        System.out.println(">>>>>>> 新增插件 : " + pluginName);
        AnnotationConfigApplicationContext pluginApplicationContext = new AnnotationConfigApplicationContext();
        // 加载父类的工厂
        pluginApplicationContext.setParent(this.applicationContext);

        URL url = new URL("file:" + pluginPath);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url});
        // 使用插件的类加载器 : class path resource [com/spring/plugin/DingDingService.class] cannot be opened because it does not exist
        // 代表这个应用上下文工厂的加载器是jar相关的
        pluginApplicationContext.setClassLoader(urlClassLoader);

        // 开始扫描类
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(pluginApplicationContext);
        scanner.setResourceLoader(new PathMatchingResourcePatternResolver(urlClassLoader));
        scanner.addIncludeFilter(new AssignableTypeFilter(ISendService.class));

        scanner.scan(packagePath);

        // 容器构建完成
        pluginApplicationContext.refresh();
        pluginManagerCache.put(pluginName, pluginApplicationContext);
    }

    public <T> List<T> getAllPlugin(Class<T> clazz) {
        List<T> sendServiceList = new ArrayList<>();
        for (AnnotationConfigApplicationContext context : pluginManagerCache.values()) {
            sendServiceList.addAll(context.getBeansOfType(clazz).values());
        }
        return sendServiceList;
    }

    public static void main(String[] args) throws Exception {
        // 模拟spring工厂启动
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //        applicationContext.registerBean(ConsoleLogService.class);
        applicationContext.refresh();

        PluginManager pluginMain = new PluginManager();
        pluginMain.setApplicationContext(applicationContext);
        String pluginPath =
            "D:\\elab\\project\\spring-cloud-demo\\spring-boot-plugin\\spring-boot-plugin-jar\\target\\spring-boot-plugin-jar-1.0-SNAPSHOT.jar";
        // 加载jar中的类到容器中,多个不同的包模拟多个jar
        pluginMain.registerPlugin("dingding", pluginPath, "com.spring.plugin.dingding", "com.spring.plugin.logback");
        pluginMain.registerPlugin("email", pluginPath, "com.spring.plugin.email");
        pluginMain.registerPlugin("weixin", pluginPath, "com.spring.plugin.weixin");

        // 父容器是获取不到子容器相关的内容的.
        Map<String, ISendService> sendServiceMap = applicationContext.getBeansOfType(ISendService.class);
        System.out.println(sendServiceMap.size());

        // 获取类
        invokePlugin(pluginMain);

        pluginMain.uninstallPlugin("dingding");
        // 获取类
        invokePlugin(pluginMain);
    }

    private static List<ISendService> invokePlugin(PluginManager pluginMain) {
        List<ISendService> allPlugin = pluginMain.getAllPlugin(ISendService.class);
        for (ISendService iSendService : allPlugin) {
            iSendService.send("hello word!");
        }
        return allPlugin;
    }

    /**
     * 卸载插件
     *
     * @param pluginName
     */
    private void uninstallPlugin(String pluginName) {
        System.out.println("<<<<< 开始卸载插件 : " + pluginName);
        pluginManagerCache.remove(pluginName);
    }
}
