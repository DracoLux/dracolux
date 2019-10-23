package pilot;

import formation.Coordinate;
import formation.Formation;

import java.util.ArrayList;
import java.util.List;

// Instantiated with a formation.
public class FormationPilot {
    private List<NavigationPilot> navigationPilots;
    private Formation formation;

    public FormationPilot(Formation formation){
        this.formation = formation;
        this.navigationPilots = new ArrayList<>();
    }

    public void addNavigationPilot(NavigationPilot pilot){
        navigationPilots.add(pilot);
    }

    public void runFormation() throws InterruptedException {
        List<Coordinate> coordinates = formation.coordinates;
        for (int i = 0; i < coordinates.size(); i++) {
            NavigationPilot navigationPilot = navigationPilots.get(i);
            navigationPilot.moveTo(formation.coordinates.get(i));
            navigationPilot.showLightPattern(formation.lightPattern, formation.color); // TODO: Different colors in same shape.
            /*TODO: Make sure that the drones do not interfere with each other
             * TODO: Move drones to the closest point
             * TODO: Parallelize*/
        }

        Thread.sleep(formation.durationSeconds*1000);
    }

}
