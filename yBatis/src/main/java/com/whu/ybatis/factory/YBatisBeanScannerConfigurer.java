package com.whu.ybatis.factory;

import com.whu.ybatis.annotation.YDao;
import com.whu.ybatis.aop.YBatisHandler;
import com.whu.ybatis.aspect.YBatisInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;

/**
 * 扫描配置文件的类
 * Created by hulichao on 2017/12/21
 */
public class YBatisBeanScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    /**
     * ,; \t\n
     */
    private String basePackage;
    /**
     * 默认是IDao,推荐使用Repository
     */
    private Class<? extends Annotation> annotation = YDao.class;
    /**
     * Map key类型
     */
    private String keyType = "origin";
    /**
     * 是否格式化sql
     */
    private boolean formatSql = false;
    /**
     * 是否输出sql
     */
    private boolean showSql = false;
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * yBatis拦截器
     */
    private YBatisInterceptor yBatisInterceptor;

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        /**
         * 注册代理类
         */
        registerRequestProxyHandler(registry);

        YBatisDaoClassMapperScanner scanner = new YBatisDaoClassMapperScanner(registry, annotation);
        /**
         * 加载Dao层接口
         */
        scanner.scan(StringUtils.tokenizeToStringArray(this.basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    }

    /**
     * RequestProxyHandler 手工注册代理类,减去了用户配置XML的烦恼
     *
     * @param registry
     */
    private void registerRequestProxyHandler(BeanDefinitionRegistry registry) {
        GenericBeanDefinition jdbcDaoProxyDefinition = new GenericBeanDefinition();
        jdbcDaoProxyDefinition.setBeanClass(YBatisHandler.class);
        jdbcDaoProxyDefinition.getPropertyValues().add("formatSql", formatSql);
        jdbcDaoProxyDefinition.getPropertyValues().add("keyType", keyType);
        jdbcDaoProxyDefinition.getPropertyValues().add("showSql", showSql);
        jdbcDaoProxyDefinition.getPropertyValues().add("dbType", dbType);
        jdbcDaoProxyDefinition.getPropertyValues().add("yBatisInterceptor", yBatisInterceptor);
        registry.registerBeanDefinition("yBatisHandler", jdbcDaoProxyDefinition);
    }

    public void setAnnotation(Class<? extends Annotation> annotation) {
        this.annotation = annotation;
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public void setFormatSql(boolean formatSql) {
        this.formatSql = formatSql;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public YBatisInterceptor getyBatisInterceptor() {
        return yBatisInterceptor;
    }

    public void setyBatisInterceptor(YBatisInterceptor yBatisInterceptor) {
        this.yBatisInterceptor = yBatisInterceptor;
    }
}
