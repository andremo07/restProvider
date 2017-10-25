package br.com.trendsoftware.restProvider.exception;

public class ServiceException extends Exception
{
	private static final long serialVersionUID = 8573937163243243543L;
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(String message, Throwable ex) {
		super(message, ex);
	}
}
