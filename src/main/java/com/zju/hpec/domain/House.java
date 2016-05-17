package com.zju.hpec.domain;

public class House {

	private String name;
	private Position position;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	@Override
	public String toString() {
		return "House [name=" + name + ", position=" + position + "]";
	}
}
