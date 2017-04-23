package pers.westwind.learn.configs;

import pers.westwind.learn.annotation.HessianExporter;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultBeanNameGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.Properties;

/**
 * This is to generate Hessian Exporter Bean
 *
 * Created by Administrator on 2017/4/22.
 */
@Component
public class HessianExporterProcess implements ApplicationContextAware, BeanPostProcessor {
    private static ApplicationContext context;
    private static DefaultBeanNameGenerator nameGenerator = new DefaultBeanNameGenerator();

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        HessianExporterProcess.context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        ConfigurableApplicationContext context = (ConfigurableApplicationContext) HessianExporterProcess.context;
        BeanDefinitionRegistry registry = (BeanDefinitionRegistry) context
                .getBeanFactory();

        Annotation[] annotations = bean.getClass().getAnnotations();
        for(Annotation annotation : annotations) {
            if(annotation.annotationType().equals(HessianExporter.class)) {
                String exportName = registerHessianServiceExporter(registry, (HessianExporter) annotation, bean);
                registerMapping(registry, (HessianExporter) annotation, exportName);
            }
        }
        return bean;
    }

    private String registerHessianServiceExporter(
            BeanDefinitionRegistry registry, HessianExporter annotation, Object bean) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(HessianServiceExporter.class);
        AbstractBeanDefinition abd = builder.getRawBeanDefinition();

        MutablePropertyValues values = new MutablePropertyValues();
        values.add("service", bean);
        Class classType = annotation.serviceInterface();
        values.add("serviceInterface",  classType);
        abd.setPropertyValues(values);

        String exporterName = annotation.name();
        if(StringUtils.isEmpty(exporterName)) {
            exporterName = nameGenerator.generateBeanName(abd, registry);
        }
        registry.registerBeanDefinition(exporterName, builder.getRawBeanDefinition());

        return exporterName;
    }

    private void registerMapping(BeanDefinitionRegistry registry, HessianExporter annotation, String exportName) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(SimpleUrlHandlerMapping.class);
        AbstractBeanDefinition abd = builder.getRawBeanDefinition();

        MutablePropertyValues values = new MutablePropertyValues();
        Properties mappings = new Properties();
        mappings.setProperty(annotation.requestUrl(),
                exportName);

        values.add("mappings", mappings);
        abd.setPropertyValues(values);

        String mappingName = nameGenerator.generateBeanName(abd, registry);
        registry.registerBeanDefinition(mappingName, builder.getRawBeanDefinition());
    }
}
