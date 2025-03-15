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
		try {
            if (socket != null && !socket.isClosed()) {
            	//Se cierra el socket para desconectar del servidor
                socket.close();
                System.out.println("Desconectado del servidor.");
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el socket: " + e.getMessage());
        }

	}

	@Override
	public void sendMessage(ChatMessage msg) {
		try {
			outputStream.writeObject("Angel P." + msg);
			outputStream.flush();
		} catch (IOException e) {
			 System.out.println("Error al enviar mensaje: " + e.getMessage());
		}
		
	}
	
	public class ChatClientListener implements Runnable {

		@Override
		public void run() {
			try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream())) {
	            while (true) {
	                try {
	                    // Se lee un objeto desde la entrada
	                    Object received = inputStream.readObject();

	                    // Se verifica el tipo de objeto obtenido para saber si es un mensaje
	                    if (received instanceof ChatMessage) {
	                        ChatMessage msg = (ChatMessage) received;

	                        // Comprobamos el tipo de mensaje
	                        switch (msg.getType()) {
	                            case MESSAGE:
	                                System.out.println("Usuario " + msg.getId() + ": " + msg.getMessage());
	                                break;
	                            case LOGOUT:
	                                System.out.println("El servidor ha solicitado tu desconexión.");
	                                disconnect();
	                                return; // Termina el hilo
	                            case SHUTDOWN:
	                                System.out.println("El servidor se está apagando. Se cerrará la conexión.");
	                                disconnect();
	                                return; // Termina el hilo
	                            default:
	                                System.out.println("Mensaje recibido con un tipo desconocido.");
	                        }
	                    } else {
	                        System.out.println("Mensaje recibido con formato no reconocido.");
	                    }
	                } catch (ClassNotFoundException e) {
	                    System.err.println("Error: No se pudo interpretar el mensaje recibido.");
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Conexión cerrada o error en el flujo de entrada: " + e.getMessage());
	        }
		}

	}
	
	

}
