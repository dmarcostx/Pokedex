package br.gov.sp.fatec.marcos.teixeira13.Spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/")
    String index() {
        return "index";
    }
}
