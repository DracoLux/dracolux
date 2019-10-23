package drone;

import java.awt.*;

import network.common.Coordinate;
import network.common.LightChange;
import network.server.Server;
import network.server.ServerCommand;

public class SimulationDrone implements Drone {
    int id;
    Server server;

    public SimulationDrone(int id, Server server){
        this.id = id;
        this.server = server;
    }

    @Override
    public void takeOff() {
        server.send(ServerCommand.TAKEOFF, id);
    }

    @Override
    public void land() {
        server.send(ServerCommand.LAND, id);
    }

    @Override
    public void hover() {
        server.send(ServerCommand.HOVER, id);
    }

    @Override
    public void move(float x, float y, float z) {
        server.send(new Coordinate(x,y,z), id);
    }

    @Override
    public void changeLight(Color color, int volumePercentage) {
        server.send(new LightChange(color, volumePercentage), id);
    }
}
