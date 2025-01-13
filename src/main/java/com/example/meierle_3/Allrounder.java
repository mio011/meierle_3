package com.example.meierle_3;

import javafx.scene.control.Button;
import javafx.stage.Screen;

public class Allrounder {

    static int screenMaxWidth = (int) Screen.getPrimary().getVisualBounds().getWidth();
    static int screenMaxHeight = (int) Screen.getPrimary().getVisualBounds().getHeight();

    static Button createDefaultButton(int x, int y, String text) {
        Button button = new Button(text);
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefWidth(200);
        button.setPrefHeight(20);
        return button;
    }
}
