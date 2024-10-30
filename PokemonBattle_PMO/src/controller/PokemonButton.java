package controller;

import javafx.scene.layout.BorderPane;
import model.specie.PokemonSpecie;

public class PokemonButton extends BorderPane{
	/**
	 * This is a BorderPane who keep in memory a specie
	 */
	private final PokemonSpecie pokemonSpecie;

	public PokemonButton(PokemonSpecie pokemonSpecie) {
		super();
		this.pokemonSpecie = pokemonSpecie;
	}
	
	public PokemonSpecie getPokemonSpecie() {
		return pokemonSpecie;
	}
}
