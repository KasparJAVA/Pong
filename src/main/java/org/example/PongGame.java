package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PongGame extends JPanel {

    private static final int TITLE_SCREEN_STATE = 0;
    private static final int PVP_STATE = 1;
    private static final int PVC_STATE = 2;

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Paddle computerPaddle;
    private Ball ball;
    private int playerLeftScore = 0;
    private int playerRightScore = 0;
    private int gameState;

    public PongGame() {
        gameState = TITLE_SCREEN_STATE;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyRelease(e);
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        Timer timer = new Timer(10, e -> {
            if(gameState == TITLE_SCREEN_STATE) {

                repaint();
            } else if(gameState == PVP_STATE) {
                updatePaddles();
                handleBallOffScreen();
                ball.move();
                ball.checkPaddleCollision(paddleLeft);
                ball.checkPaddleCollision(paddleRight);
                ball.checkWallCollision(getHeight());
                repaint();
            } else if(gameState == PVC_STATE) {
                updatePvCPaddle();
                handleBallOffScreen();
                ball.move();
                ball.checkPaddleCollision(computerPaddle);
                ball.checkPaddleCollision(paddleRight);
                ball.checkWallCollision(getHeight());
                repaint();
            }
        });
        timer.start();
    }

    private void handleKeyPress(KeyEvent e) {
        if(gameState == TITLE_SCREEN_STATE) {
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                startGamePvP();
            } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                startGamePvC();
            }
        } else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_W:
                case KeyEvent.VK_S:
                    if (paddleLeft != null) {
                        paddleLeft.keyPressed(e.getKeyCode());
                    }
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_DOWN:
                    if (gameState == PVP_STATE || gameState == PVC_STATE) {
                        paddleRight.keyPressed(e.getKeyCode());
                    }
                    break;
            }
        }
        repaint();
    }

    private void handleKeyRelease(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
                if (paddleLeft != null) {
                    paddleLeft.keyReleased(e.getKeyCode());
                }
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                if (gameState == PVP_STATE || gameState == PVC_STATE) {
                    paddleRight.keyReleased(e.getKeyCode());
                }
                break;
        }
        repaint();
    }

    private void updatePaddles() {
        paddleLeft.update();
        paddleRight.update();
    }

    private void updatePvCPaddle() {
        paddleRight.update();
        if(computerPaddle != null) {
            computerPaddle.updateForComputer(ball);
        }

    }

    private void handleBallOffScreen() {
        int ballX = ball.getBallX();
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
        ball.setBallX(getWidth()/2);
        ball.setBallY(getWidth()/2);
    }

    private void startGamePvP() {
        paddleLeft = new Paddle(14, 240);
        paddleRight = new Paddle(760,240);
        ball = new Ball(400,300);
        gameState = PVP_STATE;
        repaint();
    }

    private void startGamePvC() {
        computerPaddle = new Paddle(14, getHeight() / 2 - 80/2);
        paddleRight = new Paddle(760,240);
        ball = new Ball(400,300);
        gameState = PVC_STATE;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if(gameState == TITLE_SCREEN_STATE) {
            drawTitleScreen(g);
        } else if (gameState == PVP_STATE) {
            drawGameScreen(g);
            paddleLeft.draw(g);
            paddleRight.draw(g);
            ball.draw(g);
        } else if (gameState == PVC_STATE) {
            drawGameScreen(g);
            computerPaddle.draw(g);
            paddleRight.draw(g);
            ball.draw(g);
        }
    }

    private void drawTitleScreen(Graphics g) {
        drawGameScreen(g);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 20));
        g.drawString("██████╗ ", 110,140);
        g.drawString("██╔══██╗", 110,164);
        g.drawString("██████╔╝", 110,188);
        g.drawString("██╔═══╝ ", 110,212);
        g.drawString("██║", 110,236);
        g.drawString("╚═╝", 110,260);

        g.drawString("   ██████╗ ", 250,140);
        g.drawString("██╔═══██╗", 250,164);
        g.drawString("██║       ██║", 250,188);
        g.drawString("██║       ██║", 250,212);
        g.drawString("╚██████╔╝", 250,236);
        g.drawString("  ╚═════╝ ", 250,260);

        g.drawString("███╗       ██╗", 410,140);
        g.drawString("████╗", 410,164);
        g.drawString("██║", 508,164);
        g.drawString("██╔██╗", 410,188);
        g.drawString("██║", 508,188);
        g.drawString("██║╚██╗██║", 410,212);
        g.drawString("██║", 410,236);
        g.drawString("╚████║", 466,236);
        g.drawString("╚═╝", 410,260);
        g.drawString("╚═══╝", 480,260);

        g.drawString("  ██████╗ ", 577,140);
        g.drawString("██╔════╝ ", 575,164);
        g.drawString("██║", 575,188);
        g.drawString("    ███╗", 622,188);
        g.drawString("██║", 575,212);
        g.drawString("      ██║", 624,212);
        g.drawString("╚██████╔╝", 576,236);
        g.drawString("  ╚═════╝ ", 578,260);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 20));
        g.drawString("↑", 585, 420);
        g.drawString("↓", 585, 450);
        g.drawString("Press Spacebar for PvP", 460, 500);
        g.drawString("\"W\"", 176, 420);
        g.drawString("\"S\"", 176, 450);
        g.drawString("Press Enter for PvC", 80, 500);
    }

    private void drawGameScreen(Graphics g) {
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

        g.setFont(new Font("Monospaced", Font.PLAIN, 40));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(playerLeftScore),405,45);
        String rightPlayerScore = String.valueOf(playerRightScore);
        g.drawString(rightPlayerScore, 355 - 23 * (rightPlayerScore.length() - 1), 45);
//        g.drawString(String.valueOf(playerRightScore),355, 45);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pong");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800,600);

            frame.setLocationRelativeTo(null);
            PongGame pongGame = new PongGame();
            frame.add(pongGame);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}
