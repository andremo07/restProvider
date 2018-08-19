package br.com.trendsoftware.restProvider.http.client;


import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.AsyncHttpClient.BoundRequestBuilder;
import com.ning.http.client.AsyncHttpClientConfig;
import com.ning.http.client.FluentCaseInsensitiveStringsMap;
import com.ning.http.client.FluentStringsMap;
import com.ning.http.client.Response;

import br.com.trendsoftware.restProvider.exception.RestClientException;

public abstract class RestClient {

	private AsyncHttpClient http;

	{
		AsyncHttpClientConfig cf = new AsyncHttpClientConfig.Builder()
				.setUserAgent("B2W-JAVA-SDK-0.0.1").build();
		http = new AsyncHttpClient(cf);
	} 

	public AsyncHttpClient getHttp() {
		return http;
	}

	public void setHttp(AsyncHttpClient http) {
		this.http = http;
	}

	private BoundRequestBuilder prepareGet(String path, FluentStringsMap params) {

		BoundRequestBuilder requestBuilder = http.prepareGet(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		requestBuilder.setQueryParams(params);
		return requestBuilder;
	}
	
	private BoundRequestBuilder prepareGet(String path, FluentStringsMap params, FluentCaseInsensitiveStringsMap headers) {
		BoundRequestBuilder requestBuilder = http.prepareGet(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		requestBuilder.setQueryParams(params);
		addHeaders(requestBuilder,headers);
		return requestBuilder;
	}

	private BoundRequestBuilder prepareDelete(String path,FluentStringsMap params) {

		BoundRequestBuilder requestBuilder = http.prepareDelete(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
		
	}
	
	private BoundRequestBuilder prepareDelete(String path,FluentStringsMap params,FluentCaseInsensitiveStringsMap headers) {

		BoundRequestBuilder requestBuilder = http.prepareDelete(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
		
	}
	
	private BoundRequestBuilder prepareDelete(String path) {

		BoundRequestBuilder requestBuilder = http.prepareDelete(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		return requestBuilder;
	}

	private BoundRequestBuilder preparePost(String path, FluentStringsMap params, String body) {
		BoundRequestBuilder requestBuilder = http.preparePost(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		if(body!=null && !body.isEmpty()){
			requestBuilder.setBody(body);
			requestBuilder.setBodyEncoding("UTF-8");
		}
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}
	
	private BoundRequestBuilder preparePost(String path, FluentStringsMap params, FluentCaseInsensitiveStringsMap headers, String body) {
		BoundRequestBuilder requestBuilder = http.preparePost(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		if(body!=null && !body.isEmpty())
			requestBuilder.setBody(body);
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}
	
	private BoundRequestBuilder preparePut(String path,FluentStringsMap params, String body) {
		BoundRequestBuilder requestBuilder = http.preparePut(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		requestBuilder.setBody(body);
		if(body!=null && !body.isEmpty()){
			requestBuilder.setBody(body);
			requestBuilder.setBodyEncoding("UTF-8");
		}
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}
	
	private BoundRequestBuilder preparePut(String path,FluentStringsMap params,FluentCaseInsensitiveStringsMap headers,String body) {
		BoundRequestBuilder requestBuilder = http.preparePut(getApiUrl() + path);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		if(body!=null && !body.isEmpty()){
			requestBuilder.setBody(body);
			requestBuilder.setBodyEncoding("UTF-8");
		}
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}

	public void setDefaulRequestHeader(BoundRequestBuilder requestBuilder){
		FluentCaseInsensitiveStringsMap headersMap = new FluentCaseInsensitiveStringsMap();
		headersMap.add("Accept", "application/json");
		headersMap.add("Content-Type", "application/json; charset=utf-8");
		requestBuilder.setHeaders(headersMap);
	}
	
	
	public void addHeaders(BoundRequestBuilder requestBuilder, FluentCaseInsensitiveStringsMap headers){
		if(!headers.isEmpty())
			headers.keySet().forEach(key -> requestBuilder.addHeader(key, headers.getFirstValue(key)));	
	}
	
	public Response get(String path) throws RestClientException {
		return get(path, new FluentStringsMap());
	}

	public Response get(String path, FluentStringsMap params, FluentCaseInsensitiveStringsMap headers) throws RestClientException {

		BoundRequestBuilder r = prepareGet(path,params,headers);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public Response get(String path, FluentStringsMap params) throws RestClientException {

		BoundRequestBuilder r = prepareGet(path, params);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public Response post(String path, FluentStringsMap params, String body) throws RestClientException {

		BoundRequestBuilder r = preparePost(path, params, body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}


		return response;
	}
	
	
	public Response post(String path, FluentStringsMap params,FluentCaseInsensitiveStringsMap headers, String body) throws RestClientException {

		BoundRequestBuilder r = preparePost(path, params, headers, body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}


		return response;
	}

	public Response put(String path, FluentStringsMap params, String body) throws RestClientException {

		BoundRequestBuilder r = preparePut(path, params, body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
	
	public Response put(String path, FluentStringsMap params, FluentCaseInsensitiveStringsMap headers, String body) throws RestClientException {

		BoundRequestBuilder r = preparePut(path, params,headers,body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public Response delete(String path, FluentStringsMap params,FluentCaseInsensitiveStringsMap headers) throws RestClientException {
		BoundRequestBuilder r = prepareDelete(path, params,headers);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
	
	public Response delete(String path, FluentStringsMap params) throws RestClientException {
		BoundRequestBuilder r = prepareDelete(path, params);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
	
	public Response delete(String path) throws RestClientException {
		BoundRequestBuilder r = prepareDelete(path);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
	
	public abstract String getApiUrl();

}
