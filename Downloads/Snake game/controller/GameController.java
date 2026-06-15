package controller;

import model.Food;
import model.Snake;

public class GameController {

    private final Snake snake;
    private final Food food;

    private int score = 0;

    public GameController(
            Snake snake,
            Food food) {

        this.snake = snake;
        this.food = food;
    }

    public void checkFood() {

        if (snake.getX()[0] == food.getX()
                &&
                snake.getY()[0] == food.getY()) {

            snake.grow();
            score++;
        }
    }

    public int getScore() {
        return score;
    }
}