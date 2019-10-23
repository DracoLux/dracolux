package pilot;

import drone.Drone;
import enumerators.LightPattern;
import network.common.Coordinate;

import java.awt.*;

public class NavigationPilot {
    private Drone drone;
    private Coordinate location;

    public NavigationPilot(Drone drone, Coordinate start){
        this.drone = drone;
        this.location = start;
    }

    public void moveTo(Coordinate coordinate){
        drone.move(coordinate.x, coordinate.y, coordinate.z); /*TODO: Do some maths here*/
        location = coordinate;
    }

    public void showLightPattern(LightPattern pattern, Color color){
        drone.changeLight(color,100);
        switch (pattern) {
            /*TODO: Make some crazy amazing patterns in an async call */
            case CONSTANT: drone.changeLight(color,100); break;
            case BLINK: drone.changeLight(color,50); break;
        }
    }

}
