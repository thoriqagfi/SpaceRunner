package model;

import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class ShipPicker extends VBox {
  private ImageView circleImage;
  private ImageView shipImage;

  private String circleNotChoosen = "file:model/resources/grey_cirlce.png";
  private String circleChoosen = "model/resources/missile.png";

  private Ship ship;
  private boolean isCircleChoosen;

  public ShipPicker(Ship ship){
    this.ship = ship;
    circleImage = new ImageView(circleNotChoosen);
    shipImage = new ImageView(ship.getUrl());
    isCircleChoosen = false;
    this.setAlignment(Pos.CENTER);
    this.setSpacing(10);
    this.getChildren().add(circleImage);
    this.getChildren().add(shipImage);
  }

  public Ship getShip() {
    return ship;
  }
  public boolean getIsCircleChosen() {
    return isCircleChoosen;
  }

  public void setCircleChoosen(boolean isCircleChoosen) {
    this.isCircleChoosen = isCircleChoosen;
    String imageToSet = isCircleChoosen ? circleChoosen : circleNotChoosen;
    circleImage.setImage(new Image(imageToSet));
  }
}
