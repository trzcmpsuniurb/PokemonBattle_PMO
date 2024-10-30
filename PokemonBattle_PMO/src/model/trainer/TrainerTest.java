package model.trainer;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import model.enums.EnumPokemonType;
import model.enums.EnumSetPokemonType;
import model.move.CategoryMove;
import model.move.Move;
import model.pokemon.Pokemon;
import model.specie.PokemonSpecie;
import model.stats.Stats;
import model.type.PokemonType;
import org.junit.jupiter.api.BeforeEach;

class TrainerTest {

    private Trainer trainer;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon[] pokemons;

    @BeforeEach
    void setUp() {
    	//pokemons1= new Pokemon[1];
    	//pokemons2= new Pokemon[1];
    	
    	EnumSetPokemonType enumSetPokemonType1 = new EnumSetPokemonType(EnumPokemonType.GROUND, EnumPokemonType.BUG);
    	EnumSetPokemonType enumSetPokemonType2 = new EnumSetPokemonType(EnumPokemonType.FIRE);
    	
    	Map<EnumPokemonType, Double> typeRatio = new EnumMap<>(EnumPokemonType.class);
        typeRatio.put(EnumPokemonType.STEEL, 1.0);
        typeRatio.put(EnumPokemonType.FIGHTHING, 0.5);
        typeRatio.put(EnumPokemonType.DRAGON, 1.0);
        typeRatio.put(EnumPokemonType.WATER, 2.0);
        typeRatio.put(EnumPokemonType.ELECTRIC, 0.0);
        typeRatio.put(EnumPokemonType.FAIRY, 1.0);
        typeRatio.put(EnumPokemonType.FIRE, 2.0);
        typeRatio.put(EnumPokemonType.ICE, 2.0);
        typeRatio.put(EnumPokemonType.BUG, 1.0);
        typeRatio.put(EnumPokemonType.NORMAL, 1.0);
        typeRatio.put(EnumPokemonType.GRASS, 1.0);
        typeRatio.put(EnumPokemonType.POISON, 0.5);
        typeRatio.put(EnumPokemonType.PSYCHIC, 1.0);
        typeRatio.put(EnumPokemonType.ROCK, 1.0);
        typeRatio.put(EnumPokemonType.GROUND, 0.5);
        typeRatio.put(EnumPokemonType.GHOST, 1.0);
        typeRatio.put(EnumPokemonType.DARK, 1.0);
        typeRatio.put(EnumPokemonType.FLYING, 2.0);
        
    	PokemonType pokemonType1 = new PokemonType(enumSetPokemonType1);
    	PokemonType pokemonType2 = new PokemonType(enumSetPokemonType2);
    	
    	PokemonSpecie specie1 = new PokemonSpecie(290, "nincada", pokemonType1 , 5.5, 0.5, "img/290.png");
    	PokemonSpecie specie2 = new PokemonSpecie(4, "charmander", pokemonType2 , 6.0, 85.0, "img/004.png");
    	//creazione moveset
    	Move move1 = new Move("sketch", 100, 20, 100, CategoryMove.PHYSICAL, EnumPokemonType.NORMAL);
    	Move move2 = new Move("heat", 50, 25, 100, CategoryMove.SPECIAL, EnumPokemonType.FIRE);
    	
    	Move[] moves1 = {move1};
    	Move[] moves2 = {move2};
    	
    	//creazione stats
    	Stats stats1 = new Stats(141, 89, 91, 238, 167, 65);
    	Stats stats2 = new Stats(90, 100, 95, 196, 143, 55);
    	
    	pokemon1 = new Pokemon(specie1, moves1, stats1);
    	pokemon2 = new Pokemon(specie2, moves2, stats2);

        pokemons = new Pokemon[]{pokemon1, pokemon2};
        trainer = new Trainer("Ash", pokemons);
    }

    @Test
    void testConstructor() {
        assertNotNull(trainer);
        assertEquals("Ash", trainer.getName());
        assertEquals(2, trainer.teamSize());
    }

    @Test
    void testGetPokemon() {
        assertEquals(pokemon1, trainer.getPokemon());
        assertEquals(pokemon2, trainer.getPokemon(1));
    }

    @Test
    void testTeamSize() {
        assertEquals(2, trainer.teamSize());
    }

    @Test
    void testChangePokemon() {
        trainer.changePokemon(1);
        assertEquals(pokemon2, trainer.getPokemon());
    }

    @Test
    void testIsPokemonAlive() {
        // Assuming both Pokémon are alive at the start
        assertTrue(trainer.isPokemonAlive(0));
        assertTrue(trainer.isPokemonAlive(1));
    }

    @Test
    void testRestoreTrainer() {
        pokemon1.restore();
        pokemon2.restore();
        trainer.restoreTrainer();

        assertTrue(trainer.isPokemonAlive(0));
        assertTrue(trainer.isPokemonAlive(1));
    }

    @Test
    void testLoose() {
        // Simulate defeated Pokémon
        pokemon1.takeDmg(1000);
        pokemon2.takeDmg(1000);
        assertTrue(trainer.loose());
    }

    @Test
    void testOnlyOnePokemonAlive() {
        // Simulates a single live Pokémon
    	pokemon1.takeDmg(1000);
        assertTrue(trainer.onlyOnePokemonAlive());
    }
}

