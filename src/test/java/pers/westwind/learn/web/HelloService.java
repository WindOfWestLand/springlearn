package pers.westwind.learn.web;

import com.caucho.hessian.client.HessianProxyFactory;
import org.junit.Test;
import pers.westwind.learn.web.service.hessian.Hello;
import pers.westwind.learn.web.service.hessian.SecondHello;

import java.net.MalformedURLException;

/**
 * Created by Administrator on 2017/4/17.
 */
public class HelloService {

    @Test
    public void testHello() {
        String url = "http://localhost:8080/spring-learn/hello.service";
        HessianProxyFactory factory = new HessianProxyFactory();

        try {
            Hello service = (Hello) factory.create(
                    Hello.class, url);
            System.out.println(service.hello("world"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHello2() {
        String url = "http://localhost:8080/spring-learn/hello2.service";
        HessianProxyFactory factory = new HessianProxyFactory();

        try {
            SecondHello service = (SecondHello) factory.create(
                    SecondHello.class, url);
            System.out.println(service.sayHello2());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
