package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PongGame extends JPanel {

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball ball;
    private int playerLeftScore = 0;
    private int playerRightScore = 0;

    public PongGame() {

        paddleLeft = new Paddle(14, 240);
        paddleRight = new Paddle(760, 240);
        ball = new Ball(400, 300);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    paddleLeft.keyPressed(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    paddleLeft.keyPressed(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    paddleRight.keyPressed(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    paddleRight.keyPressed(e.getKeyCode());
                }
                repaint();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    paddleLeft.keyReleased(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    paddleLeft.keyReleased(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    paddleRight.keyReleased(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    paddleRight.keyReleased(e.getKeyCode());
                }
                repaint();
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        Timer timer = new Timer(10, e -> {
            paddleLeft.update();
            paddleRight.update();

            ball.move();
            handleBallOffScreen();
            ball.checkPaddleCollision(paddleLeft);
            ball.checkPaddleCollision(paddleRight);
            ball.checkWallCollision(getHeight());
            repaint();
        });
        timer.start();
    }

    private void handleBallOffScreen() {
        int ballX = ball.getX();
        int ballDiameter = ball.getDiameter();
        int screenWidth = getWidth();

        if(ballX < 0) {
            playerLeftScore++;
            resetBall();
        } else if(ballX > screenWidth - ballDiameter) {
            playerRightScore++;
            resetBall();
        }
    }

    private void resetBall() {
        ball.setX(getWidth()/2);
        ball.setY(getWidth()/2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(8.0f));
        g2d.drawLine(0,0,getWidth(),0);
        g2d.drawLine(0,getHeight()-1, getWidth(),getHeight() - 1);

        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());

        paddleLeft.draw(g);
        paddleRight.draw(g);
        ball.draw(g);

        g.setFont(new Font("Monospaced", Font.PLAIN, 40));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(playerLeftScore),405,45);
        g.drawString(String.valueOf(playerRightScore),355, 45);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong");
        PongGame game = new PongGame();
        frame.add(game);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
