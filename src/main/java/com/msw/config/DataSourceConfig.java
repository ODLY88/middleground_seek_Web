package com.msw.config;

import com.msw.annotation.DataSourceRegister;
import com.msw.config.factory.DataSourceFactory;
import com.msw.service.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Locale;

@Configuration
public class DataSourceConfig {
    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private DataSourceFactory dataSourceFactory;

    @PostConstruct
    public void init() {
        // 获取使用了指定注解的 Bean
        String[] allBeanNames = applicationContext.getBeanNamesForAnnotation(DataSourceRegister.class);
        for (String beanName : allBeanNames) {
            Object bean = applicationContext.getBean(beanName);
            // 检查类是否有 @DataSourceRegister 注解，不写也行因为获取时候就是为指定注解bean
            if (bean.getClass().isAnnotationPresent(DataSourceRegister.class)) {
                // 获取注解信息
                DataSourceRegister dataSourceRegister = bean.getClass().getAnnotation(DataSourceRegister.class);
                String key = dataSourceRegister.value();
                // 将 Bean 注册到 DataSourceFactory
                dataSourceFactory.registerDataSource(key.toLowerCase(Locale.ROOT), (DataSource<?>) bean);
            }
        }
    }
}