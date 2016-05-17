package com.map;

import java.io.IOException;
import java.util.List;

import org.apache.commons.httpclient.HttpException;

import com.zju.hpec.domain.GasStation;
import com.zju.hpec.domain.House;
import com.zju.hpec.util.GasStationResolver;
import com.zju.hpec.util.HouseAreaResolver;

public class BaiduMap {
	
	
	public static void main(String[] args) throws HttpException, IOException {
		String tag = "加油站";
		String city = "杭州市";
		
		List<GasStation> gass = GasStationResolver.resolveGasStation(city,tag);
		List<House> housese = HouseAreaResolver.resolver(gass);
		for(int i = 0; i < housese.size(); ++i){
			System.out.println((i+1)+" "+housese.get(i));
		}
	}
}
