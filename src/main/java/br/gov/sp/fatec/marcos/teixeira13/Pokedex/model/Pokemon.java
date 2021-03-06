package br.gov.sp.fatec.marcos.teixeira13.Pokedex.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Optional;

@Entity(name = "Pokemon")
public class Pokemon {

    @Id
    public short numero;

    public String nome;

    public Pokemon() {
    }

    private Pokemon(final short numero, final String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public static Optional<Pokemon> instance(final short numero, final String nome) {
        if (numero <= 0 || nome.isBlank())
            return Optional.empty();
        return Optional.of(new Pokemon(numero, nome));
    }
}
