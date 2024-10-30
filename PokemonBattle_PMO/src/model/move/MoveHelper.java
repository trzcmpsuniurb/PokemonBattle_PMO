package model.move;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.enums.EnumPokemonType;

public class MoveHelper {

	private static MoveDeck movesDeck;
	
	public static MoveDeck getMovesDeck() throws IOException {
		if (movesDeck == null)
			movesDeck = generate();
		return movesDeck;
	}
	
	
	/**
	 * Function who generate Moves of pokemons from an CVS File
	 * @return MovesDeck
	 * @throws IOException
	 */
	
    private static MoveDeck generate() throws IOException{
    	//If the deck his already set we return him
    	if(movesDeck!=null) {
    		return movesDeck;
    	}
    	movesDeck = new MoveDeck();

        FileReader fReader = new FileReader(new File("CSV/moves.csv"));
        int i;
        String line;
        StringBuilder lineBuilder= new StringBuilder();
        while ((i=fReader.read())!=-1) {
            char c = (char)i;
            if(c == '\n') {
                line=lineBuilder.toString();

                    String[] tab =line.split("[,;]");
                    Move cap;
                    EnumPokemonType type;
                    try {
                        type= EnumPokemonType.fromString(tab[2]);
                      
                        cap=Move.instance(tab[1], !tab[3].equals("")?Integer.parseInt(tab[3]):0, !tab[4].equals("")?Integer.parseInt(tab[4]):0, !tab[5].equals("")?Integer.parseInt(tab[5]):0, CategoryMove.fromString(tab[6]),type);
                        // create moves with the file and we add or put them to the deck
                        if(movesDeck.containsKey(type)) {
                        	movesDeck.add(type,cap);
                        }else {
                        	movesDeck.put(type, cap);
                        }
                    } catch (Exception e) {
                      
                    }

                lineBuilder=new StringBuilder();

            }
            else {
                lineBuilder.append(c);
            }
        }
        //System.out.println("moveDeck: "+movesDeck);
        return movesDeck;
    }

}
