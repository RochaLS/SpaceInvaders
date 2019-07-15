import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Spaceship {

    private BufferedImage drawing;

    private int x;
    private int y;
    private int speed;
    private int reloading;
    private boolean canShoot;
    private int sizeX;
    private int sizeY;

    public Spaceship() {

        try {
            drawing = ImageIO.read(new File("images/nave.png"));
        } catch (IOException e) {
            System.out.println("Can't load image");
            e.printStackTrace();
        }

        x = 683;
        y = 600;
        speed = 10;
        canShoot = true;
        reloading = 0;
        sizeX = 100;
        sizeY = 100;
    }

    public void drawSpaceship(Graphics2D g) {
                            //First two are posX and posY the other two are width and height
        g.drawImage(drawing, x, y,x + sizeX, y + sizeY, 0, 0, drawing.getWidth(), drawing.getHeight(), null);
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void leftBorder() {
        x = 0;
    }

    public void rightBorder() {
        x = 1366 - 100;
    }

    public int getSize() {
        return 100;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void hide() {
        sizeX = 0;
        sizeY = 0;
    }


}
