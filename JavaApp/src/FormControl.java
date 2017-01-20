import gnu.io.CommPortIdentifier;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by s401321 on 18/01/2017.
 */
public class FormControl {
    public static FormControl instance;
    private JPanel main;
    private JSlider sliderMain;
    private JSlider sliderSecondary;
    private JSlider sliderClaw;
    private JSlider sliderBase;
    private JTextField COMTextField;
    private JButton connectButton;
    private JList listComs;
    private JPanel comms;
    private JPanel controls;
    private JFrame myFrame;
    public FormControl(JFrame frame) {
        this.myFrame=frame;
        controls.setVisible(false);
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


        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controls.setVisible(true);
                comms.setVisible(false);
                sliderMain.setEnabled(true);
                sliderSecondary.setEnabled(true);
                sliderClaw.setEnabled(true);
                sliderBase.setEnabled(true);
                myFrame.pack();
                try {
                    ArduinoController.connect(COMTextField.getText(), 38400);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        class PortProxy {
            public CommPortIdentifier comm;

            public PortProxy(CommPortIdentifier c) {
                this.comm = c;
            }

            @Override
            public String toString() {
                return ArduinoController.getPortName(comm);
            }
        }

        listComs.setListData(ArduinoController.getPorts().stream()
                .map(e -> new PortProxy(e))
                .toArray()
        );
        listComs.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting())
                return;
            COMTextField.setText(((PortProxy) listComs.getSelectedValue()).comm.getName());
        });
    }

    public static void create() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel() {

                @Override
                public UIDefaults getDefaults() {
                    UIDefaults ret = super.getDefaults();
                    ret.put("defaultFont", new Font(Font.MONOSPACED, Font.BOLD, 16)); // supersize me
                    ret.put("Slider.thumbWidth",50);
                    ret.put("Slider.thumbHeight",50);
                    return ret;
                }

            });
        } catch (Exception e) {
            e.printStackTrace();
        }
//        UIDefaults defaults = UIManager.getDefaults();
//        defaults.put("Slider.thumbHeight", 500); // change height
//        defaults.put("Slider.thumbWidth", 500); // change width
        JFrame frame = new JFrame("FormControl");
        instance = new FormControl(frame);
        frame.setContentPane(instance.main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        create();
    }
}
