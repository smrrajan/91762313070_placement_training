package model;

import java.util.Random;

public class Food {

    private int x;
    private int y;

    public void spawn(int screenWidth,
                      int screenHeight,
                      int unitSize) {

        Random random = new Random();

        x = random.nextInt(screenWidth / unitSize)
                * unitSize;

        y = random.nextInt(screenHeight / unitSize)
                * unitSize;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}