package br.com.trendsoftware.restProvider.http.client;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClientConfig;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.Response;
import org.asynchttpclient.channel.ChannelPool;
import org.asynchttpclient.netty.channel.DefaultChannelPool;

import br.com.trendsoftware.restProvider.exception.RestClientException;
import io.netty.util.HashedWheelTimer;

public class RestClient {

	public static AsyncHttpClient http;

	static {		
		HashedWheelTimer timer = new HashedWheelTimer();
		timer.start();
		
		ChannelPool channelPool = new DefaultChannelPool(60000, -1, DefaultChannelPool.PoolLeaseStrategy.FIFO, timer, 0);

		DefaultAsyncHttpClientConfig cf = Dsl.config()
				.setKeepAlive(true)
				.setMaxConnections(10)
				.setConnectionPoolCleanerPeriod(10)
				.setChannelPool(channelPool)
				.build();
		
		http =  Dsl.asyncHttpClient(cf);
	} 

	public static AsyncHttpClient getHttp() {
		return http;
	}

	private static BoundRequestBuilder prepareGet(String apiUrl) 
	{
		BoundRequestBuilder requestBuilder = http.prepareGet(apiUrl);
		setDefaulRequestHeader(requestBuilder);
		return requestBuilder;
	}
	
	private static BoundRequestBuilder prepareGet(String apiUrl,String path, Map<String, List<String>> params) {

		BoundRequestBuilder requestBuilder = http.prepareGet(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		requestBuilder.setQueryParams(params);
		return requestBuilder;
	}

	private static BoundRequestBuilder prepareGet(String apiUrl, Map<String,String> headers) {
		BoundRequestBuilder requestBuilder = http.prepareGet(apiUrl);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		return requestBuilder;
	}
	
	private static BoundRequestBuilder prepareGet(String apiUrl,String path, Map<String, List<String>> params, Map<String,String> headers) {
		BoundRequestBuilder requestBuilder = http.prepareGet(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		requestBuilder.setQueryParams(params);
		addHeaders(requestBuilder,headers);
		return requestBuilder;
	}

	private static BoundRequestBuilder prepareDelete(String apiUrl,String path,Map<String, List<String>> params) {

		BoundRequestBuilder requestBuilder = http.prepareDelete(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;

	}

	private static BoundRequestBuilder prepareDelete(String apiUrl,String path,Map<String, List<String>> params,Map<String,String> headers) {

		BoundRequestBuilder requestBuilder = http.prepareDelete(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;

	}

	private static BoundRequestBuilder prepareDelete(String apiUrl,String path) {

		BoundRequestBuilder requestBuilder = http.prepareDelete(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		return requestBuilder;
	}

	private static BoundRequestBuilder preparePost(String apiUrl,String path, Map<String, List<String>> params, String body) {
		BoundRequestBuilder requestBuilder = http.preparePost(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		if(body!=null && !body.isEmpty()){
			requestBuilder.setBody(body);
		}
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}

	private static BoundRequestBuilder preparePost(String apiUrl,String path, Map<String, List<String>> params, Map<String,String> headers, String body) {
		BoundRequestBuilder requestBuilder = http.preparePost(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		if(body!=null && !body.isEmpty())
			requestBuilder.setBody(body);
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}

	private static BoundRequestBuilder preparePut(String apiUrl,String path, Map<String, List<String>> params, String body) {
		BoundRequestBuilder requestBuilder = http.preparePut(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		requestBuilder.setBody(body);
		if(body!=null && !body.isEmpty()){
			requestBuilder.setBody(body);
		}
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}

	private static BoundRequestBuilder preparePut(String apiUrl,String path, Map<String, List<String>> params,Map<String,String> headers,String body) {
		BoundRequestBuilder requestBuilder = http.preparePut(apiUrl + path);
		setDefaulRequestHeader(requestBuilder);
		addHeaders(requestBuilder,headers);
		if(body!=null && !body.isEmpty()){
			requestBuilder.setBody(body);		}
		if(params!=null)
			requestBuilder.setQueryParams(params);
		return requestBuilder;
	}

	private static void setDefaulRequestHeader(BoundRequestBuilder requestBuilder){
		requestBuilder.addHeader("Accept", "application/json");
		requestBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
	}


	private static void addHeaders(BoundRequestBuilder requestBuilder, Map<String,String> headers){
		if(!headers.isEmpty())
			headers.keySet().forEach(key -> requestBuilder.addHeader(key, headers.get(key)));	
	}

	public static Response get(String apiUrl,String path) throws RestClientException {
		return get(apiUrl,path, new HashMap<String, List<String>>());
	}

	public static Response get(String apiUrl,Map<String,String> headers) throws RestClientException {

		BoundRequestBuilder r = prepareGet(apiUrl,headers);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
	
	
	public static Response get(String apiUrl,String path, Map<String, List<String>> params, Map<String,String> headers) throws RestClientException {

		BoundRequestBuilder r = prepareGet(apiUrl,path,params,headers);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
	
	public static Response get(String apiUrl) throws RestClientException {

		BoundRequestBuilder r = prepareGet(apiUrl);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}


	public static Response get(String apiUrl,String path, Map<String, List<String>> params) throws RestClientException {

		BoundRequestBuilder r = prepareGet(apiUrl,path, params);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public static Response post(String apiUrl,String path, Map<String, List<String>> params, String body) throws RestClientException {

		BoundRequestBuilder r = preparePost(apiUrl,path, params, body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}


		return response;
	}

	public static Response post(String apiUrl, String path, Map<String, List<String>> params,Map<String,String> headers, String body) throws RestClientException {

		BoundRequestBuilder r = preparePost(apiUrl,path, params, headers, body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}


		return response;
	}

	public static Response put(String apiUrl, String path, Map<String, List<String>> params, String body) throws RestClientException {

		BoundRequestBuilder r = preparePut(apiUrl,path, params, body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public static Response put(String apiUrl, String path, Map<String, List<String>> params, Map<String,String> headers, String body) throws RestClientException {

		BoundRequestBuilder r = preparePut(apiUrl,path, params,headers,body);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public static Response delete(String apiUrl, String path, Map<String, List<String>> params,Map<String,String> headers) throws RestClientException {
		BoundRequestBuilder r = prepareDelete(apiUrl,path, params,headers);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public static Response delete(String apiUrl, String path, Map<String, List<String>> params) throws RestClientException {
		BoundRequestBuilder r = prepareDelete(apiUrl,path, params);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}

	public static Response delete(String apiUrl, String path) throws RestClientException {
		BoundRequestBuilder r = prepareDelete(apiUrl,path);

		Response response;
		try {
			response = r.execute().get();
		} catch (Exception e) {
			throw new RestClientException(e.getMessage());
		}

		return response;
	}
}
