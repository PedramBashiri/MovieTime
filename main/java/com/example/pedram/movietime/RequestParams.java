package com.example.pedram.movietime;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RequestParams {
	String baseUrl;
	List<String> singleParams = new ArrayList<String>();
	
	public RequestParams(String method, String baseUrl) {
		super();
		this.baseUrl = baseUrl;
	}


	public void addParam(String value) {
		singleParams.add(value);
	}
	
	public String getEncodedParams() {
		StringBuilder sb = new StringBuilder();
		for (String value : singleParams) {
			
			sb.append("/");
			sb.append(value);
		}
		return sb.toString();
	}

	public String getEncodedUrl() {
		return this.baseUrl + getEncodedParams();
	}
}