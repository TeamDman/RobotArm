import gnu.io.CommPortIdentifier;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by s401321 on 18/01/2017.
 */
public class FormControl {
    private JPanel main;
    private JSlider sliderMain;
    private JSlider sliderSecondary;
    private JSlider sliderClaw;
    private JSlider sliderBase;
    private JTextField COMTextField;
    private JButton connectButton;
    private JList listComs;

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


        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sliderMain.setEnabled(true);
                sliderSecondary.setEnabled(true);
                sliderClaw.setEnabled(true);
                sliderBase.setEnabled(true);

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
                this.comm=c;
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
