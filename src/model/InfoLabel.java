package model;

import java.io.File;
import java.io.FileInputStream;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import java.io.FileNotFoundException;
import javafx.geometry.Pos;

public class InfoLabel extends Label {
  public final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
  public final static String BG_IMAGE = "view/resources/yellow_button06.png";

  public InfoLabel(String text) {
    setPrefWidth(380);
    setPrefHeight(50);
    setPadding(new Insets(10,40,40,50));
    setText(text);
    setWrapText(true);
    setLabelFont();
    setAlignment(Pos.CENTER);

    BackgroundImage bgImage = new BackgroundImage(new Image(BG_IMAGE, 380, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		setBackground(new Background(bgImage));
  }

  public void setLabelFont() {
    try {
      setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 23));
    } catch (FileNotFoundException e) {
      setFont(Font.font("Verdana", 23));
    }
  }
}
