package com.zju.hpec.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zju.hpec.domain.GasStation;
import com.zju.hpec.domain.Position;

public class GasStationResolver {

	private final static String RESULT_KEY = "results";
	private final static String LOCATION = "location"; 
	private final static String NAME = "name";
	private final static String ADDRESS = "address";
	private final static String X  = "lng";
	private final static String Y = "lat";
	private final static String TAG = "tag";
	private final static String CITY = "city";
	private static final String URL = "http://api.map.baidu.com/place/v2/search?q=tag&region=city&output=t_output&ak=secret&page_size=pageSize&page_num=pageNum";
	private static final int DEFAULT_PAGE_NUM = 0;
	
	private static String getRequestURL(String city,String tag){
		return URL.replace(CITY, HttpUtil.encode(city,"utf-8"))
				  .replace(TAG, HttpUtil.encode(tag, "utf-8"))
				  .replace(HttpUtil.OUTPUT, HttpUtil.DEFAULT_OUTPUT)
				  .replace(HttpUtil.AK, HttpUtil.SECRET)
				  .replace(HttpUtil.PAGE_SIZE, HttpUtil.DEFAULT_PAGE_SIZE);
	}
	
	/**
	 * 解析加油站
	 * @param info
	 * @return
	 */
	public static List<GasStation> resolveGasStation(String city,String tag){
		List<GasStation> gss = new ArrayList<GasStation>();
		if(null == city || "".equals(city) || null == tag || "".equals(tag))
			return gss;
//		String req_url = getRequestURL(city,tag);
//		System.out.println(req_url);
		int num = DEFAULT_PAGE_NUM;
		String req_url = getRequestURL(city,tag);
		String tmp_url = req_url.replace(HttpUtil.PAGE_NUM, Integer.toString(num));
//		System.out.println(tmp_url);
		String info = HttpUtil.do_get(tmp_url);
//		System.out.println(info);
		JSONObject j = JSON.parseObject(info);
		int total = j.getIntValue("total");
//		System.out.println("total:"+total);
		int page_size = total / Integer.parseInt(HttpUtil.DEFAULT_PAGE_SIZE) + 1;
		while(num < page_size){
			try{
//				String tmp_url = req_url.replace(HttpUtil.PAGE_NUM, Integer.toString(num));
				tmp_url = req_url.replace(HttpUtil.PAGE_NUM, Integer.toString(num));
//				System.out.println(tmp_url);
//				String info = HttpUtil.do_get(tmp_url);
				info = HttpUtil.do_get(tmp_url);
//				System.out.println(info);
				JSONObject json = JSON.parseObject(info);
				JSONArray results = json.getJSONArray(RESULT_KEY);
				for(int i = 0; i < results.size(); ++i){
					JSONObject jb = results.getJSONObject(i);
					GasStation gs = new GasStation(jb.getString(NAME),jb.getString(ADDRESS));
					JSONObject pos =jb.getJSONObject(LOCATION);
					gs.setPosition(new Position(pos.getDoubleValue(X),pos.getDoubleValue(Y)));
//					System.out.println(gs);
					gss.add(gs);
				}
//				System.out.println("num:"+num);
				++num;
			}catch(Exception e){
				return gss;
			}
		}
		return gss;
	}
}
