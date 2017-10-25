package br.com.trendsoftware.restProvider.exception;

public class RestClientException extends Exception
{
	private static final long serialVersionUID = 8573937163243243543L;
	
	public RestClientException(String message) {
		super(message);
	}
	
	public RestClientException(String message, Throwable ex) {
		super(message, ex);
	}
}
