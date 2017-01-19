/**
 * Created by s401321 on 17/01/2017.
 */

import gnu.io.*;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ArduinoController {
    public static OutputStream board;
    public static enum Servos {
        MAIN('a'),
        SECONDARY('b'),
        CLAW('c'),
        BASE('d');

        char identifier;
        Servos(char identifier) {
            this.identifier = identifier;
        }
    }
    public static void main(String[] args) {
        FormControl.create();
    }

    public static void setArmAngle(Servos arm, int angle) {
        try {
            board.write((byte) arm.identifier);
            board.write(angle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void connect(String portName, int baud) throws Exception {
        CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        if (portIdentifier.isCurrentlyOwned()) {
            JOptionPane.showMessageDialog(null,"Error: Port is currently in use");
        } else {
            CommPort commPort = portIdentifier.open(ArduinoController.class.getName(), 2000);
            if (commPort instanceof SerialPort) {
                SerialPort serialPort = (SerialPort) commPort;
                serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

                InputStream in = serialPort.getInputStream();
                board = serialPort.getOutputStream();

                (new Thread(new SerialReader(in))).start();
            } else {
                JOptionPane.showMessageDialog(null,"Error: Only serial ports are handled");
            }
        }
    }

    public static class SerialReader implements Runnable {
        InputStream in;

        public SerialReader(InputStream in) {
            this.in = in;
        }

        public void run() {
            byte[] buffer = new byte[1024];
            int len = -1;
            try {
                while ((len = this.in.read(buffer)) > -1) {
                    System.out.print(new String(buffer, 0, len));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static List<CommPortIdentifier> getPorts() {
        ArrayList<CommPortIdentifier> rtn = new ArrayList<>();
        java.util.Enumeration<CommPortIdentifier> portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier portIdentifier = portEnum.nextElement();
           rtn.add(portIdentifier);
        }
        return rtn;
    }

    static String getPortName(CommPortIdentifier comm) {
        String rtn = comm.getName() + " - ";
        switch (comm.getPortType()) {
            case CommPortIdentifier.PORT_I2C:
                rtn += "I2C";
                break;
            case CommPortIdentifier.PORT_PARALLEL:
                rtn += "Parallel";
                break;
            case CommPortIdentifier.PORT_RAW:
                rtn += "Raw";
                break;
            case CommPortIdentifier.PORT_RS485:
                rtn += "RS485";
                break;
            case CommPortIdentifier.PORT_SERIAL:
                rtn += "Serial";
                break;
            default:
                rtn += "unknown type";
        }
        return rtn;
    }
}
