package pers.westwind.learn.web.service.hessian;

import pers.westwind.learn.annotation.HessianExporter;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/17.
 */
@Service("testServiceImpl")
@HessianExporter(requestUrl = "/hello.service",
        serviceInterface = Hello.class)
public class HelloImpl implements Hello {
    public String hello(String name) {
        return "Hello, " + name;
    }
}
