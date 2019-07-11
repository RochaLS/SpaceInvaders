import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SpaceInvaders extends JPanel implements Runnable, KeyListener {

    private Spaceship spaceship;
    private int direction;
    private ArrayList<Bullet> bullets;

    // Constructor - Called when doing New SpaceInvaders()
    public SpaceInvaders() {

        spaceship = new Spaceship();
        bullets = new ArrayList<Bullet>();

        Thread gameThread = new Thread(this);
        gameThread.start();

    }

//    boolean allowedToUpdate()
//    {
//        // do logic to check if allowed to yupsatre
//        // save current time
//        // have variable nextUpdate
//
//        // if (nextUpdate <= currentTime)
//        {
//            // set next update to time plus update rate
//            return true;
//
//        }
//        else
//
//            return false;
//        return true;
//    }

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

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).updateBullet();
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



        spaceship.drawSpaceship(g);

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
