package br.gov.sp.fatec.marcos.teixeira13.Pokedex;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.model.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.HashMap;
import java.util.Optional;

public class Buffer {

    private final static HashMap<Short, String> buffer = new HashMap<>();

    public static boolean contains(final short id, CrudRepository<Pokemon, Short> repository) {
        return buffer.containsKey(id) || repository.existsById(id);
    }

    public static void deleteById(final short id, CrudRepository<Pokemon, Short> repository) {
        buffer.remove(id);
        repository.deleteById(id);
    }

    public static Optional<Pokemon> get(final short id, CrudRepository<Pokemon, Short> repository) {
        if (buffer.containsKey(id))
            return Pokemon.instance(id, buffer.get(id));
        final Optional<Pokemon> optional = repository.findById(id);
        optional.ifPresent(pokemon -> buffer.put(pokemon.numero, pokemon.nome));
        return optional;
    }

    public static Pokemon save(Pokemon pokemon, CrudRepository<Pokemon, Short> repository) {
        buffer.put(pokemon.numero, pokemon.nome);
        return repository.save(pokemon);
    }
}
