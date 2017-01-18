import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by s401321 on 18/01/2017.
 */
public class FormControl {
    private JPanel main;
    private JButton turnButton;

    public FormControl() {
        turnButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    public static void create() {
        JFrame frame = new JFrame("FormControl");
        frame.setContentPane(new FormControl().main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        create();
    }
}
