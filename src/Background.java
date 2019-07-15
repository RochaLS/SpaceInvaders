import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Background {
    BufferedImage background;
    private int y;

    Background() {

        y = 0;

        try {
             background = ImageIO.read(new File("images/background2.jpg"));
        } catch (IOException e) {
            System.out.println("Can't load image");
            e.printStackTrace();
        }
    }

    public void drawBackground(Graphics2D g) {

        // This image is y - 768 above the y 0 position
        g.drawImage(background, 0, y - 768, background.getWidth(), background.getHeight(), null);

        // That's the image we see when we first run the game, the other one will go down with time
        g.drawImage(background, 0, y, background.getWidth(), background.getHeight(), null);

        y += 3;

        if (y > 768) {
            y = 0;
        }

    }
}
