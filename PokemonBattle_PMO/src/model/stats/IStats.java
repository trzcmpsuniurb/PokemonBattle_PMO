package model.stats;

import java.io.Serializable;
public interface IStats extends Serializable {

    /**
     * Returns a Stats object with random values.
     * @return a Stats object with random statistics.
     */
    static IStats RandomStat() {
        throw new UnsupportedOperationException("RandomStat is not supported in the interface.");
    }

    /**
     * Checks if the Pokemon's HP is zero.
     * @return true if HP is zero, false otherwise.
     */
    boolean noPv();

    /**
     * Returns a string representing the ratio of current HP to max HP.
     * @return string "current HP/max HP".
     */
    String pvOnPvMax();

    /**
     * Returns the ratio of current HP to max HP.
     * @return the current HP/max HP ratio as a double.
     */
    double pvRatio();

    /**
     * Returns the experience level of the Pokemon.
     * @return the experience level.
     */
    int getXpLevel();

    /**
     * Subtracts damage from the Pokemon's HP.
     * @param i the amount of damage to subtract.
     * @return true if HP has dropped to zero or below, false otherwise.
     */
    boolean substractDamage(int i);

    /**
     * Restores the Pokemon's HP to its maximum value.
     */
    void restorePv();

    /**
     * Returns the string representation of the Stats object.
     * @return representation string.
     */
    String toString();

    /**
     * Checks equality between two Stats objects.
     * @param obj the object to compare.
     * @return true if the objects are equal, false otherwise.
     */
    boolean equals(Object obj);

    /**
     * Calculates the hash code of the Stats object.
     * @return the hash code.
     */
    int hashCode();
}
