package es.ubu.lsi.client;

import es.ubu.lsi.common.ChatMessage;

public interface ChatClient {

	/**
	 * Inicia el cliente de chat.
	 * 
	 * @return true si el cliente se inicia correctamente, false en caso contrario.
	 */
	boolean start();

	/**
	 * Envía un mensaje a través del cliente de chat.
	 * 
	 * @param msg el mensaje a enviar.
	 */
	void sendMessage(ChatMessage msg);

	/**
	 * Desconecta el cliente de chat.
	 */
	void disconnect();

}
