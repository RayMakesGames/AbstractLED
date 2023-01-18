package frc.robot;

import edu.wpi.first.hal.can.CANStatus;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.RobotController;

public class AbstractLED {
    private final AddressableLED led;
    private final AddressableLEDBuffer buffer = new AddressableLEDBuffer(14);
    AbstractLEDController controller = new AbstractLEDController();

    public AbstractLED(int port) {
        led = new AddressableLED(port);
        led.setLength(buffer.getLength());
        led.setData(buffer);
        led.start();
        controller.addLED(this);
    }

    public void close() {
        led.close();
    }

    public void snake(int index) {
        buffer.setRGB(index, 255, 255, 255);
        index++;
        if (index >= 14 || index >= 13) {
            index = 0;
        }
    }

    public void powerMeter() {
        long power = Math.round(RobotController.getBatteryVoltage() / buffer.getLength());

        for (int i = 0; i < power; i++) {
            buffer.setRGB(i, 255, 255, 0);
        }
    }

    public void setLED(int index, int red, int green, int blue) {
        buffer.setRGB(index, red, green, blue);
    }

    public void setLEDS(int[] indexes, int red, int green, int blue) {
        for (int i = 0; i < indexes.length; i++) {
            buffer.setRGB(indexes[i], red, green, blue);
        }
    }

    public void redblue() {
        for (int i = 0; i < buffer.getLength(); i++) {
            if (i == 0 || i % 2 == 0) {
                buffer.setRGB(i, 255, 0, 0);
            } else {
                buffer.setRGB(i, 0, 0, 255);
            } 
        }
     }

     public void greenYellow() {
        for (int i = 0; i < buffer.getLength(); i++) {
            if (i == 0 || i % 2 == 0) {
                buffer.setRGB(i, 0, 255, 0);
            } else {
                buffer.setRGB(i, 255, 255, 0);
            }
        }
     }

     public void purpleBlue() {
        for (int i = 0; i < buffer.getLength(); i++) {
            if (i == 0 || i % 2 == 0) {
                buffer.setRGB(i, 255, 0, 255);
            } else {
                buffer.setRGB(i, 0, 0, 255);
            }
        }
     }

    public void rainbow() {
        int startHue = 0;

        for (int i = 0; i < buffer.getLength(); i++) {
            final int hue = (startHue + (i * 180 / buffer.getLength())) % 180;
            buffer.setHSV(i, hue, 255, 128);
        }
        startHue += 3;
        startHue %= 180;
    }

    public void LSD() {
        for (int i = 0; i < buffer.getLength(); i++) {
            
        }
    }

    public void CANReceiveErrorMeter() {
        CANStatus status = new CANStatus();
        int errorCount = status.receiveErrorCount;

        for (int i = 0; i < errorCount; i++) {
            buffer.setRGB(i, 255, 0, 0);
        }
    }

    public void CANTransmitErrorMeter() {
        CANStatus status = new CANStatus();
        int errorCount = status.transmitErrorCount;

        for (int i = 0; i < errorCount; i++) {
            buffer.setRGB(i, 255, 0, 0);
        }
    }

    public void blue_red_flash(int index) {
        if (index % 20 < 10) {
            this.setAll(255, 0, 0);
        } else {
            this.setAll(0, 0, 255);
    }
}

    public void setAll(int red, int green, int blue) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, red, green, blue);
        }
        led.setData(buffer);
    }
}
