import io.dronefleet.mavlink.Mavlink2Message;
import io.dronefleet.mavlink.MavlinkConnection;
import io.dronefleet.mavlink.MavlinkMessage;
import io.dronefleet.mavlink.common.Heartbeat;
import io.dronefleet.mavlink.common.MavAutopilot;
import io.dronefleet.mavlink.common.MavState;
import io.dronefleet.mavlink.common.MavType;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainMAVLink {
    public static void main(String[] args) {
        // This example uses a TCP socket, however we may also use a UDP socket by injecting
// PipedInputStream/PipedOutputStream to MavlinkConnection, or even USB by using any
// implementation that will eventually yield an InputStream and an OutputStream.
        try (Socket socket = new Socket("127.0.0.1", 5000)) {
            // After establishing a connection, we proceed to building a MavlinkConnection instance.
            MavlinkConnection connection = MavlinkConnection.create(
                    socket.getInputStream(),
                    socket.getOutputStream());

            // Now we are ready to read and send messages.
            MavlinkMessage message;
            while ((message = connection.next()) != null) {
                // The received message could be either a Mavlink1 message, or a Mavlink2 message.
                // To check if the message is a Mavlink2 message, we could do the following:
                if (message instanceof Mavlink2Message) {
                    // This is a Mavlink2 message.
                    Mavlink2Message message2 = (Mavlink2Message)message;

                    if (message2.isSigned()) {
                        // This is a signed message. Let's validate its signature.
                        byte[] mySecretKey = new byte[]{};
                        if (message2.validateSignature(mySecretKey)) {
                            // Signature is valid.
                        } else {
                            // Signature validation failed. This message is suspicious and
                            // should not be handled. Perhaps we should log this incident.
                        }
                    } else {
                        // This is an unsigned message.
                    }
                } else {
                    // This is a Mavlink1 message.
                }

                // When a message is received, its payload type isn't statically available.
                // We can resolve which kind of message it is by its payload, like so:
                if (message.getPayload() instanceof Heartbeat) {
                    // This is a heartbeat message
                    MavlinkMessage<Heartbeat> heartbeatMessage = (MavlinkMessage<Heartbeat>)message;
                }
                // We are better off by publishing the payload to a pub/sub mechanism such
                // as RxJava, JMS or any other favorite instead, though.
            }
        } catch (EOFException eof) {
            // The stream has ended.
        } catch (IOException e) {

        }
    }
}
