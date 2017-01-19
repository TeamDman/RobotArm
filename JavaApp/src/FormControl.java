import javax.swing.*;

/**
 * Created by s401321 on 18/01/2017.
 */
public class FormControl {
    private JPanel main;
    private JSlider sliderMain;
    private JSlider sliderSecondary;
    private JSlider sliderClaw;
    private JSlider sliderBase;

    public FormControl() {
        sliderMain.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
                ArduinoController.setArmAngle(ArduinoController.Servos.MAIN, source.getValue());
        });
        sliderSecondary.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
                ArduinoController.setArmAngle(ArduinoController.Servos.SECONDARY, source.getValue());
        });
        sliderClaw.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
                ArduinoController.setArmAngle(ArduinoController.Servos.CLAW, source.getValue());
        });
        sliderBase.addChangeListener(e -> {
            JSlider source = (JSlider) e.getSource();
                ArduinoController.setArmAngle(ArduinoController.Servos.BASE, source.getValue());
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
