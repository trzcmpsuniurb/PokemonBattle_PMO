package controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.fight.League;
import view.MainView;

public class LeagueController {

	@FXML
	private BorderPane root;

	private League league;

	private static int lastFightIndex = 0;
	

	private boolean mainMenu=true;

	/**
	 * Function for initilaize the component
	 */
	public void initialize() {
		if (GameControllerStatic.getGameControllerStatic().isLeagueison()) {

			mainMenu=false;
			lauchLeague(null);
		}
	}

	/**
	 * Create the interface for fight the Trainer on the league
	 * @param event
	 */
	@FXML
	void lauchLeague(ActionEvent event) {
		if (!GameControllerStatic.getGameControllerStatic().isLeagueison()) {
			try {
				league = League.createDefaultLeague();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			GameControllerStatic.getGameControllerStatic().setLeagueison(true);
			GameControllerStatic.getGameControllerStatic().setLeague(league);
		} else {
			league = GameControllerStatic.getGameControllerStatic().getLeague();
		}

		HBox hBox = new HBox();
		int i = 0;
		int j = league.getActualBot();
		if (lastFightIndex == j) {
			league.getBot(j).restoreTrainer();
		}
		lastFightIndex = j;
		for (var trainer : league.getBots()) {

			BotButton button = new BotButton(trainer);
			if (i != j) {
				button.setDisable(true);
			}
			button.setOnAction(this.event);
			hBox.getChildren().add(button);
			i++;
		}

		root.setCenter(hBox);
	}
	
	/**
	 * Launch the fight with current trainer
	 */
	private EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {

		public void handle(ActionEvent e) {

			GameControllerStatic.getGameControllerStatic().setBot(((BotButton) e.getSource()).getTrainer());

			try {
				MainView.changeScene((Stage) root.getScene().getWindow(), "Fight.fxml");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};

	/**
	 * Redirect to the personnal league
	 * @param event
	 */
	@FXML
	void createPersonnalLeague(ActionEvent event) {
		try {
			MainView.changeScene((Stage) root.getScene().getWindow(), "PersonalLeague.fxml");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	/**
	 * Create the interface for launch the previous created league
	 * @param event
	 */
	@FXML
	void lauchPersonnalLeague(ActionEvent event) {

		HBox hBox = new HBox();
		String league = "League";
		File dir = new File("League");
		// Once have the appropriate path, you can iterate through its contents
		if (dir.isDirectory()) {
			String s[] = dir.list();
			for (int i = 0; i < s.length; i++) {		
				File f = new File(league + s[i]);
				if (f != null) {
					String filename = f.getName();
					int j = filename.lastIndexOf('.');
					if (i >= 0 && i < filename.length() - 1) {
						if(filename.substring(j + 1).toLowerCase().equals("league")) {
							LeagueButton button = new LeagueButton(s[i]);
							button.setOnAction(eventUnsave);
							hBox.getChildren().add(button);
						}
					}

				}

			}
		}

		root.setCenter(hBox);		
	}
	
	/**
	 * Function for launch and charge a save league
	 */
	private EventHandler<ActionEvent> eventUnsave = new EventHandler<ActionEvent>() {

		public void handle(ActionEvent event) {
			String fileString=((LeagueButton)event.getSource()).getLeagueNameString();
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(new FileInputStream("League/"+fileString));

				GameControllerStatic.getGameControllerStatic().setLeague((League) ois.readObject());

				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			GameControllerStatic.getGameControllerStatic().setLeagueison(true);
			initialize();
		}
	};

	/**
	 * Function for return to the menu of the league or on the main menu
	 * @param event
	 */
	@FXML
	void Menu(ActionEvent event) {
		GameControllerStatic.getGameControllerStatic().setLeagueison(false);
		try {
			if(mainMenu) {

				MainView.changeScene((Stage) root.getScene().getWindow(), "interface.fxml");
			}else
				MainView.changeScene((Stage) root.getScene().getWindow(), "League.fxml");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

