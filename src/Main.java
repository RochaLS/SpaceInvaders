import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame window = new JFrame("Space Invaders");
        window.setSize(1366,768);
        window.setLayout(null );
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        SpaceInvaders spaceInvaders = new SpaceInvaders();
        spaceInvaders.setBounds(0,0, 1366, 768);
        window.setVisible(true);

        window.add(spaceInvaders);

        window.addKeyListener(spaceInvaders);

    }
}
