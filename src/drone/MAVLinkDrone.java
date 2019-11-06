package drone;

import io.dronefleet.mavlink.MavlinkConnection;
import io.dronefleet.mavlink.MavlinkMessage;
import io.dronefleet.mavlink.common.CommandInt;
import io.dronefleet.mavlink.common.GlobalPositionInt;
import network.common.Coordinate;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;

import static io.dronefleet.mavlink.common.MavCmd.*;

public class MAVLinkDrone implements Drone {
    private int systemId;
    private int componentId;
    private MavlinkConnection connection;

    public MAVLinkDrone(int systemId, int componentId, Socket socket) throws IOException {
        this.systemId = systemId;
        this.componentId = componentId;
        connection = MavlinkConnection.create(
                socket.getInputStream(),
                socket.getOutputStream());
    }

    public MavlinkConnection getConnection() {
        return connection;
    }

    private void sendCommand(CommandInt cmd){
        try {
            connection.send2(systemId,componentId,cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void takeOff() {
        CommandInt cmd = CommandInt.builder()
                            .command(MAV_CMD_NAV_TAKEOFF)
                            .build();
        sendCommand(cmd);
    }

    @Override
    public void land() {
        CommandInt cmd = CommandInt.builder()
                .command(MAV_CMD_NAV_RETURN_TO_LAUNCH)
                .build();
        sendCommand(cmd);
    }

    @Override
    public void hover() {
        CommandInt cmd = CommandInt.builder()
                .command(MAV_CMD_DO_PAUSE_CONTINUE)
                .param1(0)
                /* 0: Pause current mission or reposition command, hold current position.
                   1: Continue mission.
                   A VTOL capable vehicle should enter hover mode (multicopter and VTOL planes).
                   A plane should loiter with the default loiter radius.*/
                .build();
        sendCommand(cmd);
    }

    @Override
    public void move(float x, float y, float z) {
        CommandInt cmd = CommandInt.builder()
                .command(MAV_CMD_NAV_WAYPOINT)
                .x((int) x)
                .y((int) y)
                .z((int) z)
                .build();
        sendCommand(cmd);
    }

    @Override
    public void changeLight(Color color, int volumePercentage) {

    }

    public Coordinate getCoordinates() throws IOException {
        MavlinkMessage msg;
        do {
            msg = connection.next();
            if (msg == null) continue;
            if (!(msg.getPayload() instanceof GlobalPositionInt)) continue; // skip message
            GlobalPositionInt position = (GlobalPositionInt) msg.getPayload();
            return new Coordinate(position.lat(), position.alt(), position.lon());
        } while (true);
    }
}
