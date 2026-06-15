package ui;

import controller.GameController;
import model.Food;
import model.Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel
        implements ActionListener {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    public static final int UNIT = 25;
    public static final int DELAY = 100;

    private final Snake snake;
    private final Food food;
    private final GameController controller;

    private Timer timer;

    private boolean running = true;
    private char direction = 'R';

    public GamePanel() {

        setPreferredSize(
                new Dimension(WIDTH, HEIGHT)
        );

        setBackground(Color.BLACK);
        setFocusable(true);

        snake = new Snake(
                (WIDTH * HEIGHT)
                        / (UNIT * UNIT)
        );

        food = new Food();

        controller =
                new GameController(
                        snake,
                        food
                );

        food.spawn(
                WIDTH,
                HEIGHT,
                UNIT
        );

        addKeyListener(
                new SnakeKeyListener()
        );

        timer = new Timer(
                DELAY,
                this
        );

        timer.start();
    }

    @Override
    protected void paintComponent(
            Graphics g) {

        super.paintComponent(g);

        draw(g);
    }

    private void draw(Graphics g) {

        if (!running) {

            gameOver(g);
            return;
        }

        g.setColor(new Color(30, 30, 30));

        for (int x = 0; x <= WIDTH; x += UNIT) {
            g.drawLine(x, 0, x, HEIGHT);
        }

        for (int y = 0; y <= HEIGHT; y += UNIT) {
            g.drawLine(0, y, WIDTH, y);
        }

        g.setColor(Color.RED);

        g.fillOval(
                food.getX(),
                food.getY(),
                UNIT,
                UNIT
        );

        for (int i = 0;
             i < snake.getBodyParts();
             i++) {

            if (i == 0)
                g.setColor(Color.GREEN);
            else
                g.setColor(
                        new Color(
                                45,
                                180,
                                0
                        )
                );

            g.fillRect(
                    snake.getX()[i],
                    snake.getY()[i],
                    UNIT,
                    UNIT
            );
        }

        g.setColor(Color.WHITE);

        g.drawString(
                "Score: "
                        + controller.getScore(),
                20,
                20
        );
    }

    private void move() {

        int[] x = snake.getX();
        int[] y = snake.getY();

        for (int i =
             snake.getBodyParts();
             i > 0;
             i--) {

            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {

            case 'U':
                y[0] -= UNIT;
                break;

            case 'D':
                y[0] += UNIT;
                break;

            case 'L':
                x[0] -= UNIT;
                break;

            case 'R':
                x[0] += UNIT;
                break;
        }

        checkCollision();
    }

    private void checkCollision() {

        int headX =
                snake.getX()[0];

        int headY =
                snake.getY()[0];

        if (headX < 0 ||
                headX >= WIDTH ||
                headY < 0 ||
                headY >= HEIGHT) {

            running = false;
            timer.stop();
        }
    }

    private void gameOver(
            Graphics g) {

        g.setColor(Color.RED);

        g.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        50
                )
        );

        g.drawString(
                "GAME OVER",
                150,
                300
        );
    }

    @Override
    public void actionPerformed(
            ActionEvent e) {

        move();

        controller.checkFood();

        if (snake.getX()[0]
                == food.getX()
                &&
                snake.getY()[0]
                        == food.getY()) {

            food.spawn(
                    WIDTH,
                    HEIGHT,
                    UNIT
            );
        }

        repaint();
    }

    class SnakeKeyListener
            extends KeyAdapter {

        @Override
        public void keyPressed(
                KeyEvent e) {

            switch (
                    e.getKeyCode()
            ) {

                case KeyEvent.VK_UP:
                    if (direction != 'D')
                        direction = 'U';
                    break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U')
                        direction = 'D';
                    break;

                case KeyEvent.VK_LEFT:
                    if (direction != 'R')
                        direction = 'L';
                    break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L')
                        direction = 'R';
                    break;
            }
        }
    }
}