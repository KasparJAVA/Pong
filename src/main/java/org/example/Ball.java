package org.example;

import java.awt.*;

public class Ball {
    private int ballX, ballY;
    private int diameter = 15;
    private int xSpeed = 8;
    private int ySpeed = 8;



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
        if (ballX + diameter > paddle.getPaddleXPos() && ballX < paddle.getPaddleXPos() + paddle.getWidth() && ballY + diameter > paddle.getPaddleYPos() && ballY < paddle.getPaddleYPos() + paddle.getHeight()) {
            int paddleCenterY = paddle.getPaddleYPos() + paddle.getHeight() / 2;
            int relativeBallY = ballY + diameter / 2 - paddleCenterY;
            double normalizedRelativeY = (double) relativeBallY / ((double) paddle.getHeight() / 2);

            double reflectionAngle = normalizedRelativeY * (Math.PI / 3.5);

            double speed = 8;

            xSpeed = (int) (speed * Math.cos(reflectionAngle));
            ySpeed = (int) (speed * Math.sin(reflectionAngle));

            if (xSpeed > 0 && ballX < paddle.getPaddleXPos() + paddle.getWidth() / 2) {
                xSpeed = -xSpeed;
            } else if (xSpeed < 0 && ballX > paddle.getPaddleXPos() + paddle.getWidth() / 2) {
                xSpeed = -xSpeed;
            }
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
