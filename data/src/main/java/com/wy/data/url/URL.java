package com.wy.data.url;

import java.util.Set;

/**
 * @author wy
 * @version 创建时间：2018年1月22日 下午1:20:31
 */
public class URL{
	String url;
	int status;
	String title;
	Set<String> links;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Set<String> getLinks() {
		return links;
	}
	public void setLinks(Set<String> links) {
		this.links = links;
	}
			
}
