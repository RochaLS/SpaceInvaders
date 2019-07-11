import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SpaceInvaders extends JPanel implements Runnable, KeyListener {

    private Spaceship spaceship;
    private int direction;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;

    // Constructor - Called when doing New SpaceInvaders()
    public SpaceInvaders() {

        spaceship = new Spaceship();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();

        for(int i = 0; i < 60; i++) {
            enemies.add(new Enemy( 50 + i % 20 * 50, 50 + i/20 * 50, 1));
        }

        Thread gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        int frameCount = 0;


        while(true) {
            update();
            repaint();
            sleep();
        }
    }

    private void update() {
        spaceship.movement(direction);

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).updateEnemy();
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).updateBullet();

            // Remove bullets from the ArrayList
            if (bullets.get(i).destroy()) {
                bullets.remove(i);
                i--;
            }

            for(int j = 0; j < enemies.size(); j++) {
//                if (bullets.get(i).colideWith(enemies.get(j))) {
//                    enemies.remove(j);
//                    j--;
//
//                    bullets.remove(i);
//                }
            }

        }

        for(int i = 0; i < enemies.size(); i++) {
            // Checking if enemy is at the left border of the or if it is at the right border.
            if(enemies.get(i).getX() <= 0 || enemies.get(i).getX() > 1366 - 50) {

                // Change the direction of ALL enemies
                for(int j = 0; j < enemies.size(); j++) {
                    enemies.get(j).changeDirection();
                }
                break; // Need to break to change direction just once
            }
        }

    }

    public void paintComponent(Graphics g2) {
        super.paintComponent(g2); // Clear the Screen

        // Turn on the anti aliasing
        Graphics2D g = (Graphics2D) g2.create();
        g.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );
        g.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );


        // Draw spaceship
        spaceship.drawSpaceship(g);
        // Draw enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).drawEnemy(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).drawBullet(g);
        }

    }

    private void sleep() {
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

        }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            direction = 1; // Move Right
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            direction = -1; // Move Left
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE && spaceship.canShoot()) {
            bullets.add(spaceship.shoot()); // Adding bullet created by shoot() on the ArrayList
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_D) {
            direction = 0;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            direction = 0;
        }
    }
}
