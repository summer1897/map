package com.zju.hpec.util;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtil {

	private static final String DEFAULT_RESULT = "fail";
	
	public static String do_get(String url){
		String result = DEFAULT_RESULT;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		int flag;
		try {
			flag = client.executeMethod(get);
			if(HttpStatus.SC_OK == flag)
				result = new String(get.getResponseBody(),"utf-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String do_post(String url,Map<String,String> params){
		String result = DEFAULT_RESULT;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		Set<String> keys = params.keySet();
		for(String key : keys){
			post.setParameter(key, params.get(key).toString());
		}
		int flag;
		try {
			flag = client.executeMethod(post);
			if(HttpStatus.SC_OK == flag)
				result = new String(post.getResponseBody(),"utf-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String do_post(String url){
		String result = DEFAULT_RESULT;
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(url);
		int flag;
		try {
			flag = client.executeMethod(post);
			if(HttpStatus.SC_OK == flag)
				result = new String(post.getResponseBody(),"utf-8");
		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
