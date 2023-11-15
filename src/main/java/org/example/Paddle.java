package org.example;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class Paddle {
    private int paddleXPos, paddleYPos;
    private int width = 10;
    private int height = 80;
    private Set<Integer> keysPressed = new HashSet<>();

    public Paddle(int paddleXPos, int paddleYPos) {
        this.paddleXPos = paddleXPos;
        this.paddleYPos = paddleYPos;
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

    public void updateForComputer(Ball ball) {
        int paddleCenterY = paddleYPos + height/2;

        if(ball.getBallY() < paddleCenterY) {
            moveUp();
        } else {
            moveDown();
        }
    }

    public void moveUp() {
        if(paddleYPos - 5 >= 0) {
            paddleYPos -= 5;
        }
    }

    public void moveDown() {
        if(paddleYPos + height + 5 <= 560) {
            paddleYPos += 5;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(paddleXPos, paddleYPos,width,height);
    }

    public int getPaddleXPos() {
        return paddleXPos;
    }

    public int getPaddleYPos() {
        return paddleYPos;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
