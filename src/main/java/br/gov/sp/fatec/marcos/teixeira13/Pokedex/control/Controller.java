package br.gov.sp.fatec.marcos.teixeira13.Pokedex.control;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.model.Pokemon;
import br.gov.sp.fatec.marcos.teixeira13.Pokedex.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    private Service service;

    @RequestMapping("/")
    String index() {
        return "index";
    }

    @RequestMapping("/new/{id}")
    String create(@PathVariable final short id, final Model model) {
        if (!service.exists(id)) {
            model.addAttribute("id", id);
            return "create";
        } else
            return "error";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    String save(@RequestParam final short numero, @RequestParam final String nome) {
        Pokemon.instance(numero, nome).map(pokemon -> service.save(pokemon));
        return "error";
    }

    @RequestMapping("/edit/{id}")
    String edit(@PathVariable final short id, final Model model) {
        final Optional<Pokemon> optional = service.get(id);
        if (optional.isPresent()) {
            model.addAttribute("pokemon", optional.get());
            return "edit";
        } else
            return "error";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    String update(@RequestParam final short numero, @RequestParam final String nome) {
        Pokemon.instance(numero, nome).map(pokemon -> service.save(pokemon));
        return "error";
    }

    @RequestMapping("/del/{id}")
    String del(@PathVariable final short id, Model model) {
        final Optional<Pokemon> optional = service.get(id);
        if (optional.isPresent()) {
            model.addAttribute("pokemon", optional.get());
            return "delete";
        } else
            return "error";
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    String delete(@RequestParam final short id) {
        service.deleteById(id);
        return "error";
    }
}
