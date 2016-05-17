package com.map;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zju.hpec.util.HttpUtil;

public class Map {
	
	private static final String URL = "http://api.map.baidu.com/place/v2/search?q=tag&region=t_place&output=t_output&ak=secret";
	private static final String SECRET = "V6keXFqPW2DNz1GCkQ9zObrW";
	
	public static void main(String[] args) throws HttpException, IOException {
		String tag = "加油站";
		String place = "杭州市";
		String re_url = URL.replace("tag", URLEncoder.encode(tag, "utf-8"))
						   .replace("t_place", URLEncoder.encode(place,"utf-8"))
						   .replace("t_output", "json")
						   .replace("secret", SECRET);
//		System.out.println(re_url);
		String result = HttpUtil.do_get(re_url);
//		System.out.println(result);
		//JSONObject jsons = JSONObject.fromObject(result);
//		System.out.println(jsons);
		//JSONArray results = jsons.getJSONArray("results");
		JSONObject json = JSON.parseObject(result);
		JSONArray results = json.getJSONArray("results");
		System.out.println(results);
	}
}
