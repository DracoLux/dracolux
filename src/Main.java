import drone.SimulationDrone;
import enumerators.LightPattern;
import network.common.Coordinate;
import enumerators.Shape;
import formation.Formation;
import formation.FormationGenerator;
import network.server.Server;
import network.server.ServerAdapter;
import pilot.CommanderPilot;
import pilot.NavigationPilot;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Server server = new Server(5000, new ServerAdapter());
        server.start();

        // Wait for drones to connect
        int numberOfDrones = 10;
        int i = 0;
        while (server.getClients().size() < numberOfDrones)
        try {
            System.out.println(++i + "...");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<NavigationPilot> navigationPilots = new ArrayList<>();

        Collection<Server.ConnectionToClient> clients = server.getClients();
        for (Server.ConnectionToClient c : clients){
            int id = c.getClientId();
            navigationPilots.add(new NavigationPilot(new SimulationDrone(id, server), new Coordinate(0, 0, 0)));
        }

        // TODO: This is where shapes and light patterns are decided and generated.
        CommanderPilot commanderPilot = new CommanderPilot(navigationPilots);

        // Generate formations here arbitrarily.
        Formation formation1 = FormationGenerator.generateFormation(navigationPilots.size(), Shape.SQUARE, Color.RED, LightPattern.CONSTANT, 2);
        commanderPilot.addFormation(formation1);
        Formation formation2 = FormationGenerator.generateFormation(navigationPilots.size(), Shape.CIRCLE, Color.YELLOW, LightPattern.BLINK, 4);
        commanderPilot.addFormation(formation2);
        Formation formation3 = FormationGenerator.generateFormation(navigationPilots.size(), Shape.SQUARE, Color.GREEN, LightPattern.BLINK, 2);
        commanderPilot.addFormation(formation3);

        try {
            commanderPilot.runFormation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            server.shutDown();
        }

    }

}
