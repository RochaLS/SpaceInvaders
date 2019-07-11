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

    // Check if bullet Y position is < 0 if it is the func returns true
    public boolean destroy() {
        return y < 0;
    }

//    public boolean collideWith(Enemy enemy) {
//
//    }
}
