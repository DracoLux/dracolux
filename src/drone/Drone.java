package drone;

import java.awt.*;

public interface Drone {

    void takeOff();

    void land();

    void hover();

    void move(float x, float y, float z);

    void changeLightVolume(int percentage);

    void changeLightColor(Color color);

}
