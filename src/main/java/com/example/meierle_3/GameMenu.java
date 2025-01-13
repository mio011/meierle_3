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

    @Override
    public void start(Stage stage) {
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
            players.add(new Player(textField.getText()));
            textField.setText("");
        });
        Button startGameButton = createStartButton();
        pane.getChildren().addAll(newPlayerButton, startGameButton, textField);
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
        Button startGameButton = Allrounder.createDefaultButton(screenMaxWidth / 2 + 50, screenMaxHeight / 2 + 20, "Start Game");
        startGameButton.setOnAction(e -> {
            Game game = new Game(pane, players);
            game.startGame();
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