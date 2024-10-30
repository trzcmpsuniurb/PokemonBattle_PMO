package model.trainer;

import java.io.Serializable;

import model.pokemon.Pokemon;
public interface ITrainer extends Serializable {

    /**
     * Returns the currently active Pokemon.
     * @return the current Pokemon
     */
    Pokemon getPokemon();

    /**
     * Returns the Pokemon at the specified position.
     * @param i the index of the Pokemon in the team
     * @return the Pokemon at the specified position
     */
    Pokemon getPokemon(int i);

    /**
     * Returns the size of the Pokemon team.
     * @return the team size
     */
    int teamSize();

    /**
     * Checks if the Pokemon at the specified position is still alive.
     * @param i the index of the Pokemon
     * @return true if the Pokemon is alive, false otherwise
     */
    boolean isPokemonAlive(int i);

    /**
     * Switches the current Pokemon with another Pokemon.
     * @param i the index of the new Pokemon in the team
     * @return the newly selected Pokemon
     */
    Pokemon changePokemon(int i);

    /**
     * Restores the health of all Pokemon in the Trainer's team.
     */
    void restoreTrainer();

    /**
     * Checks if the Trainer has lost the game.
     * @return true if the Trainer has lost, false otherwise
     */
    boolean loose();

    /**
     * Checks if there is only one Pokemon still alive in the team.
     * @return true if only one Pokemon is alive, false otherwise
     */
    boolean onlyOnePokemonAlive();

    /**
     * Returns the name of the Trainer.
     * @return the Trainer's name
     */
    String getName();
}
