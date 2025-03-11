package es.ubu.lsi.client;

import java.io.*;
import java.net.*;

import es.ubu.lsi.common.ChatMessage;

public class ChatClientImpl implements ChatClient {
	
	private String server;
	
	private String username;
	
	private int port = 1500;
	
	private Socket socket;
	
	private ObjectOutputStream outputStream;
   
	private BufferedReader consoleInput;
	
	public ChatClientImpl (String server, int port, String username) {
		
	 this.server = (server == null || server.isEmpty()) ? "localhost" : server;
	 this.username = username;
		
	}

	@Override
	public boolean start() {
		try {
			
			//Conexión con el servidor
			socket = new Socket(server,port);
			
			//Se informa de la conexión
            System.out.println("Conectado a " + server + " como " + username);
            
            //Se crea flujo de salida de mensajes
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            
            //Hilo de escucha
            Thread listener = new Thread(new ChatClientListener());
            
            //Se lanza la escucha
            listener.run();
            
            return true;
            
        } catch (IOException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
            
            return false;
        }
		
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(ChatMessage msg) {
		// TODO Auto-generated method stub
		
	}
	
	

}
