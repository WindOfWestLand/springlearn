package pers.westwind.learn.configs;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by Administrator on 2017/4/12.
 */
public class WebMvcConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    protected String[] getServletMappings() {
        return new String[] {"/", "*.service"};
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        WebApplicationContext context = super.createServletApplicationContext();
        if(context instanceof BeanFactory
                && context instanceof AnnotationConfigRegistry) {

        }
        return context;
    }
}
