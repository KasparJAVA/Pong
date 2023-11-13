package org.example;

import java.awt.*;

public class Ball {
    private int x,y;
    private int diameter = 20;
    private int xSpeed = 2;
    private int ySpeed = 2;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move() {
        x += xSpeed;
        y += ySpeed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x,y,diameter,diameter);
    }

    public void checkPaddleCollision(Paddle paddle) {
        if(x + diameter > paddle.getX() && x < paddle.getX() + paddle.getWidth() && y + diameter > paddle.getY() && y < paddle.getY() + paddle.getHeight()) {
            xSpeed = -xSpeed;
        }
    }

    public void checkWallCollision(int screenHeight) {
        if (y <= 0 || y + diameter >= screenHeight) {
            ySpeed = -ySpeed;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDiameter() {
        return diameter;
    }
}
