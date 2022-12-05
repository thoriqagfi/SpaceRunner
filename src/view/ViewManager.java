package view;


import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.InfoLabel;
import model.Ship;
import model.ShipPicker;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubscene;


public class ViewManager {
	private static final int HEIGHT = 768;
	private static final int WIDTH = 1024;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	private SpaceRunnerSubscene creditsSubscene;
	private SpaceRunnerSubscene helpSubscene;
	private SpaceRunnerSubscene scoreSubscene;
	private SpaceRunnerSubscene ShipChooserSubscene;
	private SpaceRunnerSubscene sceneToHide;
	List<SpaceRunnerButton> menuButtons;
	
	List<ShipPicker> ShipsList;
	private Ship choosenShip;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT );
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createSubScenes();
		CreateButtons();
		createBackground();
		createLogo();

	}

	private void showSubScene(SpaceRunnerSubscene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}

		subScene.moveSubScene();
		sceneToHide = subScene;
	}

	private void createSubScenes() {
		creditsSubscene = new SpaceRunnerSubscene();
		mainPane.getChildren().add(creditsSubscene);
		helpSubscene = new SpaceRunnerSubscene();
		mainPane.getChildren().add(helpSubscene);
		scoreSubscene = new SpaceRunnerSubscene();
		mainPane.getChildren().add(scoreSubscene);

		createShipChooserSubScene();
	}
	private void createShipChooserSubScene() {
		ShipChooserSubscene = new SpaceRunnerSubscene();
		mainPane.getChildren().add(ShipChooserSubscene);
		InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR Ship");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		ShipChooserSubscene.getPane().getChildren().add(chooseShipLabel);
		ShipChooserSubscene.getPane().getChildren().add(createShipsToChoose());
		ShipChooserSubscene.getPane().getChildren().add(createButtonToStart());
	}
	
	private HBox createShipsToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		ShipsList = new ArrayList<>();
		for (Ship Ship : Ship.values()) {
			ShipPicker ShipToPick = new ShipPicker(Ship);
			ShipsList.add(ShipToPick);
			box.getChildren().add(ShipToPick);
			ShipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for (ShipPicker Ship : ShipsList) {
						Ship.setCircleChoosen(false);
					}
					ShipToPick.setCircleChoosen(true);
					choosenShip = ShipToPick.getShip();
				}
			});
		}
		box.setLayoutX(300 - (118*2));
		box.setLayoutY(100);
		return box;
	}
	private SpaceRunnerButton createButtonToStart() {
		SpaceRunnerButton startButton = new SpaceRunnerButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(300);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (choosenShip != null) {
					GameViewManager gameManager = new GameViewManager();
					gameManager.createNewGame(mainStage, choosenShip);;
				}
			}
		});
		return startButton;
	}

	public Stage getMainStage() {
		return mainStage;
	}
	private void AddMenuButtons(SpaceRunnerButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	private void CreateButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	private void createStartButton() {
		SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
		AddMenuButtons(startButton);
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(ShipChooserSubscene);
			}
		});
	}
	private void createScoresButton() {
		SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
		AddMenuButtons(scoresButton);
		scoresButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubscene);
			}
		});
	}
	private void createHelpButton() {
		SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
		AddMenuButtons(helpButton);
		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubscene);
			}
		});
	}
	private void createCreditsButton() {
		SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
		AddMenuButtons(creditsButton);
		creditsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubscene);
			}
		});
	}
	private void createExitButton() {
		SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
		AddMenuButtons(exitButton);
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
	}
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/blue.png", 256, 256, false, false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	private void createLogo() {
		ImageView logo = new ImageView("view/resources/space_runner.png");
		logo.setLayoutX(380);
		logo.setLayoutY(50);
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
			}
		});
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
			}
		});
		mainPane.getChildren().add(logo);
	}
}