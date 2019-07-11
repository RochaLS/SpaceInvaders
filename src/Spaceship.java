import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spaceship {

    private BufferedImage drawing;

    private int x;
    private int speed;
    private int reloading;
    private boolean canShoot;

    public Spaceship() {

        try {
            drawing = ImageIO.read(new File("images/nave.png"));
        } catch (IOException e) {
            System.out.println("Can't load image");
            e.printStackTrace();
        }

        x = 683;
        speed = 10;
        canShoot = true;
        reloading = 0;

    }

    public void drawSpaceship(Graphics2D g) {
                            //First two are posX and posY the other two are width and height
        g.drawImage(drawing, x, 600,x + 100,600 + 100, 0, 0, drawing.getWidth(), drawing.getHeight(), null);
    }

    // Spaceship returns a bullet on it's position
    public Bullet shoot() {
        canShoot = false;
        reloading = 0;
                                // originX + n is the middle of the ship
        Bullet newBullet = new Bullet(x + 49, 600);
        return newBullet;
    }

    public void movement(int value) {
        // if value is negative <<<<<<<
        // if value is positive >>>>>>>
        if (value == 1) {
            x += speed;
        } else if (value == -1) {
            x -= speed;
        }

        if (reloading >= 10) {
            canShoot = true;
            reloading = 0;
        }

        reloading++;


    }

    public boolean canShoot() {
        return canShoot;
    }
}
