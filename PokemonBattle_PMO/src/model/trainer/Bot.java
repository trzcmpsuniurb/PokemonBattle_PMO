package model.trainer;

import java.util.ArrayList;
import java.util.Random;

import model.move.Move;
import model.pokemon.Pokemon;

public class Bot extends Trainer{
	
	public Bot(String name, Pokemon[] pokemons) {
		super(name, pokemons);
	}
	
	/**
	 * Select a move to play
	 * @param 
	 * @return Move The move used or null if no more usable
	 */
	public Move fight() {
		ArrayList<Move> usableMoves = new ArrayList<Move>();
		for (Move move : getPokemon().getMoves()) {
			if(move!=null && move.isUsable()) {
				usableMoves.add(move);
			}
		}
		
		if(usableMoves.size()==0) {
			return null;
		}
		
		Random random = new Random();
		return usableMoves.get(random.nextInt(usableMoves.size()));
	}
	
	/**
	 * Select a new Pokemon
	 * @param i No impact
	 * @return Pokemon Pokemon selected
	 */
	@Override
	public Pokemon changePokemon(int i) {
		ArrayList<Integer> usablePokemons = new ArrayList<Integer>();
		for (int j = 0; j < teamSize(); j++) {
			if(isPokemonAlive(j)) {
				usablePokemons.add(j);
			}
		}
		
		Random random = new Random();
		return super.changePokemon(usablePokemons.get(random.nextInt(usablePokemons.size())));
	}
}
