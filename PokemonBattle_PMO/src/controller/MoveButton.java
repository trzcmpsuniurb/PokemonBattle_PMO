package controller;

import javafx.scene.layout.BorderPane;
import model.move.Move;

public class MoveButton extends BorderPane {
	
	private final Move move;

	public MoveButton(Move move) {
		super();
		this.move = move;
	}
	
	public Move getCapacity() {
		return move;
	}
	
}

