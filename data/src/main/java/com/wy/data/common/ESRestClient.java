package com.wy.data.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.Response;


/**
 * @author weiyang
 * @version 创建时间：2017年12月12日 下午3:34:55
 */
public class ESRestClient {
	
//	private static String ES_SERVER_IP = "192.168.1.252";
	private static String ES_SERVER_IP = "localhost";
	
	private static org.elasticsearch.client.RestClient client = org.elasticsearch.client.RestClient
			.builder(new HttpHost(ES_SERVER_IP, 9200, "http")).build();

	public static org.elasticsearch.client.RestClient client() {
		return client;
	}

	public static Response post(String endpoint, String json) {
		Header header = new BasicHeader("Content-Type", "application/json");
		HttpEntity httpEntity = new StringEntity(json, "UTF-8");
		try {
			return client.performRequest("post", endpoint, Collections.<String, String>emptyMap(), httpEntity, header);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Response get(String endpoint) {
		try {
			return client.performRequest("get", endpoint);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String postAsJson(String endpoint, String json) {
		Response response = post(endpoint, json);
		
		try {
			return inputStream2String(response.getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String inputStream2String(InputStream in) {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		try {
			for (int n; (n = in.read(b)) != -1;) {
				out.append(new String(b, 0, n,"utf-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toString();
	}

	public static void main(String[] args) throws UnsupportedOperationException, IOException {

		String json = "{\"aggs\":{\"group_by_source\":{\"terms\":{\"field\":\"_index\"}}},\"sort\":{\"_index\":\"desc\"},\"size\":0}";
		Response response = post("_search", json);
		System.out.println(inputStream2String(response.getEntity().getContent()));
	}
}
