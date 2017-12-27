package com.whu.ybatis.factory;

import com.whu.ybatis.annotation.YDao;
import com.whu.ybatis.aop.YBatisHandler;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;

/**
 * Created by hulichao on 2017/12/21
 */
public class YBatisDaoClassMapperScanner extends ClassPathBeanDefinitionScanner {
    private static final Logger logger = Logger.getLogger(YBatisHandler.class);

    public YBatisDaoClassMapperScanner(BeanDefinitionRegistry registry, Class<? extends Annotation> annotation) {
        super(registry, false);
        addIncludeFilter(new AnnotationTypeFilter(annotation));
        if (!YDao.class.equals(annotation)) {
            addIncludeFilter(new AnnotationTypeFilter(YDao.class));
        }
    }

    @Override
    public Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitions = super.doScan(basePackages);

        if (beanDefinitions.isEmpty()) {
            logger.warn("No Dao interface was found in '" + Arrays.toString(basePackages) + "' package. Please check your configuration.");
        }
        GenericBeanDefinition definition;
        for (BeanDefinitionHolder holder : beanDefinitions) {
            definition = (GenericBeanDefinition) holder.getBeanDefinition();
            definition.getPropertyValues().add("proxy", getRegistry().getBeanDefinition("yBatisHandler"));
            definition.getPropertyValues().add("daoInterface", definition.getBeanClassName());
            if (logger.isInfoEnabled()) {
                logger.info("register yBatis name is { " + definition.getBeanClassName() + " }");
            }
            definition.setBeanClass(YBatisDaoBeanFactory.class);
        }

        return beanDefinitions;
    }

    /**
     * 默认不允许接口的,这里重写,覆盖下,另外默认会Scan @Component 这样所以的被@Component 注解的都会Scan
     */
    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
    }

}
