package br.gov.sp.fatec.marcos.teixeira13.Spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping("/")
    @ResponseBody
    String index() {
        return "<div style='text-align: center;'><img src='https://spring.io/img/homepage/icon-spring-boot.svg' width='250px'><br><h1>Aprendendo String Boot</h1></div>";
    }
}
