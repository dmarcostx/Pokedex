package br.gov.sp.fatec.marcos.teixeira13.Pokedex.control;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.Buffer;
import br.gov.sp.fatec.marcos.teixeira13.Pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class APIController {

    @Autowired
    private PokemonRepository repository;

    @RequestMapping("/")
    String index() {
        return Buffer.api(repository).orElse(null);
    }
}
