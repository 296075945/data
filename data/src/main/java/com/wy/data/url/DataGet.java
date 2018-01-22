package com.wy.data.url;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alibaba.fastjson.JSON;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.wy.data.common.ESRestClient;
import com.wy.data.common.HttpUtils;

/**
 * @author wy
 * @version 创建时间：2018年1月22日 上午11:53:39
 */
public class DataGet {
	public static void main(String[] args) throws IOException {
		URL url = get("https://www.99114.com");
		for(String s : url.links){
			try{
				URL u =get(s);
				add(u);
			}catch(Exception e){
				
			}
			
		}
//		add(url);
		ESRestClient.client().close();
	}
	public static URL get(String url){
		URL u = new URL();
		u.url = url;
		try {
			HttpResponse<String> response = HttpUtils.get(url);
			u.status = response.getStatus();
			String body = response.getBody();
			Pattern title = Pattern.compile("<title>(.*)</title>");
			Matcher m = title.matcher(body);
			if(m.find()){
				u.title = m.group(1);
			}
			Pattern links = Pattern.compile("<a.*href=\"(.*?)\".*>(.*)</a>");
			Matcher m2 = links.matcher(body);
			Set<String> set  = new HashSet<String>();
			while(m2.find()){
				set.add(m2.group(1));
			}
			u.links = set;
		} catch (UnirestException e) {
			u.status = -1; 
			e.printStackTrace();
		}
		return u;
	}
	public static void add(URL url){
		System.out.println(JSON.toJSONString(url));
		String s =ESRestClient.postAsJson("url/doc", JSON.toJSONString(url));
		System.out.println(s);
		
	}
}
