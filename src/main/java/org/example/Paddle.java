package org.example;

import java.awt.*;

public class Paddle {
    private int x, y;
    private int width = 10;
    private int height = 80;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveUp() {
        y -= 5;
    }

    public void moveDown() {
        y += 5;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x,y,width,height);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
