package com.spring.boot.plugin.test;

import com.spring.boot.plugin.test.service.DingDingService;
import com.spring.boot.plugin.test.service.EmailService;
import com.spring.boot.plugin.test.service.SmsService;
import com.spring.boot.plugin.test.service.WeiXinService;
import com.spring.api.ISendService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.filter.AssignableTypeFilter;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liukaixiong
 * @Email liukx@elab-plus.com
 * @date 2021/12/14 - 19:09
 */
public class PluginMain implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<String, AnnotationConfigApplicationContext> pluginManagerCache = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void registerPluginClass(String pluginName, Class<?>... clazz) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
            new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.setParent(applicationContext);
        for (Class<?> beanClass : clazz) {
            annotationConfigApplicationContext.registerBean(beanClass);
        }
        annotationConfigApplicationContext.refresh();
        pluginManagerCache.put(pluginName, annotationConfigApplicationContext);
    }

    public Map<String, AnnotationConfigApplicationContext> getPluginManagerCache() {
        return pluginManagerCache;
    }

    public <T> List<T> getAllPlugin(Class<T> clazz) {
        List<T> sendServiceList = new ArrayList<>();
        for (AnnotationConfigApplicationContext context : pluginManagerCache.values()) {
            sendServiceList.add(context.getBean(clazz));
        }
        return sendServiceList;
    }

    public static void main(String[] args) throws Exception {
        // 模拟spring工厂启动
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();

        PluginMain pluginMain = new PluginMain();
        String pluginPath =
            "D:\\elab\\project\\spring-cloud-demo\\spring-boot-plugin\\spring-boot-plugin-jar\\target\\spring-boot-plugin-jar-1.0-SNAPSHOT.jar";
        pluginMain.registerPlugin("spring-boot-plugin-jar", pluginPath);

        List<ISendService> allPlugin = pluginMain.getAllPlugin(ISendService.class);
        for (ISendService iSendService : allPlugin) {
            iSendService.send("hello word!");
        }
        System.out.println(allPlugin.size());
    }

    private void registerPlugin(String pluginName, String pluginPath) throws MalformedURLException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        URL url = new URL("file:" + pluginPath);
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url});
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(applicationContext);
        scanner.setResourceLoader(new PathMatchingResourcePatternResolver(urlClassLoader));
        scanner.addIncludeFilter(new AssignableTypeFilter(ISendService.class));
        scanner.scan("com.spring.plugin");
        applicationContext.refresh();
        pluginManagerCache.put(pluginName, applicationContext);
    }

    private static void testLocalPlugin() {
        // 模拟spring工厂启动
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.registerBean(EmailService.class);
        applicationContext.refresh();

        PluginMain pluginMain = new PluginMain();
        pluginMain.setApplicationContext(applicationContext);
        pluginMain.registerPluginClass("sms", SmsService.class);
        pluginMain.registerPluginClass("weixin", WeiXinService.class);
        pluginMain.registerPluginClass("dingding", DingDingService.class);

        // 如果从父工厂中查找，你会发现只会找到EmailService
        Map<String, ISendService> beansOfType = applicationContext.getBeansOfType(ISendService.class);

        // 插件从上级工厂找到了EmailService，但是你如果查找同类型的话，如果子类有的话是不会向上查找的。
        EmailService emailService = pluginMain.getPluginManagerCache().get("sms").getBean(EmailService.class);

        // 从子插件工厂中查找 只能查找到A
        Map<String, ISendService> sms =
            pluginMain.getPluginManagerCache().get("sms").getBeansOfType(ISendService.class);
        Map<String, ISendService> weixin =
            pluginMain.getPluginManagerCache().get("weixin").getBeansOfType(ISendService.class);
        Map<String, ISendService> dingding =
            pluginMain.getPluginManagerCache().get("dingding").getBeansOfType(ISendService.class);

        List<ISendService> allPlugin = pluginMain.getAllPlugin(ISendService.class);
        for (ISendService iSendService : allPlugin) {
            iSendService.send("hello word!");
        }
        System.out.println(allPlugin.size());
    }

    private static void test1() {
        PluginMain pluginMain = new PluginMain();
        Map<String, AnnotationConfigApplicationContext> pluginManager = new HashMap<>();

        pluginManager.put("sms", pluginMain.builderFactory(SmsService.class));
        pluginManager.put("dingding", pluginMain.builderFactory(DingDingService.class));
        pluginManager.put("weixin", pluginMain.builderFactory(WeiXinService.class));

        pluginManager.values().forEach((context) -> {
            ISendService bean = context.getBean(ISendService.class);
            bean.send(" hello word!");
        });
    }

    private AnnotationConfigApplicationContext builderFactory(Class<?> clazz) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext =
            new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.setParent(applicationContext);
        annotationConfigApplicationContext.registerBean(clazz);
        annotationConfigApplicationContext.refresh();
        return annotationConfigApplicationContext;
    }

}
