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
	
	public GasStation(String name,String address){
		this.name = name;
		this.address = address;
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

	@Override
	public String toString() {
		return "GasStation [name=" + name + ", address=" + address + ", position=" + position + "]\n";
	}
	
}
