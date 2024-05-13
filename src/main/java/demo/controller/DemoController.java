package demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class DemoController {


    @RequestMapping( "/index")
    public String index() {
        log.info("access index2 to skip index.jsp page");
        return "index";
    }


    @GetMapping( "/demo2")
    @ResponseBody
    public String demo2(){
        log.info("demo2 access");
        return "demo2 access ok";
    }

}
