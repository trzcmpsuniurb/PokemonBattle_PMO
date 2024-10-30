package model.pokemon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.enums.EnumPokemonType;
import model.enums.EnumSetPokemonType;
import model.move.CategoryMove;
import model.move.Move;
import model.specie.PokemonSpecie;
import model.stats.Stats;
import model.type.PokemonType;
import org.junit.jupiter.api.BeforeEach;

class PokemonTest {

    private Pokemon pokemon;
    private PokemonSpecie specie;
    private Move move1;
    private Move move2;
    private Stats stats;
    private Move[] moves;

    @BeforeEach
    void setUp() {
        // Initialization of species, moves, and stats for the test
        specie = new PokemonSpecie(4, "charmander", new PokemonType(new EnumSetPokemonType(EnumPokemonType.FIRE)), 6.0, 85.0, "img/004.png");
        move1 = new Move("sketch", 100, 20, 100, CategoryMove.PHYSICAL, EnumPokemonType.NORMAL);
        move2 = new Move("heat", 50, 25, 100, CategoryMove.SPECIAL, EnumPokemonType.FIRE);

        moves = new Move[] { move1, move2 };
        stats = new Stats(50, 60, 40, 70, 50, 65);
        pokemon = new Pokemon(specie, moves, stats, "charmander");
    }

    @Test
    void testConstructor() {
        assertNotNull(pokemon);
        assertEquals("charmander", pokemon.getNickName());
        assertEquals(specie, pokemon.getSpecie());
        assertArrayEquals(moves, pokemon.getMoves());
        assertEquals(stats, pokemon.getStat());
    }

    @Test
    void testGetNickName() {
        assertEquals("charmander", pokemon.getNickName());
    }

    @Test
    void testIsAlive() {
        assertTrue(pokemon.isAlive());
     // Assuming initial HP is 50 and this results in KO
        stats.substractDamage(100);  
        assertFalse(pokemon.isAlive());
    }

    @Test
    void testTakeDmg() {
        int damage = 20;
        boolean isDead = pokemon.takeDmg(damage);
        // HP still above 0 after damage
        assertFalse(isDead); 
        // HP have been correctly reduced (65-20 = 45)
        assertEquals(45, Integer.parseInt(stats.pvOnPvMax().split("/")[0]));
    }

    @Test
    void testRestore() {
        pokemon.takeDmg(30);
        //int currentHP = Integer.parseInt(stats.pvOnPvMax().split("/")[0]);
        //int maxHP = Integer.parseInt(stats.pvOnPvMax().split("/")[1]);
        // Check that HP is not full
        assertTrue(Integer.parseInt(stats.pvOnPvMax().split("/")[0]) < Integer.parseInt(stats.pvOnPvMax().split("/")[1])); 
        pokemon.restore();
        // Check that HP has been restored
        assertEquals(Integer.parseInt(stats.pvOnPvMax().split("/")[1]), Integer.parseInt(stats.pvOnPvMax().split("/")[0]));  
    }

    @Test
    void testNoMoreFight() {
        // Initially, moves are available
        assertFalse(pokemon.noMoreFight());  

        // Decrease available PP of move 1 and move 2
        int maxPPmove1 = move1.getMaxPowerPoint();
        int maxPPmove2 = move2.getMaxPowerPoint();
        for (int i = 0; i < maxPPmove1; i++) {
            move1.substractPP();
        }
        for (int i = 0; i < maxPPmove2; i++) {
            move2.substractPP();
        }
        // No move should be available
        assertTrue(pokemon.noMoreFight());  

    }
    
    /*
     * test commented as Pokedex initialization would be required
    @Test
    void testTakeDmgCap() {
        // Assuming specie.getTypes().getRatio(EnumPokemonType.FIRE) returns 2.0
        Move move2 = new Move("heat", 50, 25, 100, CategoryMove.SPECIAL, EnumPokemonType.FIRE);
        boolean survivedAfterMove = pokemon.takeDmgCap(move2);
        System.out.println("pippo" + survivedAfterMove);
        // Check that the Pokemon is still alive after taking the damage
        assertTrue(survivedAfterMove);  
    }
    */
  
}
