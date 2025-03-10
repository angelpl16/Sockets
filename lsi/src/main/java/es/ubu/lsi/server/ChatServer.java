package es.ubu.lsi.server;

import es.ubu.lsi.common.ChatMessage;

public interface ChatServer {
    /**
     * Inicia el servidor de chat.
     */
    void startup();

    /**
     * Apaga el servidor de chat.
     */
    void shutdown();

    /**
     * Envía un mensaje a todos los clientes conectados.
     * @param message El mensaje a enviar.
     */
    void broadcast(ChatMessage message);

    /**
     * Elimina un cliente del servidor usando su ID.
     * @param id El ID del cliente a eliminar.
     */
    void remove(int id);
}

