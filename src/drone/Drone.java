package drone;

import java.awt.*;

public interface Drone {

    void takeOff();

    void land();

    void hover();

    void move(float x, float y, float z);

    void changeLight(Color color, int volumePercentage);

}
