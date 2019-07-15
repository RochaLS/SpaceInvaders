import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SpaceInvaders extends JPanel implements Runnable, KeyListener {

    private Font myFont;
    private Spaceship spaceship;
    private int direction;
    private ArrayList<Bullet> bullets;
    private ArrayList<Enemy> enemies;
    private Background background;
    private boolean won;
    private boolean lose;
    private JLabel labelScore;
    private int score;
    BufferedImage enemyDrawing;

    // Constructor - Called when doing New SpaceInvaders()
    public SpaceInvaders() {

        spaceship = new Spaceship();
        bullets = new ArrayList<Bullet>();
        enemies = new ArrayList<Enemy>();
        background = new Background();
        myFont = new Font("Consola", Font.BOLD, 20);
        won = false;
        lose = false;
        score = 0;


        try {
            enemyDrawing = ImageIO.read(new File("images/inimigo.png"));
        } catch (IOException e) {
            System.out.println("Can't load image");
            e.printStackTrace();
        }

        for(int i = 0; i < 40; i++) {
            enemies.add(new Enemy(enemyDrawing, 50 + i % 20 * 50, 50 + i/20 * 50, 1));
        }

        Thread gameThread = new Thread(this);
        gameThread.start();

    }

    @Override
    public void run() {
        int frameCount = 0;


        while(true) {
            long startTime = System.currentTimeMillis();

            update();
            repaint();

            long finalTime = System.currentTimeMillis();
            long time = 16 - (finalTime - startTime);

            // It's just gonna sleep when it needs to, in slower computers it will run as fast as it can, if it's s faster computer it will sleep for more time.
            if (time > 0) {
                sleep(time);
            }

        }
    }

    private void update() {
        spaceship.movement(direction);


        if (enemies.size() == 0) {
            won = true;
        }

        // Creating borders at the left and right side
        if (spaceship.getX() <= 0) {
            spaceship.leftBorder();
        } else if (spaceship.getX() > 1366 - 100) {
            spaceship.rightBorder();
        }

        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).updateEnemy();

            //The Spaceship is gone after they collide
            if (enemies.get(i).collideWith(spaceship)) {
                spaceship.hide();
                lose = true;
            }
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).updateBullet();

            // Remove bullets from the ArrayList
            if (bullets.get(i).destroy()) {
                bullets.remove(i);
                i--;
            } else if (enemies.size() == 0 || lose == true) {
                bullets.get(i).hideBullet();
            }
            else {

                for (int j = 0; j < enemies.size(); j++) {
                    if (bullets.get(i).collideWith(enemies.get(j))) {
                        enemies.remove(j);
                        j--;
                        score += 1;
                        System.out.println(score);

                        bullets.remove(i);
                        break; // If bullet is removed we can't check the same bullet that was removed with other enemies, it would crash the game, so that's we are using break here.
                    }

                }
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

        Graphics2D g = (Graphics2D) g2.create();

        // Draw background
        background.drawBackground(g);

        g.setColor(Color.white);
        g.setFont(myFont);
        g.drawString( "Score: " + Integer.toString(score), 10,20);


        //Draw won message
        if (won) {
            g.setFont(myFont);
            g.setColor(Color.white);
            g.drawString("You won!", 1366 / 2 - 50, 768 / 2);

        } else if (lose) {
            g.setFont(myFont);
            g.setColor(Color.white);
            g.drawString("Game Over!", 1366 / 2 - 50, 768 / 2);
        }

        // Draw spaceship
        spaceship.drawSpaceship(g);

        // Draw enemies
        for(int i = 0; i < enemies.size(); i++) {
            enemies.get(i).drawEnemy(g);
        }

        // Draw Bullets
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).drawBullet(g);
        }



    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
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
