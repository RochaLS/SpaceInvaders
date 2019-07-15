import javax.swing.*;

public class Main {
    private static int width = 1366;
    private static int height = 768;

    public static void main(String[] args) {


        JFrame window = new JFrame("Space Invaders");
        window.setSize(1366,768);
        window.setLayout(null );
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SpaceInvaders spaceInvaders = new SpaceInvaders();
        spaceInvaders.setBounds(0,0, width, height);
        window.setVisible(true);

        window.add(spaceInvaders);

        window.addKeyListener(spaceInvaders);

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
