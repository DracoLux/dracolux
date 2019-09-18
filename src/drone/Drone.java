package drone;

import java.awt.*;

public interface Drone {

    void takeOff();

    void land();

    void hover();

    void moveX(int x);

    void moveY(int y);

    void moveZ(int z);

    void changeLightVolume(int percentage);

    void changeLightColor(Color color);

}
