package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Paddle {
    private int x, y;
    private int width = 10;
    private int height = 80;

    private Set<Integer> keysPressed = new HashSet<>();

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void keyPressed (int keyCode) {
        keysPressed.add(keyCode);
    }

    public void keyReleased(int keyCode) {
        keysPressed.remove(keyCode);
    }

    public void update() {
        if(keysPressed.contains(KeyEvent.VK_W) && !keysPressed.contains(KeyEvent.VK_S)) {
            moveUp();
        } else if (!keysPressed.contains(KeyEvent.VK_W) && keysPressed.contains(KeyEvent.VK_S)) {
            moveDown();
        }
        if(keysPressed.contains(KeyEvent.VK_UP) && !keysPressed.contains(KeyEvent.VK_DOWN)) {
            moveUp();
        } else if (!keysPressed.contains(KeyEvent.VK_UP) && keysPressed.contains(KeyEvent.VK_DOWN)) {
            moveDown();
        }

    }

    public void updateForComputer(int ballY) {
        int paddleCenterY = y + height/2;

        if(ballY < paddleCenterY) {
            moveUp();
        } else {
            moveDown();
        }
    }

    public void moveUp() {
        if(y - 5 >= 0) {
            y -= 5;
        }
    }

    public void moveDown() {
        if(y + height + 5 <= 560) {
            y += 5;
        }
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
