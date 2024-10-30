package model.pokedex;

import java.io.IOException;

import model.specie.PokemonSpecie;

public interface IPokedex {
	/**
	 * Function who generate the pokedex with all pokemons from an CVS File
	 * @return CapacityDeck
	 * @throws IOException
	 */
	Pokedex getPokedex() throws IOException;
	
	/**
	 *
	 * @return the specie of the pokemon by index passed
	 */
	PokemonSpecie get(Integer index);
}
