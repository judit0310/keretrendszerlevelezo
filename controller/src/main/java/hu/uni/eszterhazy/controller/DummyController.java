package hu.uni.eszterhazy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DummyController {

    @RequestMapping(value = "/hello")
    public void hello(){
        System.out.println("Hello");
    }


}
