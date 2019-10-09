package drone;

import java.awt.*;

import network.server.Server;

public class SimulationDrone implements Drone {
    int id;
    Server server;

    public SimulationDrone(int id, Server server){
        this.id = id;
        this.server = server;
    }

    @Override
    public void takeOff() {

    }

    @Override
    public void land() {

    }

    @Override
    public void hover() {

    }

    @Override
    public void move(int x, int y, int z) {
        server.send(x + "-" + y + "-" + z, id);
    }

    @Override
    public void changeLightVolume(int percentage) {

    }

    @Override
    public void changeLightColor(Color color) {

    }
}
