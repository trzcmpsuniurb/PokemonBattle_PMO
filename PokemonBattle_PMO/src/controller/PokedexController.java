package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.move.Move;
import model.move.MoveDeck;
import model.move.MoveHelper;
import model.pokedex.Pokedex;
import model.pokemon.Pokemon;
import model.specie.PokemonSpecie;
import model.stats.Stats;
import model.trainer.Bot;
import model.trainer.Trainer;
import model.type.PokemonType;
import view.MainView;


public class PokedexController {

	@FXML
	private BorderPane root;

	@FXML
	private GridPane root2;


	private ObservableList<Node> chidreNodes = FXCollections.observableArrayList();

	private ObservableList<RowConstraints> rowContraintsList = FXCollections.observableArrayList();

	private AudioClip music;
	
	private Pokedex pokedex = null;

	private TextField nameFiled;

	private final int column = 6;

	private int row = 0;

	private int choosenPokemon = 0;

	private Pokemon[] pokemons = new Pokemon[column];

	private MoveDeck deck = null;

	
	/**
	 * Function who initialize the Pokedex with the existing pokemon
	 * @throws FileNotFoundException
	 */
	public void initialize() throws FileNotFoundException {
		System.out.println("Launch Pokedex");
		try {
			pokedex = new Pokedex().getPokedex();
			deck = MoveHelper.getMovesDeck();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setMusic();
		int total = pokedex.size();
		row = (int) (total / column);
		int pokemonNumber = 1;
		//for All the row needed to create
		for (int i = 0; i < row; i++) {
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPrefHeight(100.0);
			rowConstraints.setMinHeight(100.0);
			root2.getRowConstraints().add(rowConstraints);
			//Create the row on the grid
			rowContraintsList.add(rowConstraints);
			HBox vBox = new HBox();
			nameFiled = new TextField("Player");
			vBox.getChildren().add(new Label("Trainer's name :  "));
			vBox.getChildren().add(nameFiled);
			root.setTop(vBox);
			//for all the columns of the grid
			for (int j = 0; j < column; j++) {
				//Add the PokemonButton with the image and importante information
				PokemonButton rectangle = new PokemonButton(pokedex.get(pokemonNumber));
				rectangle.setPrefWidth(100.0);
				rectangle.setPrefHeight(100.0);
				rectangle.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
				rectangle.setTop(new Label(pokemonNumber + " " + pokedex.get(pokemonNumber).getNamePokemon()));
				BorderPane.setAlignment(rectangle.getTop(), Pos.CENTER);
				
				ImageView view = new ImageView();
				view.prefHeight(75.0);
				view.prefWidth(75.0);
				view.setFitHeight(75.0);
				view.setFitWidth(75.0);				
				view.setImage(new Image(new FileInputStream("Pictures/" + pokedex.get(pokemonNumber).getImagePath()), 75.0, 75.0, false, false));
				
				rectangle.setCenter(view);
				BorderPane.setAlignment(rectangle.getCenter(), Pos.CENTER);
				rectangle.setOnMouseClicked(event);
				root2.add(rectangle, j, i);
				chidreNodes.add(rectangle);
				pokemonNumber++;

			}
		}

	}

	/**
	 * Set music of the scene
	 * @param 
	 * @return void
	 */
	private void setMusic() {
		String bip = "Musics/pokedexTheme.mp3";
		Media hit = new Media(Paths.get(bip).toUri().toString());
		AudioClip mediaPlayer = new AudioClip(hit.getSource());
		mediaPlayer.setVolume(0.05);
		mediaPlayer.setCycleCount(AudioClip.INDEFINITE);
		mediaPlayer.play();
		music = mediaPlayer;
	}

	private EventHandler<MouseEvent> event = new EventHandler<MouseEvent>() {
		private int capacitychoosen = 0;
		private int capacityMax = 4;
		private Move[] moves = new Move[capacityMax];
		private PokemonSpecie pokemonSpecie;
		private TextField namePokemonField;
		private PokemonButton pokemonButton;

		/**
		 * Function when click on a pokemonButton, for watch more details on
		 */
		public void handle(MouseEvent e) {
			var x = e.getSource();
			if (x instanceof PokemonButton) {
				PokemonButton pokemonButton = (PokemonButton) x;
				this.pokemonButton = pokemonButton;
				pokemonSpecie = pokemonButton.getPokemonSpecie();

				try {
					setPokemonInterface(pokemonSpecie);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
		
		/**
		 * Set the interface for watch the details of the current pokemon
		 * @param pokemonSpecie
		 * @throws FileNotFoundException
		 */
		public void setPokemonInterface(PokemonSpecie pokemonSpecie) throws FileNotFoundException {
			HBox hBox1 = new HBox();
			namePokemonField = new TextField(pokemonSpecie.getNamePokemon());
			hBox1.getChildren().add(new Label("Pokemon's name"));
			hBox1.getChildren().add(namePokemonField);
			root.setTop(hBox1);
			AnchorPane anchorPane = new AnchorPane();
			anchorPane.setPrefWidth(600);
			anchorPane.setPrefHeight(200);
			anchorPane.setMaxHeight(100);
			ImageView view = new ImageView();
			view.setImage(new Image(new FileInputStream("Pictures/" + pokemonSpecie.getImagePath()), 200.0, 150.0,
					false, false));
			AnchorPane.setLeftAnchor(view, 0.0);
			AnchorPane.setTopAnchor(view, 65.0);

			AnchorPane anchorPane3 = new AnchorPane();
			anchorPane3.setPrefWidth(274);
			anchorPane3.setPrefHeight(76);
			AnchorPane.setLeftAnchor(anchorPane3, 286.0);
			AnchorPane.setTopAnchor(anchorPane3, 43.0);
			anchorPane.getChildren().addAll(view, anchorPane3);

			ImageView view2 = new ImageView();
			view2.setImage(new Image(new FileInputStream("Pictures/pokeball.png"), 34.0, 28.0, false, false));
			Label label = new Label(pokemonSpecie.getNbPokemon() +"   "+ pokemonSpecie.getNamePokemon());
			label.setFont(new Font(20));
			AnchorPane.setLeftAnchor(view2, 10.0);
			AnchorPane.setTopAnchor(view2, 10.0);
			AnchorPane.setLeftAnchor(label, 81.0);
			AnchorPane.setTopAnchor(label, 9.0);
			
			BackgroundImage pokedexBackground = new BackgroundImage(new Image(new FileInputStream("Pictures/pokedexBackground.png")),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(250, 75, false, false, false, false));
			
			anchorPane3.setBackground(new Background(pokedexBackground));
			anchorPane3.getChildren().addAll(view2, label);

			ImageView view3 = new ImageView();
			view3.setImage(new Image(
					new FileInputStream("Pictures/types/" + pokemonSpecie.getTypes().getEnumPokemonType(0).toString() + ".png"), 30.0,
					30.0, false, false));
			ImageView view4 = new ImageView();
			if (pokemonSpecie.getTypes().size() == 2) {
				view4.setImage(new Image(
						new FileInputStream("Pictures/types/" + pokemonSpecie.getTypes().getEnumPokemonType(1).toString() + ".png"),
						30.0, 30.0, false, false));

			}

			AnchorPane.setLeftAnchor(view3, 347.0);
			AnchorPane.setTopAnchor(view3, 81.0);
			AnchorPane.setLeftAnchor(view4, 407.0);
			AnchorPane.setTopAnchor(view4, 81.0);
			anchorPane.getChildren().addAll(view3, view4);
			VBox vBox = new VBox();
			vBox.setPrefHeight(69);
			vBox.setPrefWidth(250);
			Label label2 = new Label("Height : " + pokemonSpecie.getSize().getHeight()+"Inch");
			Label label3 = new Label("Weight : " + pokemonSpecie.getSize().getWeight()+".lbs.");
			label2.setFont(new Font(20));
			label3.setFont(new Font(20));
			vBox.getChildren().addAll(label2, label3);
			vBox.setAlignment(Pos.CENTER);
			BackgroundImage pokedexBackground2 = new BackgroundImage(new Image(new FileInputStream("Pictures/pokedexBackground2.png")),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(250, 69, false, false, false, false));
			vBox.setBackground(new Background(pokedexBackground2));
			AnchorPane.setLeftAnchor(vBox, 311.0);
			AnchorPane.setTopAnchor(vBox, 188.0);

			anchorPane.getChildren().addAll(vBox);
			
			root.setCenter(anchorPane);
			BackgroundImage fightMenu = new BackgroundImage(new Image(new FileInputStream("Pictures/fightMenu.png")),
					BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
					new BackgroundSize(600, 100, false, false, false, false));
			HBox hBox = new HBox();
			Button returnButton = new Button("Return");
			Button selectButton = new Button("Select");
			selectButton.setOnMouseClicked(e -> {
				pokemonButton.setBorder(new Border(
						new BorderStroke(Color.RED, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));

				try {
					updateRoot(pokemonSpecie.getTypes());
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			returnButton.setOnMouseClicked(event -> root.setCenter(root2));
			returnButton.setPrefHeight(75);
			selectButton.setPrefHeight(75);
			returnButton.setPrefWidth(75);
			selectButton.setPrefWidth(75);
			hBox.setBackground(new Background(fightMenu));
			hBox.getChildren().addAll(returnButton, selectButton);
			hBox.setPrefHeight(100);
			hBox.setPrefWidth(600);
			hBox.setAlignment(Pos.CENTER);
			hBox.setSpacing(10);
			root.setBottom(hBox);
			AnchorPane.setLeftAnchor(hBox, 0.0);
			AnchorPane.setTopAnchor(hBox, 300.0);
		

		}

		public void updateRoot(PokemonType type) throws FileNotFoundException {
			var set = deck.get(type);

			root2.getChildren().clear();
			root2.getRowConstraints().clear();

			root.setTop(null);
			root.setBottom(null);
			root.setCenter(root2);
			int k = 0;
			int i, j;
			for (Move move : set) {
				i = k / column;
				j = k % column;
				if (j == 0) {
					RowConstraints rowConstraints = new RowConstraints();
					rowConstraints.setPrefHeight(100.0);
					rowConstraints.setMinHeight(100.0);
					root2.getRowConstraints().add(rowConstraints);
				}
				MoveButton rectangle = new MoveButton(move);
				rectangle.setPrefWidth(
						100.0 - ((BorderWidths.DEFAULT.getLeft() + BorderWidths.DEFAULT.getRight()) * column));
				rectangle.setPrefHeight(100.0);
				rectangle.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,
						BorderWidths.DEFAULT)));

				rectangle.setTop(new Label(move.getName()));
				BorderPane vBox2 = new BorderPane();
				vBox2.setRight(new Label("Pow:" + move.getPower() + ""));
				vBox2.setLeft(new Label("PP:" + move.getMaxPowerPoint() + ""));

				rectangle.setBottom(vBox2);
				BorderPane.setAlignment(rectangle.getTop(), Pos.CENTER);
				ImageView view = new ImageView();
				view.prefHeight(50);
				view.prefWidth(50.0);
				view.setFitHeight(50);
				view.setFitWidth(50.0);

				view.setImage(new Image(new FileInputStream("Pictures/types/" + move.getType().toString() + ".png"),
						50.0, 50.0, false, false));
				rectangle.setCenter(view);

				BorderPane.setAlignment(rectangle.getCenter(), Pos.CENTER);
				rectangle.setOnMouseClicked(event2);
				root2.add(rectangle, j, i);
				k++;
			}

		}

		private EventHandler<MouseEvent> event2 = new EventHandler<MouseEvent>() {
			/**
			 * Function for create the interface with all the capcities of the current pokemon 
			 */
			public void handle(MouseEvent e) {
				var x = e.getSource();
				if (x instanceof MoveButton) {
					MoveButton pokemonButton = (MoveButton) x;
					boolean newMove = true;
					for (int i = 0; i < moves.length; i++) {
						if (moves[i] == pokemonButton.getCapacity()) {
							newMove = false;
						}
					}
					if (newMove) {
						pokemonButton.setBorder(new Border(new BorderStroke(Color.RED, BorderStrokeStyle.SOLID,
								CornerRadii.EMPTY, new BorderWidths(4))));
						moves[capacitychoosen] = pokemonButton.getCapacity();
						capacitychoosen++;
						//If the number of capcity chosen is the max -> return to the pokemon interface
						if (capacitychoosen == capacityMax) {
							root2.getChildren().clear();
							root2.getChildren().addAll(chidreNodes);
							root2.getRowConstraints().clear();
							root2.getRowConstraints().addAll(rowContraintsList);
							pokemons[choosenPokemon] = new Pokemon(pokemonSpecie, moves,
									Stats.RandomStat(), namePokemonField.getText());
							choosenPokemon++;
							moves = new Move[capacityMax];
							capacitychoosen = 0;

							//If we choose all pokmeon we are redirect to an other interface
							if (choosenPokemon == column) {

								try {

									if (GameControllerStatic.getGameControllerStatic().isCreateLeagueOn()) {
										GameControllerStatic.getGameControllerStatic().setBot(new Bot(nameFiled.getText(), pokemons));
										MainView.changeScene((Stage) root2.getScene().getWindow(),
												"PersonalLeague.fxml");
									} else {
										GameControllerStatic.getGameControllerStatic().setTrainer(new Trainer(nameFiled.getText(), pokemons));

										MainView.changeScene((Stage) root2.getScene().getWindow(), "interface.fxml");
										GameControllerStatic.Save();

									}
									
									music.stop();
								
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}

							}
						}
					}

				}
			}
		};
	};
}
