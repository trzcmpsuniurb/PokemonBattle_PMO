package model.pokemon;

import java.io.Serializable;
import model.move.Move;
import model.specie.PokemonSpecie;
import model.stats.Stats;

public interface IPokemon extends Serializable {

    /**
     * Returns the Pokemon's nickname.
     * @return the Pokemon's nickname
     */
    String getNickName();

    /**
     * Returns the Pokemon's species.
     * @return the Pokemon's species
     */
    PokemonSpecie getSpecie();

    /**
     * Returns the Pokemon's abilities.
     * @return an array of abilities
     */
    Move[] getMoves();

    /**
     * Returns the Pokemon's stats.
     * @return the Pokemon's stats
     */
    Stats getStat();

    /**
     * Checks if the Pokemon is alive.
     * @return true if the Pokemon is alive, false otherwise
     */
    boolean isAlive();

    /**
     * Deals damage to the Pokemon.
     * @param i the amount of damage
     * @return true if the Pokemon is still alive, false otherwise
     */
    boolean takeDmg(int i);

    /**
     * Restores the Pokemon's health points.
     */
    void restore();

    /**
     * Checks if the Pokemon can still fight.
     * @return true if the Pokemon can no longer fight, false otherwise
     */
    boolean noMoreFight();

    /**
     * Deals damage to the Pokemon using an ability.
     * @param capUsed the ability used
     * @return true if the Pokemon is still alive after taking the damage, false otherwise
     */
    // boolean takeDmgCap(Move capUsed);
}

