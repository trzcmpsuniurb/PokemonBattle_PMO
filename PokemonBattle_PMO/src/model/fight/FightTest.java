package model.fight;

import static org.junit.jupiter.api.Assertions.*;

import java.util.EnumMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.enums.EnumPokemonType;
import model.enums.EnumSetPokemonType;
import model.move.CategoryMove;
import model.move.Move;
import model.pokemon.Pokemon;
import model.specie.PokemonSpecie;
import model.stats.Stats;
import model.trainer.Trainer;
import model.type.PokemonType;

class FightTest {

    private Trainer trainer1;
    private Trainer trainer2;
    private Fight fight;
    private Pokemon pokemon1;
    private Pokemon pokemon2;
    private Pokemon[] pokemons1;
    private Pokemon[] pokemons2;

    @BeforeEach
    void setUp() {
    	pokemons1= new Pokemon[1];
    	pokemons2= new Pokemon[1];
    	
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
    	Stats stats = new Stats(141, 89, 91, 238, 167, 65);
    	pokemon1 = new Pokemon(specie1, moves1, stats);
    	pokemon2 = new Pokemon(specie2, moves2, stats);
		    
        trainer1 = new Trainer("Misty", pokemons1);   
        trainer2 = new Trainer("Ash", pokemons2);
        fight = new Fight(trainer1, trainer2);
    }

    @Test
    void testConstructor() {
        assertNotNull(fight.getTrainer1(), "Trainer1 should not be null");
        assertNotNull(fight.getTrainer2(), "Trainer2 should not be null");
        assertEquals(trainer1, fight.getTrainer1(), "Trainer1 should match the initial trainer");
        assertEquals(trainer2, fight.getTrainer2(), "Trainer2 should match the initial trainer");
        assertEquals(trainer1, fight.getCurrentTrainer(), "Current trainer should be trainer1 initially");
        assertEquals(1, fight.getFightPlan(), "Fight plan should be initialized to 1");
    }

    @Test
    void testNextTurn() {
        fight.nextTurn();
        assertEquals(trainer2, fight.getCurrentTrainer(), "After nextTurn, current trainer should switch to trainer2");
        assertEquals(trainer1, fight.getNonCurrentTrainer(), "Non-current trainer should be trainer1");
        
        fight.nextTurn();
        assertEquals(trainer1, fight.getCurrentTrainer(), "After nextTurn again, current trainer should switch back to trainer1");
        assertEquals(trainer2, fight.getNonCurrentTrainer(), "Non-current trainer should be trainer2");
    }

    @Test
    void testSwitchPlan() {
        int initialPlan = fight.getFightPlan();
        
        int switchedPlan = fight.switchPlan();
        assertNotEquals(initialPlan, switchedPlan, "Fight plan should switch to a different value");
        
        switchedPlan = fight.switchPlan();
        assertEquals(initialPlan, switchedPlan, "Switching again should revert to the initial plan");
    }
    
}
