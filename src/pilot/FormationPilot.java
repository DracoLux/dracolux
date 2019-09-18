package pilot;

import dumb.Coordinate;
import formation.Formation;
import timeline.Event;

import java.util.ArrayList;
import java.util.List;

public class FormationPilot {
    private List<NavigationPilot> pilots;

    public FormationPilot(){
        this.pilots = new ArrayList<>();
    }

    public void addPilot(NavigationPilot pilot){
        pilots.add(pilot);
    }

    public void showEvent(Event event){
        List<Coordinate> coordinates = event.formation.createFormation(pilots.size());
        for (int i = 0; i < pilots.size(); i++){
            NavigationPilot pilot = pilots.get(i);
            pilot.moveTo(coordinates.get(i));
            pilot.changeLights(event.lightVolume,event.color);
            pilot.showLightPattern(event.lightPattern);
            /*TODO: Make sure that the drones do not interfere with each other
            * TODO: Move drones to the closest point
            * TODO: Parallelize*/
        }

    }

}
