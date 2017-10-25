package br.com.trendsoftware.restProvider.exception;

public class ProviderException extends Exception 
{
	private static final long serialVersionUID = 7143589772289057283L;
	
	private String code;
	
	private String codeMessage;

	public ProviderException(String failure) {
		super(failure);
		this.setCodeMessage(failure);
	}
	
	public ProviderException(String failure, String code, String codeMessage) {
		super(failure);
		this.setCode(code);
		this.setCodeMessage(codeMessage);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeMessage() {
		return codeMessage;
	}

	public void setCodeMessage(String codeMessage) {
		this.codeMessage = codeMessage;
	}
}
