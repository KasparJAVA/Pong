package org.example;

import java.awt.*;

public class Ball {
    private int ballX, ballY;
    private int diameter = 15;
    private int xSpeed = 3;
    private int ySpeed = 3;

    public Ball(int ballX, int ballY) {
        this.ballX = ballX;
        this.ballY = ballY;
    }

    public void move() {
        ballX += xSpeed;
        ballY += ySpeed;
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY,diameter,diameter);
    }

    public void checkPaddleCollision(Paddle paddle) {
        if(ballX + diameter > paddle.getPaddleXPos() && ballX < paddle.getPaddleXPos() + paddle.getWidth() && ballY + diameter > paddle.getPaddleYPos() && ballY < paddle.getPaddleYPos() + paddle.getHeight()) {
            xSpeed = -xSpeed;
        }
    }

    public void checkWallCollision(int screenHeight) {
        if (ballY <= 0 || ballY + diameter >= screenHeight) {
            ySpeed = -ySpeed;
        }
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setBallX(int ballX) {
        this.ballX = ballX;
    }

    public void setBallY(int ballY) {
        this.ballY = ballY;
    }
}
