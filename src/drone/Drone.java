package drone;

import java.awt.*;

public interface Drone {

    void takeOff();

    void land();

    void hover();

    void move(int x, int y, int z);

    void changeLightVolume(int percentage);

    void changeLightColor(Color color);

}
