package com.example.meierle_3;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Game {

    List<Player> players;
    Pane pane;
    DiceCup diceCup = new DiceCup();
    int activePlayer = 0;
    Stage stage;
    int claimedNumber = 0;


    public Game(Pane pane, List<Player> players, Stage stage) {
        this.pane = pane;
        this.players = players;
        this.stage = stage;
        diceCup.setNumber(0);
    }

    public void startGame() {
        pane.getChildren().clear();
        addStartText();
        addPlayerLabels();
        Button cancelButton = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 100, Allrounder.screenMaxHeight / 2 + 180, "Verlassen");
        cancelButton.setOnAction(e -> {
            GameMenu gameMenu = new GameMenu();
            gameMenu.start(stage);
            stage.setFullScreen(true);
        });
        pane.getChildren().addAll(rollDiceButton(), raiseCupButton(), raiseWithoutRollCupButton(), cancelButton);
    }

    private Button rollDiceButton() {
        Button button = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 100, Allrounder.screenMaxHeight / 2 + 90, "Rolle Würfel");
        button.setOnAction(e -> {
            pane.getChildren().clear();
            addPlayerLabels();
            addStartText();
            diceCup.roll();
            addRollNumberLabel();
            addKeepNumberQuestion();
        });
        return button;
    }

    private Button raiseCupButton() {
        Button button = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 100, Allrounder.screenMaxHeight / 2 + 120, "Becher heben");
        button.setOnAction(e -> {
            if (claimedNumber != 0) {
                if (checkExpression()) {
                    showLostScreen(activePlayer-1);
                    players.get(activePlayer-1).addPenaltyPoint();
                } else {
                    showLostScreen(activePlayer);
                    players.get(activePlayer).addPenaltyPoint();
                }
            }
        });
        return button;
    }

    private void addStartText() {
        Label playerText = new Label("Spieler " + players.get(activePlayer).getName() + ", Sie sind an der Reihe.");
        playerText.setLayoutX(Allrounder.screenMaxWidth / 2 - 230);
        playerText.setLayoutY(200);
        playerText.setFont(new Font(30));
        Label claimedNumberText = new Label(claimedNumber + " wird behauptet.");
        claimedNumberText.setLayoutX(Allrounder.screenMaxWidth / 2 - 230);
        claimedNumberText.setLayoutY(300);
        claimedNumberText.setFont(new Font(30));
        if (claimedNumber == 0) {
            claimedNumberText.setVisible(false);
        }
        pane.getChildren().addAll(playerText, claimedNumberText);
    }

    private void addKeepNumberQuestion() {
        Label playerText = new Label("Welche Zahl möchtest du an den nächsten Spieler weiter geben?");
        playerText.setLayoutX(Allrounder.screenMaxWidth / 2 - 230);
        playerText.setLayoutY(400);
        playerText.setFont(new Font(30));
        TextField claimedNumberText = new TextField();
        claimedNumberText.setLayoutX(Allrounder.screenMaxWidth / 2 - 230);
        claimedNumberText.setLayoutY(450);
        claimedNumberText.setFont(new Font(20));
        Button okButton = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 230, 500, "Ok");
        okButton.setOnAction(e -> {
            try {
                int enteredValue = Integer.parseInt(claimedNumberText.getText());
                if (!isRollableNumber(enteredValue)) {
                    throw new NumberFormatException();
                }
                claimedNumber = enteredValue;
                activePlayer++;
                if (players.size() == activePlayer) {
                    activePlayer = 0;
                }
                startGame();
            } catch (Exception ignored) {
            }
        });
        Button cancelButton = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 20, 500, "Cancel");
        cancelButton.setOnAction(e -> {
            stage.close();
        });
        pane.getChildren().addAll(playerText, claimedNumberText, okButton, cancelButton);
    }

    private void addPlayerLabels() {
        int i = 0;
        for (Player player : players) {
            Label label = new Label(player.getName() + " " + player.getPenaltyPoints());
            label.setLayoutX(100);
            label.setLayoutY(100 + i * 20);
            label.setFont(new javafx.scene.text.Font(20));
            i++;
            pane.getChildren().add(label);
        }
    }

    private void addRollNumberLabel() {
        Label label = new Label("Deine gewürfelte Zahl ist: " + diceCup.getNumber());
        label.setLayoutY(355);
        label.setFont(new javafx.scene.text.Font(20));
        label.setLayoutX(Allrounder.screenMaxWidth / 2 - 230);
        label.setFont(new Font(30));
        pane.getChildren().add(label);
    }

    private Button raiseWithoutRollCupButton() {
        Button button = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 100, Allrounder.screenMaxHeight / 2 + 150, "Erhöhen ohne Schütteln");
        button.setOnAction(e -> {
            if (claimedNumber != 0) {
                pane.getChildren().clear();
                addKeepNumberQuestion();
            }
        });
        return button;
    }

    private boolean isRollableNumber(int number) {
        int firstDigit = number % 10;
        int secondDigit = number / 10;
        return firstDigit > 0 && secondDigit > 0 && secondDigit <= 6 && firstDigit <= secondDigit;
    }

    private boolean checkExpression() {
        int firstDigit = diceCup.getNumber() % 10;
        int secondDigit = diceCup.getNumber() / 10;
        if (firstDigit == secondDigit) {
            diceCup.setNumber(diceCup.getNumber() * 10);
        }
        firstDigit = claimedNumber % 10;
        secondDigit = claimedNumber / 10;
        if (firstDigit == secondDigit) {
            claimedNumber = claimedNumber * 10;
        }
        return diceCup.getNumber() < claimedNumber && diceCup.getNumber() != 21;
    }

    private void showLostScreen(int player) {
        pane.getChildren().clear();
        Label lose = new Label("Player " + players.get(player).getName() + " lost!");
        lose.setLayoutX(Allrounder.screenMaxWidth / 2 - 230);
        lose.setLayoutY(400);
        lose.setFont(new Font(30));
        Button newGameButton = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 230, 450, "neue Runde Starten");
        newGameButton.setOnAction(e -> {
            Game game = new Game(pane, players, stage);
            game.startGame();
        });
        Button cancelButton = Allrounder.createDefaultButton(Allrounder.screenMaxWidth / 2 - 20, 450, "Cancel");
        cancelButton.setOnAction(e -> {
            stage.close();
        });
        pane.getChildren().addAll(lose, newGameButton, cancelButton);
    }

}
