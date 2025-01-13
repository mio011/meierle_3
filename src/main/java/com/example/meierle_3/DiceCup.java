package com.example.meierle_3;

public class DiceCup {
    int number;

    void roll() {
        int number1 = (int)(Math.random()*6)+1;
        int number2 = (int)(Math.random()*6)+1;
        if(number1 > number2) {
            number = number1 * 10 + number2;
        } else {
            number = number2 * 10 + number1;
        }
    }

    public int getNumber() {
        return number;
    }
}
