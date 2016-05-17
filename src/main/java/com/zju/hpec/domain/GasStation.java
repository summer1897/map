package com.zju.hpec.domain;

/**
 * 加油站
 * @author hzduhao
 *
 */
public class GasStation {

	private String name;
	private String address;
	private Position position;
	
	public GasStation(String name,String address,Position position){
		this.name = name;
		this.address = address;
		this.position = position;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
}
