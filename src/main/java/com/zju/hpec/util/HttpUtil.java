package com.zju.hpec.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

public class HttpUtil {

	public static final String DEFAULT_RESULT = "fail";
	public static final String AK = "secret";
//	public static final String SECRET = "V6keXFqPW2DNz1GCkQ9zObrW";
	public static final String SECRET = "GT2n1LYw3GYtWqrTfXMTrrCShQIti2fR";
//	public static final String SECRET = "81Ahs1D8snCqI6stpgbp5XLCTk9tFGmL";
	public static final String DEFAULT_OUTPUT = "json"; 
	public static final String OUTPUT = "t_output";
	public static final String PAGE_SIZE = "pageSize";
	public static final String DEFAULT_PAGE_SIZE = "10";
	public static final String PAGE_NUM = "pageNum";
	
	public static String encode(String str,String charset){
		try {
			return URLEncoder.encode(str,charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
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
	
	public static String do_get(String url,Map<String,String> params){
		String result = DEFAULT_RESULT;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		Set<String> keys = params.keySet();
		NameValuePair[] nvps = new NameValuePair[keys.size()];
		int index = 0;
		for(String key : keys){
			try {
				 nvps[index++] = new NameValuePair(key,URLEncoder.encode(params.get(key).toString(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		//设置查询参数
		get.setQueryString(nvps);
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
			try {
				post.setParameter(key, URLEncoder.encode(params.get(key).toString(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
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
