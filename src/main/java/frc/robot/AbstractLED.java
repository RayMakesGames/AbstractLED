package frc.robot;
import java.util.Random;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

public class AbstractLED {
    private final AddressableLED led;
    private final AddressableLEDBuffer buffer = new AddressableLEDBuffer(14);

    public AbstractLED(int port) {
        led = new AddressableLED(port);
        led.setLength(buffer.getLength());
        led.setData(buffer);
        led.start();
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

    public void patternOne(int index) {
        if (index % 20 < 10) {
            this.setAll(255, 0, 0);
        } else {
            this.setAll(0, 0, 255);
    }
}

    public void setRandom(int index) {
        Random rand = new Random();
        int red = rand.nextInt(0, 255);
        int green = rand.nextInt(0, 255);
        int blue = rand.nextInt(0, 255);

        buffer.setRGB(index, red, green, blue);
    }

    public void setAll(int red, int green, int blue) {
        for (int i = 0; i < buffer.getLength(); i++) {
            buffer.setRGB(i, red, green, blue);
        }
        led.setData(buffer);
    }
}
