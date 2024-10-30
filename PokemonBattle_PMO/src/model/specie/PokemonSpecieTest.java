package model.specie;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.enums.EnumPokemonType;
import model.enums.EnumSetPokemonType;
import model.size.Size;
import model.type.PokemonType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PokemonSpecieTest {

    private PokemonSpecie pokemonSpecie;
    private static final int nbPokemon = 4;
    private static final String namePokemon = "charmander";
    private static final PokemonType enumPokemonTypes = new PokemonType(new EnumSetPokemonType(EnumPokemonType.FIRE)); 
    private static final double height = 6.0;
    private static final double weight = 85.0;
    private static final String imagePath = "img/004.png";

    @BeforeEach
    void setUp() {
        pokemonSpecie = new PokemonSpecie(nbPokemon, namePokemon, enumPokemonTypes, height, weight, imagePath);
    }

    @Test
    void testConstructor_ValidParameters() {
        assertEquals(nbPokemon, pokemonSpecie.getNbPokemon());
        assertEquals(namePokemon, pokemonSpecie.getNamePokemon());
        assertEquals(enumPokemonTypes, pokemonSpecie.getTypes());
        assertEquals(height, pokemonSpecie.getSize().getHeight(), 0.001);
        assertEquals(weight, pokemonSpecie.getSize().getWeight(), 0.001);
        assertEquals(imagePath, pokemonSpecie.getImagePath());
    }

    @Test
    void testConstructor_NullName_ThrowsException() {
        assertThrows(NullPointerException.class, () ->
                new PokemonSpecie(nbPokemon, null, enumPokemonTypes, height, weight, imagePath));
    }

    @Test
    void testConstructor_NullTypes_ThrowsException() {
        assertThrows(NullPointerException.class, () ->
                new PokemonSpecie(nbPokemon, namePokemon, null, height, weight, imagePath));
    }

    @Test
    void testConstructor_NullImagePath_ThrowsException() {
        assertThrows(NullPointerException.class, () ->
                new PokemonSpecie(nbPokemon, namePokemon, enumPokemonTypes, height, weight, null));
    }

    @Test
    void testGetNbPokemon() {
        assertEquals(nbPokemon, pokemonSpecie.getNbPokemon());
    }

    @Test
    void testGetNamePokemon() {
        assertEquals(namePokemon, pokemonSpecie.getNamePokemon());
    }

    @Test
    void testGetTypes() {
        assertEquals(enumPokemonTypes, pokemonSpecie.getTypes());
    }

    @Test
    void testGetSize() {
        Size size = pokemonSpecie.getSize();
        assertEquals(height, size.getHeight(), 0.001);
        assertEquals(weight, size.getWeight(), 0.001);
    }

    @Test
    void testGetImagePath() {
        assertEquals(imagePath, pokemonSpecie.getImagePath());
    }

    @Test
    void testEqualsAndHashCode() {
        PokemonSpecie other = new PokemonSpecie(nbPokemon, namePokemon, enumPokemonTypes, height, weight, imagePath);
        assertEquals(pokemonSpecie, other);
        assertEquals(pokemonSpecie.hashCode(), other.hashCode());
    }

    /*
     * superfluo
    @Test
    void testToString() {
        String expected = "PokemonSpecie [nbPokemon=" + nbPokemon + ", namePokemon=" + namePokemon +
                ", types=" + enumPokemonTypes + ", size=" + pokemonSpecie.getSize() + ", imagePath=" + imagePath + "]";
        assertEquals(expected, pokemonSpecie.toString());
    }
    */
}
