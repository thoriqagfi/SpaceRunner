package model;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.Background;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.File;

public class SmallInfoLabel extends Label {
  private static final String FONT_PATH = "src/model/resources/kenvector_future.ttf";

  public SmallInfoLabel(String text) {
    setPrefWidth(130);
    setPrefHeight(50);
    BackgroundImage bgImg = new BackgroundImage(new Image("/view/resources/buttonBlue.png", 130, 50, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
    setBackground(new Background(bgImg));
    setAlignment(Pos.CENTER_LEFT);
    setPadding(new Insets(10,10,10,10));
    setLabelFont();
    setText(text);
  }

  private void setLabelFont() {
    try {
      setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 10));
    } catch (FileNotFoundException e) {
      System.out.println("Font file not found. Using default font \"Verdana\".");
      setFont(Font.font("Verdana", 15));
    }
  }
}
