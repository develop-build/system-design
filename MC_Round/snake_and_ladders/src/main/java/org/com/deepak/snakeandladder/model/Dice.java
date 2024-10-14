package org.com.deepak.snakeandladder.model;
import java.util.Random;

public class Dice {
    private int numberOfDice;
    private static int MIN = 1;
    private Random random;

    public Dice(int numberOfDice) {
        this.numberOfDice = numberOfDice;
        this.random = new Random();
    }
    public int getNumberOnDice() {
        return random.nextInt(numberOfDice) + MIN;
    }
}
