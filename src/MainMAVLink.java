import drone.MAVLinkDrone;
import enumerators.LightPattern;
import enumerators.Shape;
import formation.Formation;
import formation.FormationGenerator;
import network.common.Coordinate;
import pilot.CommanderPilot;
import pilot.NavigationPilot;

import java.awt.*;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MainMAVLink {
    public static void main(String[] args) {
        // This example uses a TCP socket, however we may also use a UDP socket by injecting
        // PipedInputStream/PipedOutputStream to MavlinkConnection, or even USB by using any
        // implementation that will eventually yield an InputStream and an OutputStream.
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            // After establishing a connection, we proceed to building a MavlinkConnection instance.

            List<NavigationPilot> navigationPilots = new ArrayList<>();

            for (int i = 0; i<1; i++) {
                MAVLinkDrone drone = new MAVLinkDrone(i,0,socket);
                Coordinate coordinate = drone.getCoordinates();
                navigationPilots.add(new NavigationPilot(drone, coordinate));
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

            commanderPilot.runFormation();

        } catch (EOFException eof) {
            // The stream has ended.
        } catch (IOException e) {

        } catch (InterruptedException e) {

        }
    }
}
