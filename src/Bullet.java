import java.awt.*;

public class Bullet {

    private int x;
    private int y;
    private int speed;
    private int sizeX;
    private int sizeY;


    public Bullet(int originX, int originY) {

        this.x = originX;
        this.y = originY;

        speed = 15;
        sizeX = 3;
        sizeY = 15;
    }


    public void drawBullet(Graphics2D g) {

        g.setColor(Color.red);
        g.fillRect(x, y, sizeX, sizeY);
    }

    public void updateBullet() {
        y -= speed;
    }

    // Check if bullet Y position is < 0 if it is the func returns true
    public boolean destroy() {

        return y < 0;
    }

    public boolean collideWith(Enemy enemy) {
        // Checks if bullet will inside the enemy
        if ( x >= enemy.getX() && x + sizeX < enemy.getX() + enemy.getSize()) {

            // Checks if bullet already reached the enemy
            if (y <= enemy.getY() + enemy.getSize()) {
                return true;
            }
        }
        return false;
    }

    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void hideBullet() {
        sizeX = 0;
        sizeY = 0;
    }
}
