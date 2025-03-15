package es.ubu.lsi.server;

import es.ubu.lsi.common.ChatMessage;

import java.io.IOException;
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
            serverSocket = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port);

            while (alive) {
                Socket clientSocket = serverSocket.accept();
                if (!alive) break;

                ServerThreadForClient clientThread = new ServerThreadForClient(clientSocket, clientId++);
                clients.add(clientThread);
                clientThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }

	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	@Override
	public void broadcast(ChatMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(int id) {
		// TODO Auto-generated method stub

	}
	
	public class ServerThreadForClient extends Thread {

	}


}
