import drone.MAVLinkDrone;
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
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        boolean useRealDrone = true;
        List<NavigationPilot> navigationPilots;

        if (useRealDrone){
            navigationPilots = useArdupilot();
        } else {
            navigationPilots = useSimulation();
        }

        // TODO: This is where shapes and light patterns are decided and generated.
        CommanderPilot commanderPilot = new CommanderPilot(navigationPilots);

        // Generate formations here arbitrarily.
        Formation formation1 = FormationGenerator.generateFormation(navigationPilots.size(), Shape.LINE, Color.PINK, LightPattern.CONSTANT, 4);
        commanderPilot.addFormation(formation1);
        Formation formation2 = FormationGenerator.generateFormation(navigationPilots.size(), Shape.SQUARE, Color.YELLOW, LightPattern.BLINK, 10);
        commanderPilot.addFormation(formation2);
        Formation formation3 = FormationGenerator.generateFormation(navigationPilots.size(), Shape.NUMBER_ONE, Color.GREEN, LightPattern.BLINK, 8);
        commanderPilot.addFormation(formation3);

        try {
            commanderPilot.runFormation();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static List<NavigationPilot> useSimulation(){
        Server server = new Server(5000, new ServerAdapter());
        server.start();

        // Wait for drones to connect
        int numberOfDrones = 30;
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
        System.out.println("We received " + navigationPilots.size() + " drones.");

        return navigationPilots;
    }

    private static List<NavigationPilot> useArdupilot() {
        List<NavigationPilot> navigationPilots = new ArrayList<>();
  
        try (Socket socket = new Socket("127.0.0.1", 5760)){
            MAVLinkDrone drone = new MAVLinkDrone(1, 0, socket.getInputStream(), socket.getOutputStream());
            Coordinate coordinate = drone.getCoordinates();
            navigationPilots.add(new NavigationPilot(drone, coordinate));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return navigationPilots;
    }

}
