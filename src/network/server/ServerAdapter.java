package network.server;

import network.client.ClientCommand;
import network.common.Command;

public class ServerAdapter implements ServerListener {

	@Override
	public void clientConnected(Server server, Server.ConnectionToClient client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageReceived(Server server, Server.ConnectionToClient client, Object msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commandReceived(Server server, Server.ConnectionToClient client, Command cmd) {
		if (cmd == ClientCommand.DISCONNECT) {
			client.localShutDown();
		}
	}

	@Override
	public void clientDisconnected(Server server, Server.ConnectionToClient client) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageSent(Server server, Server.ConnectionToClient toClient, Object msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commandSent(Server server, Server.ConnectionToClient toClient, Command cmd) {
		// TODO Auto-generated method stub
		
	}

}
