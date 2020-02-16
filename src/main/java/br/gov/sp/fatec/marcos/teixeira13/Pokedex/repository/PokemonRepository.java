package br.gov.sp.fatec.marcos.teixeira13.Pokedex.repository;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.model.Pokemon;
import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, Short> {
}
