package frc.robot;

import java.util.ArrayList;

public class AbstractLEDController {
    ArrayList<AbstractLED> leds = new ArrayList<AbstractLED>();

    public void addLED(AbstractLED led) {
        leds.add(led);
    }

    public AbstractLED getLED(int index) {
        return leds.get(index);
    }

    public void removeLED(int index) {
        leds.remove(index);
    }
}
