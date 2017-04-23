package pers.westwind.learn.web.service.hessian;

import pers.westwind.learn.annotation.HessianExporter;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/19.
 */
@Service
@HessianExporter(requestUrl = "/hello2.service",
        serviceInterface = SecondHello.class,
        name = "hello2")
public class SecondHelloImpl implements SecondHello {
    public String sayHello2() {
        return "I'm hello 2!";
    }
}
