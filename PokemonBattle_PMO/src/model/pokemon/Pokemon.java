package model.pokemon;

import java.io.Serializable;
import java.util.Arrays;

import model.move.Move;
import model.specie.PokemonSpecie;
import model.stats.Stats;

public class Pokemon implements IPokemon, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final PokemonSpecie specie;
	private final Move[] moves;
	private final Stats stat;
	private final String nickName;
	
	public Pokemon(PokemonSpecie specie, Move[] moves, Stats stat, String nickName) {
		this.nickName=nickName;
		this.specie = specie;
		this.moves = moves;
		this.stat = stat;
	}
	
	public Pokemon(PokemonSpecie specie, Move[] moves, Stats stat) {
		this(specie, moves, stat, specie.getNamePokemon());
	}

	@Override
	public String toString() {
		return "Pokemon [specie=" + specie + ",\n\t moves=" + Arrays.toString(moves) + ",\n\t stat=" + stat
				+ ",\n\t nickname=" + getNickName() + "]";
	}
	
	
	/**
	 * 
	 * @return the name of the pokemon of the pokemon
	 */
	public String getNickName() {
		return nickName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(moves);
		result = prime * result + ((specie == null) ? 0 : specie.hashCode());
		result = prime * result + ((stat == null) ? 0 : stat.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pokemon other = (Pokemon) obj;
		if (!Arrays.equals(moves, other.moves))
			return false;
		if (specie == null) {
			if (other.specie != null)
				return false;
		} else if (!specie.equals(other.specie))
			return false;
		if (stat == null) {
			if (other.stat != null)
				return false;
		} else if (!stat.equals(other.stat))
			return false;
		return true;
	}

	/**
	 * 
	 * @return the specie of the pokemon
	 */
	public PokemonSpecie getSpecie() {
		return specie;
	}

	/**
	 * 
	 * @return the moves of the pokemon
	 */
	public Move[] getMoves() {
		return moves;
	}
	
	/**
	 * 
	 * @return the Stats of the pokemon
	 */
	public Stats getStat() {
		return stat;
	}
	
	/**
	 * Return if the pokemon is alive
	 * @return boolean
	 */
	public boolean isAlive() {
		return !stat.noPv();
	}
	
	/**
	 * Return true if the damage brings HP to zero or less
	 * @param i
	 * @return boolean
	 */
	public boolean takeDmg(int i) {
		return stat.substractDamage(i);
	}
	
	/**
	 * Reset Pv of the pokemon
	 */
	public void restore() {
		stat.restorePv();
		
	}
	
	/**
	 * Say if the pokemon can use some moves or if he can't
	 * @return
	 */
	public boolean noMoreFight() {
		for (Move move : moves) {
			if(move.isUsable()) {
				return false;
			}
		}
		return true;
	}

	public boolean takeDmgCap(Move capUsed) {
		return takeDmg((int)(specie.getTypes().getRatio(capUsed.getType())*capUsed.getPower()));	
	}
	
}

