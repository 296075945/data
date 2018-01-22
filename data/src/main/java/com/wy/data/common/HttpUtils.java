package com.wy.data.common;

import java.io.IOException;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpUtils {
	public static void main(String[] args) throws UnirestException, IOException {
		HttpResponse<String> response = get("http://www.baidu.com");
		System.out.println(response.getBody().length());
		System.out.println(response.getRawBody().available());
	}
	/**
	 * 超时时间
	 */
	public static final long TIMEOUT = 5*1000;
	static {
		Unirest.setTimeouts(TIMEOUT, TIMEOUT);
	}
	public static HttpResponse<String> get(String url) throws UnirestException{
			HttpResponse<String> response = Unirest.get(url)
					  .header("cache-control", "no-cache")
					  .asString();
			return response;
	}
	public static HttpResponse<String> post(String url) throws UnirestException{
			HttpResponse<String> response = Unirest.post(url)
					  .header("cache-control", "no-cache")
					  .asString();
			return response;
	}
}
