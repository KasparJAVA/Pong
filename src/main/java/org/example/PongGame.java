package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PongGame extends JPanel {

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball ball;


    public PongGame() {

        paddleLeft = new Paddle(13, 240);
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
            ball.checkPaddleCollision(paddleLeft);
            ball.checkPaddleCollision(paddleRight);
            ball.checkWallCollision(getHeight());
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(8.0f));
        g2d.drawRect(0, 0,  getWidth()-1, getHeight()-1);

        g2d.setStroke(new BasicStroke(3.0f));
        g2d.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());

        paddleLeft.draw(g);
        paddleRight.draw(g);
        ball.draw(g);
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
