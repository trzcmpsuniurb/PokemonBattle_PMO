package model.type;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import model.enums.*;

public interface IType extends Iterator<EnumPokemonType>, Serializable {

    /**
     * Returns the PokemonType associated with the EnumSetPokemonType.
     * @param enumPokemonTypes the EnumPokemonType set for the Pokemon
     * @return the associated PokemonType
     */
    static PokemonType getPokemonType(EnumSetPokemonType enumPokemonTypes) {
        return null;
    }

    /**
     * Generates all Pokemon types with their strengths and weaknesses
     * from the file Grid_Types.csv.
     * @throws IOException
     */
    static void generatePokemonType() throws IOException {}

    /**
     * Returns a string representing the object.
     * @return the representation string
     */
    String toString();

    /**
     * Returns the hash code for the object.
     * @return the hash code
     */
    int hashCode();

    /**
     * Checks for equality with another object.
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
    boolean equals(Object obj);

    /**
     * Checks if there are more elements in the iterator.
     * @return true if there are more elements, false otherwise
     */
    boolean hasNext();

    /**
     * Returns the next element in the iterator.
     * @return the next EnumPokemonType
     */
    EnumPokemonType next();

    /**
     * Resets the iterator to allow a new iteration.
     */
    void resetIterator();

    /**
     * Returns the Pokemon type ratio for a specific EnumPokemonType.
     * @param e the Pokemon type
     * @return the corresponding ratio
     */
    Double getRatio(EnumPokemonType e);

    /**
     * Returns the size of the EnumSetPokemonType.
     * @return the size
     */
    int size();

    /**
     * Returns the EnumPokemonType at the specified index.
     * @param index the index of the element
     * @return the corresponding EnumPokemonType
     */
    EnumPokemonType getEnumPokemonType(int index);
    
    /**
     * Returns the corresponding EnumSetPokemonType.
     * @return EnumSetPokemonType
     */
    EnumSetPokemonType getEnumSetPokemonType();
}
