package model;

public class Snake {

    private final int[] x;
    private final int[] y;
    private int bodyParts;

    public Snake(int maxUnits) {
        x = new int[maxUnits];
        y = new int[maxUnits];
        bodyParts = 1;
    }

    public int[] getX() {
        return x;
    }

    public int[] getY() {
        return y;
    }

    public int getBodyParts() {
        return bodyParts;
    }

    public void grow() {
        bodyParts++;
    }
}