package es.ubu.lsi.server;

public class ClientNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClientNotFoundException(String message) {
        super(message);
    }
}
