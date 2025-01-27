package com.example.meierle_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameMenu extends Application {
    Pane pane;
    List<Player> players;
    int screenMaxWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
    int screenMaxHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();
    Stage primaryStage;

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        pane = new Pane();
        pane.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        Scene scene = new Scene(pane, Screen.getPrimary().getVisualBounds().getWidth(), Screen.getPrimary().getVisualBounds().getHeight());
        stage.setTitle("Meierle");
        stage.setFullScreen(true);
        stage.fullScreenExitHintProperty().setValue("");
        stage.setScene(scene);
        stage.show();
        enterPlayerNames();
    }

    private void enterPlayerNames() {
        players = new ArrayList<>();
        pane.getChildren().clear();
        Label label = new Label("Name des Spielers eingeben:");
        label.setLayoutX((double) screenMaxWidth /2 - 100);
        label.setLayoutY((double) screenMaxHeight /2 - 30);
        pane.getChildren().add(label);
        TextField textField = createTextField();
        Button newPlayerButton = createNewPlayerButton();
        newPlayerButton.setOnAction(e -> {
            if(!textField.getText().isEmpty()) {
                players.add(new Player(textField.getText()));
                textField.setText("");
            }
        });
        Button startGameButton = createStartButton();
        Button cancelButton = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 100, Allrounder.screenMaxHeight / 2 + 180, "Verlassen");
        cancelButton.setOnAction(e -> {
            primaryStage.close();
        });
        pane.getChildren().addAll(newPlayerButton, startGameButton, textField, cancelButton);
    }

    private TextField createTextField() {
        TextField textField = new TextField();
        textField.setLayoutX((double) screenMaxWidth /2 - 100);
        textField.setLayoutY((double) screenMaxHeight /2 - 10);
        textField.setPrefWidth(200);
        textField.setPrefHeight(20);
        return textField;
    }


    private Button createStartButton() {
        Button startGameButton = Allrounder.createDefaultButton((double) screenMaxWidth / 2 + 50, (double) screenMaxHeight / 2 + 20, "Start Game");
        startGameButton.setOnAction(e -> {
            if(players.size() > 1) {
                Game game = new Game(pane, players, primaryStage);
                game.startGame();
            }
        });
        return startGameButton;
    }

    private Button createNewPlayerButton() {
        return Allrounder.createDefaultButton(Allrounder.screenMaxWidth /2 - 250, Allrounder.screenMaxHeight /2 + 20, "Neuer Spieler");
    }

    public static void main(String[] args) {
        launch();
    }
}