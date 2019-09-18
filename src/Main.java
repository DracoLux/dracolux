import drone.Drone;
import drone.DumbDrone;
import formation.SquareFormation;
import pilot.ShowPilot;
import timeline.Event;
import timeline.Timeline;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Drone> drones = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            drones.add(new DumbDrone());
        }

        ShowPilot showPilot = new ShowPilot(drones);

    }
}
