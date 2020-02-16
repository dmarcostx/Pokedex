package br.gov.sp.fatec.marcos.teixeira13.Pokedex.service;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.model.Pokemon;
import br.gov.sp.fatec.marcos.teixeira13.Pokedex.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class Service {
    @Autowired
    private PokemonRepository repository;

    public Optional<String> api() {
        return Buffer.api(repository);
    }

    public boolean exists(final short id) {
        return Buffer.exists(id, repository);
    }

    public Service deleteById(final short id) {
        Buffer.deleteById(id, repository);
        return this;
    }

    public Optional<Pokemon> get(final short id) {
        return Buffer.get(id, repository);
    }

    public Pokemon save(Pokemon pokemon) {
        return Buffer.save(pokemon, repository);
    }
}
