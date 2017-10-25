package br.com.trendsoftware.restProvider.response;

public abstract class RestResponse 
{
	private String body;
	
	private Integer statusCode;
	
	private String statusMessage;
	
	private String contentType;
	
	private long timeLapsed;
	
	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	public String getStatusMessage() {
		return statusMessage;
	}
	
	public long getTimeLapsed() {
		return timeLapsed;
	}

	public void setTimeLapsed(long timeLapsed) {
		this.timeLapsed = timeLapsed;
	}

	public abstract boolean isResponseOK();
}
