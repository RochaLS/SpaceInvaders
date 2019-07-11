import java.awt.*;

public class Bullet {

    private int x;
    private int y;
    private int speed;


    public Bullet(int originX, int originY) {

        this.x = originX;
        this.y = originY;

        speed = 15;
    }


    public void drawBullet(Graphics2D g) {

        g.setColor(Color.red);
        g.fillRect(x, y, 3, 15);
    }

    public void updateBullet() {
        y -= speed;
    }
}
