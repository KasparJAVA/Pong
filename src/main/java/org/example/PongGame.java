package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PongGame extends JPanel {

    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;


    public PongGame() {
        paddle1 = new Paddle(20, 250);
        paddle2 = new Paddle(760, 250);
        ball = new Ball(400, 300);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    paddle1.keyPressed(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    paddle1.keyPressed(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    paddle2.keyPressed(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    paddle2.keyPressed(e.getKeyCode());
                }
                repaint();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    paddle1.keyReleased(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_S) {
                    paddle1.keyReleased(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    paddle2.keyReleased(e.getKeyCode());
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    paddle2.keyReleased(e.getKeyCode());
                }
                repaint();
            }
        });

        setFocusable(true);
        requestFocusInWindow();

        Timer timer = new Timer(10, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                paddle1.update();
                paddle2.update();

                ball.move();
                ball.checkPaddleCollision(paddle1);
                ball.checkPaddleCollision(paddle2);
                ball.checkWallCollision(getHeight());
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        g.fillRect(0,0,getWidth(),getHeight());

        paddle1.draw(g);
        paddle2.draw(g);
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
