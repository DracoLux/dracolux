package pilot;

import drone.Drone;
import timeline.Event;
import timeline.Timeline;

import java.util.List;

public class ShowPilot {
    private List<Drone> drones;

    public ShowPilot(List<Drone> drones) {
        this.drones = drones;
    }

    public void showTimeline(Timeline timeline){
        while (!timeline.isEmpty()){
            Event nextEvent = timeline.poll();
            FormationPilot formationPilot = new FormationPilot();
            /*TODO: How do we create formations without being specific here?*/
            formationPilot.showEvent(nextEvent);
        }
    }
}
