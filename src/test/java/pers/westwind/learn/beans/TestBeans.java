package pers.westwind.learn.beans;

import pers.westwind.learn.configs.BeanConfigs;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Administrator on 2017/4/12.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BeanConfigs.class)
public class TestBeans {
    @Autowired
    private Bird bird;

    @Test
    public void testAutowired() {
        bird.fly();
        Assert.assertEquals(true, false);
    }
}
