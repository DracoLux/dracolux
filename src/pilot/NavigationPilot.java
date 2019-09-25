package pilot;

import drone.Drone;
import formation.Coordinate;
import enumerators.LightPattern;

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
        drone.changeLightColor(color);
        switch (pattern) {
            /*TODO: Make some crazy amazing patterns in an async call */
        }
    }

}
