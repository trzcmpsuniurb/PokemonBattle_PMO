package model.pokedex;

import java.io.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import model.enums.*;
import model.specie.PokemonSpecie;
import model.type.PokemonType;

public class Pokedex implements IPokedex{
	
	private static Pokedex pokedex;
	private static final HashMap<Integer, PokemonSpecie> pokedexMap= new HashMap<Integer, PokemonSpecie>();
	
	public Pokedex getPokedex() throws IOException {
		if (pokedex == null)
			pokedex = generate();
		return pokedex;
	}
	
	/**
	 * Function who generate the pokedex with all pokemons from an CVS File
	 * @return MoveDeck
	 * @throws IOException
	 */
	private static Pokedex generate() throws IOException{
		if(pokedex!=null) {
			return pokedex;
		}
		pokedex = new Pokedex();
		
		FileReader fReader = new FileReader(new File("CSV/pokedex.csv"));
		
		int i;
		int nbLine=0;
		String line;
		StringBuilder lineBuilder= new StringBuilder();
		while ((i=fReader.read())!=-1) {
			char c = (char)i;
			if(c == '\n') {
				line=lineBuilder.toString().trim();
				
				if(nbLine>0) {
					String[] tab =line.split("[,;]");
					PokemonSpecie specie;
					//If the Pokemon have 2 types
					if(tab.length==6) {						
						specie=new PokemonSpecie(Integer.parseInt(tab[0]), tab[1],PokemonType.getPokemonType(new EnumSetPokemonType(EnumPokemonType.fromString(tab[5]))), Double.parseDouble(tab[3])/10, Double.parseDouble(tab[4])/10,tab[2]);
					}
					else {
						specie=new PokemonSpecie(Integer.parseInt(tab[0]), tab[1], PokemonType.getPokemonType(new EnumSetPokemonType(EnumPokemonType.fromString(tab[5]),EnumPokemonType.fromString(tab[6]))), Double.parseDouble(tab[3])/10, Double.parseDouble(tab[4])/10,tab[2]);
					}
					
					if(!pokedexMap.containsKey(specie.getNbPokemon())) {
						pokedexMap.put(specie.getNbPokemon(), specie);
					}
				
					lineBuilder=new StringBuilder();
				}else {
					nbLine++;
					lineBuilder=new StringBuilder();
				}
					
				
			}
			else {
				lineBuilder.append(c);
			}
		}
		return (pokedex);
	}
	
	public PokemonSpecie get(Integer index) {
		return pokedexMap.get(index);
	}
	
	@Override
	public String toString() {
		StringBuilder builder=new StringBuilder();
		builder.append("Pokedex:\n");
		for (PokemonSpecie pokemonSpecie : pokedexMap.values()) {
			builder.append(pokemonSpecie+"\n");
		}
		return builder.toString();
	}

	public int size() {
		// TODO Auto-generated method stub
		return pokedexMap.size();
	}
	
}
