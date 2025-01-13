package com.example.meierle_3;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class Game {

    List<Player> players;
    Pane pane;
    DiceCup diceCup = new DiceCup();
    int lastDiceCupNumber = 0;


    public Game(Pane pane, List<Player> players) {
        this.pane = pane;
        this.players = players;
    }

    public void startGame() {
        pane.getChildren().clear();
        addRollDiceButton();
        addPlayerLabel();
        addRaiseCupButton();
        addRaiseWithoutRollCupButton();
    }

    public void round() {

    }

    private void addPlayerLabel() {
        int i = 0;
        for (Player player : players) {
            Label label = new Label();
            label.setLayoutX(100);
            label.setLayoutY(100 + i * 20);
            label.setText(player.name);
            label.setFont(new javafx.scene.text.Font(20));
            pane.getChildren().add(label);
            i++;
        }
    }

    private void addRollDiceButton() {
        Button button = Allrounder.createDefaultButton(Allrounder.screenMaxWidth/ 2 - 100, Allrounder.screenMaxHeight/2 + 90, "Rolle Würfel");
        button.setOnAction(e -> {
            diceCup.roll();
            addRollNumberLabel();
        });
        pane.getChildren().add(button);
    }

    private void addRollNumberLabel() {
        Label label = new Label();
        label.setLayoutY((double) Allrounder.screenMaxHeight /2 - 10);
        label.setFont(new javafx.scene.text.Font(20));
        label.setText("Deine gewürfelte Zahl ist: " + diceCup.getNumber());
        label.setLayoutX((double) Allrounder.screenMaxWidth /2 - label.getText().length() * 5);
        pane.getChildren().add(label);
    }

    private void addRaiseCupButton() {
        Button button = Allrounder.createDefaultButton(Allrounder.screenMaxWidth/2 - 100, Allrounder.screenMaxHeight/2 + 120, "Becher heben");
        button.setOnAction(e -> {

        });
        pane.getChildren().add(button);
    }

    private void addRaiseWithoutRollCupButton() {
        Button button = Allrounder.createDefaultButton(Allrounder.screenMaxWidth/2 - 100, Allrounder.screenMaxHeight/2 + 120, "Erhöhen ohne Schütteln");
        button.setOnAction(e -> {

        });
        pane.getChildren().add(button);
    }
}
