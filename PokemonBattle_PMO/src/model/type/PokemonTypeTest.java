package model.type;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.enums.EnumPokemonType;
import model.enums.EnumSetPokemonType;

import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


class PokemonTypeTest {

    private PokemonType pokemonType;
    private EnumSetPokemonType enumSetPokemonType;
    private EnumPokemonType type1;
    private EnumPokemonType type2;

    @BeforeEach
    void setUp() {
    	// Assuming FIRE is a valid EnumPokemonType value
        type1 = EnumPokemonType.FIRE; 
        // Assuming WATER is a valid EnumPokemonType value
        type2 = EnumPokemonType.WATER; 
        enumSetPokemonType = new EnumSetPokemonType(type1, type2);
        pokemonType = new PokemonType(enumSetPokemonType);
    }

    @Test
    void testConstructor_ValidEnumSet() {
        assertEquals(enumSetPokemonType, pokemonType.getEnumSetPokemonType());
    }
    
    
    @Test
    void testGetPokemonType() {
    	assertNull(PokemonType.getPokemonType(new EnumSetPokemonType(EnumPokemonType.GRASS)));
    }
	
    
    @Test
    void testValidFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("CSV/grid_types.csv"))) {
            String firstLine = br.readLine(); 
            String firstLineByCSV = "Type1, Type2, steel, fighting, dragon, water, electric, fairy, fire, ice, bug, normal, grass, poison, psychic, rock, ground, ghost, dark, flying";
            assertEquals(firstLineByCSV, firstLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void testGeneratePokemonType() throws IOException {
        PokemonType.generatePokemonType();

        PokemonType fireType = PokemonType.getPokemonType(new EnumSetPokemonType(EnumPokemonType.FIRE));
        assertNotNull(fireType);
        //value from csv
        assertEquals(0.25, fireType.getRatio(EnumPokemonType.FIRE));
        assertEquals(1.0, fireType.getRatio(EnumPokemonType.WATER));
    }
   

    @Test
    void testSize() {
        assertEquals(2, pokemonType.size());
    }

    @Test
    void testGet_ValidIndex() {
        assertEquals(type1, pokemonType.getEnumPokemonType(0));
    }

    @Test
    void testHasNext_And_Next() {
        pokemonType.resetIterator();
        assertTrue(pokemonType.hasNext());
        assertEquals(type1, pokemonType.next());
        assertTrue(pokemonType.hasNext());
        assertEquals(type2, pokemonType.next());
        assertFalse(pokemonType.hasNext());
    }

    @Test
    void testEqualsAndHashCode() {
        PokemonType other = new PokemonType(enumSetPokemonType);
        assertEquals(pokemonType, other);
        assertEquals(pokemonType.hashCode(), other.hashCode());
    }
    
    /*
     * superfluo
    @Test
    void testToString() {
        String expected = "PokemonType [enumPokemonTypes=" + enumSetPokemonType + ", typeRatio=" + pokemonType.typeRatio + "]";
        assertEquals(expected, pokemonType.toString());
    }
    */
}
