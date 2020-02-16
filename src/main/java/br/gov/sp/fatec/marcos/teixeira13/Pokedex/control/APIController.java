package br.gov.sp.fatec.marcos.teixeira13.Pokedex.control;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private Service service;

    @RequestMapping("/")
    String index() {
        return service.api().orElse(null);
    }
}
