package network.common;

import java.io.Serializable;

/**
 * 
 * @author Mordechai
 *
 */
public enum Command implements Serializable {
    HANDSHAKE, CONNECTED, ERROR_CONNECTION, DISCONNECTED, DISCONNECT, REJECT_CONNECTION
}
