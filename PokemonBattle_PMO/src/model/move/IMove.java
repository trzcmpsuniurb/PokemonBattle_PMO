package model.move;

import java.io.Serializable;
import model.enums.EnumPokemonType;

public interface IMove extends Serializable {

    /**
     * Returns the Pokemon type of the Move object.
     * @return the EnumPokemonType associated with the move.
     */
    EnumPokemonType getType();

    /**
     * Returns the power of the move.
     * @return the power value.
     */
    int getPower();

    /**
     * Returns the name of the move.
     * @return the name of the move.
     */
    String getName();

    /**
     * Creates an instance of a move, checking if the name has not been used before.
     * @param name the name of the move
     * @param power the power of the move (between 15 and 300 and a multiple of 5)
     * @param powerPoint the power points of the move
     * @param accuracy the accuracy of the move (between 0 and 100)
     * @param categoryMove the category of the move (physical, special, or status)
     * @param type the type of the move
     * @return an instance of Move
     * @throws IllegalArgumentException if the name has already been used or if the parameters are invalid.
     */
    static IMove instance(String name, int power, int powerPoint, int accuracy, CategoryMove categoryMove, EnumPokemonType type) {
        throw new UnsupportedOperationException("instance is not supported in the interface.");
    }

    /**
     * Checks if the move is usable (has PowerPoints greater than 0).
     * @return true if the move is usable, false otherwise.
     */
    boolean isUsable();

    /**
     * Returns the current power points of the move.
     * @return the current PowerPoint value.
     */
    int getPowerPoint();

    /**
     * Returns the maximum number of PowerPoints for the move.
     * @return the maximum PowerPoint value.
     */
    int getMaxPowerPoint();

    /**
     * Subtracts one PowerPoint when the move is used.
     */
    void substractPP();

    /**
     * Returns the string representation of the move.
     * @return a textual representation of the move.
     */
    String toString();

    /**
     * Compares if two moves are equal.
     * @param obj the object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    boolean equals(Object obj);

    /**
     * Returns the hash code of the move.
     * @return the hash code.
     */
    int hashCode();
}
