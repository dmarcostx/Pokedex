package br.gov.sp.fatec.marcos.teixeira13.Pokedex.service;

import br.gov.sp.fatec.marcos.teixeira13.Pokedex.model.Pokemon;
import org.springframework.data.repository.CrudRepository;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

class Buffer {

    private final static HashMap<Short, String> buffer = new HashMap<>();

    static Optional<String> api(CrudRepository<Pokemon, Short> repository) {
        final short size = (short) repository.count();
        if (size < 1)
            return Optional.empty();
        if (size != buffer.size()) {
            buffer.clear();
            repository.findAll().forEach(pokemon -> buffer.put(pokemon.numero, pokemon.nome));
        }
        return new Random().ints(1, 810).boxed()
                .map(Integer::shortValue).filter(buffer::containsKey)
                .findAny().map(numero -> {
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    try {
                        final KeyPairGenerator ec = KeyPairGenerator.getInstance("RSA");
                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.ENCRYPT_MODE, ec.generateKeyPair().getPublic());
                        ObjectOutputStream o = new ObjectOutputStream(b);
                        o.writeObject(new SealedObject(Pokemon.instance(numero, buffer.get(numero)).get(), cipher));
                        o.close();
                    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IOException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                    return Base64.getEncoder().encodeToString(b.toByteArray());
                });
    }

    static boolean exists(final short id, CrudRepository<Pokemon, Short> repository) {
        return buffer.containsKey(id) || repository.existsById(id);
    }

    static void deleteById(final short id, CrudRepository<Pokemon, Short> repository) {
        buffer.remove(id);
        repository.deleteById(id);
    }

    static Optional<Pokemon> get(final short id, CrudRepository<Pokemon, Short> repository) {
        if (buffer.containsKey(id))
            return Pokemon.instance(id, buffer.get(id));
        final Optional<Pokemon> optional = repository.findById(id);
        optional.ifPresent(pokemon -> buffer.put(pokemon.numero, pokemon.nome));
        return optional;
    }

    static Pokemon save(Pokemon pokemon, CrudRepository<Pokemon, Short> repository) {
        buffer.put(pokemon.numero, pokemon.nome);
        return repository.save(pokemon);
    }
}
