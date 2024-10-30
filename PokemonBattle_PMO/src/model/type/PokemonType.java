	package model.type;

import java.io.*;
import java.util.*;

import model.enums.*;
import model.specie.PokemonSpecie;


public class PokemonType implements IType,Iterator<EnumPokemonType>, Serializable {

	private static final long serialVersionUID = 1L;
	private EnumSetPokemonType enumPokemonTypes=null;
	private Map<EnumPokemonType, Double> typeRatio = new EnumMap<EnumPokemonType, Double>(EnumPokemonType.class);
	private static Map<EnumSetPokemonType, PokemonType> arrayToType = new HashMap<EnumSetPokemonType, PokemonType>();

	public PokemonType(EnumSetPokemonType enumPokemonTypes) {
		super();
		this.enumPokemonTypes = enumPokemonTypes;
	}

	/**
	 * This function return to the user a PokemonType thanks to the generatePokemonType() winch create the differentes pokemontypes
	 * @param enumPokemonTypes the set of the different EnumPokemonType of ur pokemon
	 * @return PokemonType
	 */
	
	public static PokemonType getPokemonType(EnumSetPokemonType enumPokemonTypes) {
		return PokemonType.arrayToType.get(enumPokemonTypes);
	}

	/**
	 * Function that generates all types of pokemon with their strengths and weaknesses from the Grid_Types.csv file.
	 * 
	 * @throws IOException
	 */
	public static void generatePokemonType() throws IOException {
		ArrayList<PokemonSpecie> pokemonSpecies = new ArrayList<PokemonSpecie>();//Create a list of specie

		FileReader fReader = new FileReader(new File("CSV/grid_types.csv"));

		int i;
		String line;
		StringBuilder lineBuilder = new StringBuilder();
		int nbline = 0;
		ArrayList<EnumPokemonType> enumPokemonTypesList = new ArrayList<EnumPokemonType>();
		while ((i = fReader.read()) != -1) {//Read the file while it have character
			char c = (char) i;
			if (c == '\n') {
				line = lineBuilder.toString();
				nbline++;
				String[] tab = line.split("[,;]");
				//If we are on the first line we can add the differente PokemonType of Ratio we will see
				if (nbline == 1) {
					for (int j = 2; j < tab.length; j++) {
						enumPokemonTypesList.add(EnumPokemonType.fromString(tab[j]));
					}
				} else {
					EnumSetPokemonType enumPokemonTypesSet;
					//If we wtach the ratio of pokemon with 2 or 1 type
					if(tab[1].isBlank()) {
						enumPokemonTypesSet = new EnumSetPokemonType(EnumPokemonType.fromString(tab[0])); 
						
					}else
						enumPokemonTypesSet = new EnumSetPokemonType(EnumPokemonType.fromString(tab[0]),EnumPokemonType.fromString(tab[1])); 
					PokemonType pokemonType = new PokemonType(enumPokemonTypesSet);
					//We add the diff�rente ratio of the pokemonType
					for (int j = 2; j < tab.length; j++) {
						if(enumPokemonTypesList.get(j - 2)!=null)
							pokemonType.typeRatio.put(enumPokemonTypesList.get(j - 2), Double.valueOf(tab[j]));
					}
					//We add the pokemonType link to his EnumPokemonTypesSet
					PokemonType.arrayToType.put(enumPokemonTypesSet, pokemonType);
				}

				lineBuilder = new StringBuilder();

			} else {
				lineBuilder.append(c);
			}
		}
	}

	@Override
	public String toString() {
		return "PokemonType [enumPokemonTypes=" + enumPokemonTypes + ", typeRatio=" + typeRatio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((enumPokemonTypes == null) ? 0 : enumPokemonTypes.hashCode());
		result = prime * result + ((typeRatio == null) ? 0 : typeRatio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PokemonType other = (PokemonType) obj;
		if (enumPokemonTypes == null) {
			if (other.enumPokemonTypes != null)
				return false;
		} else if (!enumPokemonTypes.equals(other.enumPokemonTypes))
			return false;
		if (typeRatio == null) {
			if (other.typeRatio != null)
				return false;
		} else if (!typeRatio.equals(other.typeRatio))
			return false;
		return true;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		
		return enumPokemonTypes.hasNext();
	}

	@Override
	public EnumPokemonType next() {
		// TODO Auto-generated method stub
		
		return enumPokemonTypes.next();
	}
	
	/**
	 * Reset the Iterator of the class for reIterate on
	 *
	 */
	public void resetIterator() {
		enumPokemonTypes.resetIterator();
		
	}
	
	public Double getRatio(EnumPokemonType e) {
		return typeRatio.get(e);
	}
	
	/**
	 * 
	 * @return the size of the enumPokemonTypes
	 */
	public int size() {
		return enumPokemonTypes.Size();
	}
	
	/**
	 * 
	 * @return the EnumPokemonType of the PokemonType
	 */
	public EnumPokemonType getEnumPokemonType(int index) {
		return enumPokemonTypes.get(index);
	}
	
	/**
	 * 
	 * @return the EnumSetPokemonType of the PokemonType
	 */
	public EnumSetPokemonType getEnumSetPokemonType() {
		return enumPokemonTypes;
	}

}
