package com.example.meierle_3;

import javafx.scene.control.Button;
import javafx.stage.Screen;

public class Allrounder {

    static double screenMaxWidth = Screen.getPrimary().getVisualBounds().getWidth();
    static double screenMaxHeight = Screen.getPrimary().getVisualBounds().getHeight();

    static Button createDefaultButton(double x, double y, String text) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(200);
        button.setPrefHeight(20);
        return button;
    }
}
