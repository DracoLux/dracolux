import drone.DumbDrone;
import formation.Coordinate;
import enumerators.LightPattern;
import enumerators.Shape;
import formation.Formation;
import formation.FormationGenerator;
import pilot.CommanderPilot;
import pilot.NavigationPilot;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<NavigationPilot> navigationPilots = new ArrayList<NavigationPilot>();
        navigationPilots.add(new NavigationPilot(new DumbDrone(), new Coordinate(5, 6, 7)));
        navigationPilots.add(new NavigationPilot(new DumbDrone(), new Coordinate(3, 1, 4)));
        navigationPilots.add(new NavigationPilot(new DumbDrone(), new Coordinate(25, 36, 47)));


        // TODO: This is where shapes and light patterns are decided and generated.
        CommanderPilot commanderPilot = new CommanderPilot(navigationPilots);

        // Generate formations here arbritarily.
        for (int i = 0; i < 3; i++) {
            Formation formation = FormationGenerator.generateFormation(10 - i, Shape.SQUARE, Color.RED, LightPattern.BLINK, i * 2);
            commanderPilot.addFormation(formation);
        }

        commanderPilot.runFormation();

    }

}
