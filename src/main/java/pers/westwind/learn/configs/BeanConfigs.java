package pers.westwind.learn.configs;

import pers.westwind.learn.beans.Bird;
import pers.westwind.learn.beans.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/4/12.
 */
@Configuration
@ComponentScan("pers.westwind.learn.beans")
public class BeanConfigs {
    @Bean
    public Bird getBird() {
        return new Parrot();
    }
}
