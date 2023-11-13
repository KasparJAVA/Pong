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
