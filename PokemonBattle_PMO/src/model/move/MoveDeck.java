package model.move;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import model.enums.EnumPokemonType;
import model.type.PokemonType;

public class MoveDeck{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final HashMap<EnumPokemonType,Set<Move>> hashMap =new HashMap<EnumPokemonType, Set<Move>>();

	public void add(EnumPokemonType type, Move cap) {
		var x=hashMap.get(type);
				x.add(cap);	
	}
	
	public boolean containsKey(EnumPokemonType e) {
		return hashMap.containsKey(e);
	}
	
	/**
	 * Put the capcity in link with the specified key
	 * @param key
	 * @param value
	 * @return Set<Move> who correspond to the key asked
	 */
	public Set<Move> put(EnumPokemonType key, Move value) {
		// TODO Auto-generated method stub
		var x=new HashSet<Move>();
		x.add(value);
		return hashMap.put(key, x);
	}
	
	
	/**
	 * Give moves that correspond to the specific pokemonType and also the NormalType
	 * @param types
	 * @return
	 */
	public Set<Move> get(PokemonType types){
		Set<Move> moves=new HashSet<Move>();
		
		while (types.hasNext()) {
			EnumPokemonType type=types.next();
			moves.addAll(hashMap.get(type));
			
		}
		moves.addAll(hashMap.get(EnumPokemonType.NORMAL));
		types.resetIterator();
		return moves;
	}
	
	
	
}
