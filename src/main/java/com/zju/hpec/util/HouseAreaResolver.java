package com.zju.hpec.util;

import java.util.ArrayList;
import java.util.List;

import com.zju.hpec.domain.GasStation;
import com.zju.hpec.domain.House;

public class HouseAreaResolver {

	private final static String RADIUS = "1000";
	private final static String T_RADIUS = "t_radius";
	private final static String T_QUERY = "t_query";
	private final static String T_LOCATION = "t_location";
	private final static String HOUSE = "小区";//&page_size=pageSize&page_num=pageNum
	private final static String URL = "http://api.map.baidu.com/place/v2/search?query=t_query&output=t_output&ak=secret"
									  + "&location=t_location&radius=t_radius";
	
	private static String getURL(){
		return URL.replace(T_QUERY, HttpUtil.encode(HOUSE,"utf-8"))
				  .replace(T_RADIUS, RADIUS)
				  .replace(HttpUtil.OUTPUT, HttpUtil.DEFAULT_OUTPUT)
				  .replace(HttpUtil.AK, HttpUtil.SECRET)
				  .replace(HttpUtil.PAGE_SIZE, HttpUtil.DEFAULT_PAGE_SIZE)
				  .replace("pageNum", "0");
	}
	
	public static List<House> resolver(List<GasStation> gass){
		List<House> house = new ArrayList<House>();
		String re_url = getURL();
		String tmp_url = "";
		tmp_url = re_url.replace(T_LOCATION, gass.get(0).getPosition().toString());
		System.out.println(tmp_url);
		String result = HttpUtil.do_post(tmp_url);
		System.out.println(result);
//		for(GasStation gas : gass){
//			tmp_url = re_url.replace(T_LOCATION, gas.getPosition().toString());
//			String result = HttpUtil.do_get(tmp_url);
//			System.out.println(result);
//		}
		return house;
	}
}
