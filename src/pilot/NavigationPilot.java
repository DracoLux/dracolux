package pilot;

import drone.Drone;
import dumb.Coordinate;
import dumb.LightPattern;

import java.awt.*;

public class NavigationPilot {
    private Drone drone;
    private Coordinate currentCoordinate;

    public NavigationPilot(Drone drone, Coordinate start){
        this.drone = drone;
        this.currentCoordinate = start;
    }

    public void moveTo(Coordinate coordinate){
        drone.moveX(0); /*TODO: Do some maths here*/
        drone.moveY(0);
        drone.moveZ(0);

        currentCoordinate = coordinate;
    }

    public void changeLights(int volume, Color color){
        drone.changeLightVolume(volume);
        drone.changeLightColor(color);
    }

    public void showLightPattern(LightPattern pattern){
        switch (pattern) {
            /*TODO: Make some crazy amazing patterns*/
        }
    }

}
