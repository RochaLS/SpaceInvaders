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

    public Enemy(int originX, int originY, int direction) {
        try {
            enemyDrawing = ImageIO.read(new File("images/inimigo.png"));
        } catch (IOException e) {
            System.out.println("Can't load image");
            e.printStackTrace();
        }

        this.x = originX;
        this.y = originY;
        this.direction = direction;
        this.speed = 2;
    }

    public void drawEnemy(Graphics2D g) {
        //First two are posX and posY the other two are width and height
        g.drawImage(enemyDrawing, x, y,x + 50, y + 50, 0, 0, enemyDrawing.getWidth(), enemyDrawing.getHeight(), null);
    }

    public void updateEnemy() {

        x += speed * direction;
    }

    public void changeDirection() {
        direction = direction * -1;
        y += 25;
    }

    public int getX() {
        return x;
    }
}
