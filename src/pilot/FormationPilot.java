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

    public void runFormation(){
        List<Coordinate> coordinates = formation.coordinates;
        for (int i = 0; i < coordinates.size(); i++) {
            NavigationPilot navigationPilot = navigationPilots.get(i);
            navigationPilot.moveTo(formation.coordinates.get(i));
            navigationPilot.showLightPattern(formation.lightPattern, formation.color); // TODO: Different colors in same shape.
            /*TODO: Make sure that the drones do not interfere with each other
             * TODO: Move drones to the closest point
             * TODO: Parallelize*/
        }

    }

    // Check if there's an intersection between section c1 to c2 and section c3 to c4.
    static public boolean checkIntersection(Coordinate c1, Coordinate c2, Coordinate c3, Coordinate c4) {
        float t, u, denom, nume1, nume2;
        /* Make use of Bézier parameters to check intersection on section of coordinates from c1 to c2.
        If it exists within section, it must exist within other section as well.
         */
        // First check denominator. If it's 0, the lines are parallel.
        denom = (c1.x - c2.x) * (c3.y - c4.y) - (c1.y - c2.y) * (c3.x - c4.x);
        if (denom == 0) {
            return false;
        }
        nume1 = (c1.x - c3.x) * (c3.y - c4.y) - (c1.y - c3.y) * (c3.x - c4.x);
        t = nume1/denom;

        nume2 = (c1.x - c2.x) * (c1.y - c3.y) - (c1.y - c2.y) * (c1.x - c3.x);
        nume2 *= -1;
        u = nume2/denom;

        if ((0f < t && t < 1.0f) && (0f < u && u < 1.0f)) {
            return true;
        }
        return false;
    }

}
