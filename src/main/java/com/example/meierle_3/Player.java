package com.example.meierle_3;

public class Player {
    String name;
    int penaltyPoints;

    public Player(String name) {
        this.name = name;
        penaltyPoints = 0;
    }

    public void addPenaltyPoint() {
        penaltyPoints++;
    }

}
