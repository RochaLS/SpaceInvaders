import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Enemy {
    private BufferedImage enemyDrawing;
    private int x;
    private int y;
    private int speed;
    private int direction;
    private int sizeX;
    private int sizeY;

    public Enemy(BufferedImage image,int originX, int originY, int direction) {

        this.enemyDrawing = image;
        this.x = originX;
        this.y = originY;
        this.direction = direction;
        this.speed = 2;
        sizeX = 50;
        sizeY = 50;
    }

    public void drawEnemy(Graphics2D g) {
        //First two are posX and posY the other two are width and height
        g.drawImage(enemyDrawing, x, y,x + sizeX, y + sizeY, 0, 0, enemyDrawing.getWidth(), enemyDrawing.getHeight(), null);
    }

    public void updateEnemy() {

        x += speed * direction;
    }

    public void changeDirection() {
        direction = direction * -1;
        y += 25;
        speed++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return 50;
    }

    public boolean collideWith(Spaceship spaceship) {
        if (x >= spaceship.getX() &&  x + sizeX < spaceship.getX() + spaceship.getSize()  ) {

            if (y >= spaceship.getY()) {
                spaceship.hide();
                return true;

//                if ( x >= enemy.getX() && x + sizeX < enemy.getX() + enemy.getSize()) {
//
//                    // Checks if bullet already reached the enemy
//                    if (y <= enemy.getY() + enemy.getSize()) {
//                        return true;
            }
        }
        return false;
    }
}
