package es.ubu.lsi.server;

import es.ubu.lsi.common.ChatMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatServerImpl implements ChatServer {
	
	public static final int DEFAULT_PORT = 1500;
	
	private int port;
	
	private boolean alive;
	
	private int clientId = 0;
	
	private List<ServerThreadForClient> clients;
	
	private SimpleDateFormat sdf;
	
	private ServerSocket serverSocket;
	
	public ChatServerImpl(int port) {
        this.port = port;
        this.clients = new ArrayList<>();
        this.sdf = new SimpleDateFormat("HH:mm:ss");
        this.alive = true;
    }
	
	@Override
	public void startup() {
		try {
			alive = true;
			serverSocket = new ServerSocket(port);
			
			while(alive) {
				Socket socket = serverSocket.accept();
				
				ServerThreadForClient clientThread = new ServerThreadForClient(socket, clientId++);
				
				clients.add(clientThread);
			}
		} catch (IOException e) {
			System.err.println("Error en la conexión con el cliente " + clientId + ": " + e.getMessage());
		}

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(ChatMessage message) {
		System.out.println("Broadcasting: " + message.getMessage());
        for (ServerThreadForClient client : clients) {
        	
        }
	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}
	
	public class ServerThreadForClient extends Thread {
		
		private Socket socket;
		
		private int clientId;
		
		public ServerThreadForClient(Socket socket, int clientId) {
			this.socket = socket;
			this.clientId = clientId;
			
		}
		
		public void run()

	}


}
