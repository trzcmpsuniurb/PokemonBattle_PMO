package model.specie;

import model.size.Size;
import model.type.PokemonType;

public interface ISpecie {
    /**
     * @return specie's number
     */
    int getNbPokemon();
    
    /**
     * @return specie's name
     */
    String getNamePokemon();
    
    /**
     * @return specie's types
     */
    PokemonType getTypes();
    
    /** 
     * @return specie's imagePath
     */
    String getImagePath();
    
    /**
     * @return specie's Size
     */
    Size getSize();
}

