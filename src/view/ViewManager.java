package view;

import java.util.List;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubscene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Background;
import javafx.event.EventHandler;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class ViewManager {
  private static final int HEIGHT = 768;
  private static final int WIDTH = 1024;
  private AnchorPane mainPane;
  private Scene mainScene;
  private Stage mainStage;

  private final static int MENU_BUTTONS_START_X = 100;
  private final static int MENU_BUTTONS_START_Y = 150;

  List<SpaceRunnerButton> menuButtons;

  public ViewManager() {
    menuButtons = new ArrayList<>();
    mainPane = new AnchorPane();
    mainScene = new Scene(mainPane, WIDTH, HEIGHT);
    mainStage = new Stage();
    mainStage.setScene(mainScene);
    createButtons();
    createBackground();
    createLogo();
    SpaceRunnerSubscene subScene = new SpaceRunnerSubscene();

    subScene.setLayoutX(200);
    subScene.setLayoutY(100);
    mainPane.getChildren().add(subScene);
  }

  public Stage getMainStage() {
    return mainStage;
  }

  private void addMenuButton(SpaceRunnerButton button) {
    button.setLayoutX(MENU_BUTTONS_START_X);
    button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
    menuButtons.add(button);
    mainPane.getChildren().add(button);
  }

  private void createButtons() {
    createStartButton();
    createScoreButton();
    createHelpButton();
    createCreditsButton();
    createExitButton();
  }

  private void createStartButton() {
    SpaceRunnerButton startButton = new SpaceRunnerButton("PLAY");
    addMenuButton(startButton);
  }

  private void createScoreButton() {
    SpaceRunnerButton scoreButton = new SpaceRunnerButton("SCORES");
    addMenuButton(scoreButton);
  }

  private void createHelpButton() {
    SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
    addMenuButton(helpButton);
  }

  private void createCreditsButton() {
    SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
    addMenuButton(creditsButton);
  }

  private void createExitButton() {
    SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
    addMenuButton(exitButton);
  }

  private void createBackground() {
    Image backgroundImage = new Image("view/resources/blue.png", 256, 256, false, true);
    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
    mainPane.setBackground(new Background(background));
  }

  private void createLogo() {
    ImageView logo = new ImageView("view/resources/space_runner.png");
    logo.setLayoutX(400);
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
