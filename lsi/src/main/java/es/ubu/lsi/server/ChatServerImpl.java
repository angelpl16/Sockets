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

	private Map<Integer, ServerThreadForClient> clients;

	private SimpleDateFormat sdf;

	private ServerSocket serverSocket;

	public ChatServerImpl(int port) {
		this.port = port;
		this.clients = new HashMap<>();
		this.sdf = new SimpleDateFormat("HH:mm:ss");
		this.alive = true;
	}

	@Override
	public void startup() {
		try {
			alive = true;
			serverSocket = new ServerSocket(port);

			while (alive) {
				Socket socket = serverSocket.accept();

				ServerThreadForClient clientThread = new ServerThreadForClient(socket);

				clients.put(clientId, clientThread);
				clientThread.start();
			}
		} catch (IOException e) {
			System.err.println("Error en la conexión con el cliente " + clientId + ": " + e.getMessage());
		}

	}

	@Override
	public void shutdown() {
		try {
			alive = false;
			if (serverSocket != null && !serverSocket.isClosed()) {
				serverSocket.close();

			}
		} catch (IOException e) {
			System.err.println("Error al cerrar el ServerSocket: " + e.getMessage());
		}
	}

	@Override
	public void broadcast(ChatMessage message) {
		// Hay que enviar mensajes a todos los clientes de la lista 'Clientes'.
		// El tipo de los valores necesita una funcion enviar.

		for (ServerThreadForClient client : clients.values()) {

			client.sendMessage(message);

		}
	}

	@Override
	public void remove(int id) {
		try {
			if (clients.containsKey(id)) {

			}
		} catch (IOException e) {

		}

	}

	public class ServerThreadForClient extends Thread {

		private Socket socket;

		public ServerThreadForClient(Socket socket) {
			this.socket = socket;

		}

		public void run() {

		}

		public void sendMessage(ChatMessage message) {

		}

		public void disconnect() {

		}

	}

}
