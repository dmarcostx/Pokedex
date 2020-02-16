package br.gov.sp.fatec.marcos.teixeira13.Pokedex;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.model.Pokemon;
import br.gov.sp.fatec.marcos.teixeira13.Pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private PokemonRepository repository;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/new/{id}")
    String create(@PathVariable final short id, Model model) {
        model.addAttribute("id", id);
        return "create";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    String save(@RequestParam final short numero, @RequestParam final String nome) {
        Pokemon.instance(numero, nome).map(repository::save);
        return "error";
    }

    @RequestMapping("/edit/{id}")
    String edit(@PathVariable final short id) {
        System.out.println(id);
        return "index";
    }

    @RequestMapping("/del/{id}")
    String delete(@PathVariable final short id) {
        System.out.println(id);
        return "index";
    }
}
