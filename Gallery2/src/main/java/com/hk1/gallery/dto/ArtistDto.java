package com.hk1.gallery.dto;

public class ArtistDto {
	private int a_no;
	private String a_name;
	private String a_solo;
	private String squard;
	private String a_career;
	private int a_state;
	
	
	public ArtistDto() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getA_no() {
		return a_no;
	}


	public ArtistDto setA_no(int a_no) {
		this.a_no = a_no;
		return this;
	}


	public String getA_name() {
		return a_name;
	}


	public ArtistDto setA_name(String a_name) {
		this.a_name = a_name;
		return this;
	}


	public String getA_solo() {
		return a_solo;
	}


	public ArtistDto setA_solo(String a_solo) {
		this.a_solo = a_solo;
		return this;
	}


	public String getSquard() {
		return squard;
	}


	public ArtistDto setSquard(String squard) {
		this.squard = squard;
		return this;
	}


	public String getA_career() {
		return a_career;
	}


	public ArtistDto setA_career(String a_career) {
		this.a_career = a_career;
		return this;
	}


	public int getA_state() {
		return a_state;
	}


	public ArtistDto setA_state(int a_state) {
		this.a_state = a_state;
		return this;
	}


	@Override
	public String toString() {
		return "ArtistDto [a_no=" + a_no + ", a_name=" + a_name + ", a_solo=" + a_solo + ", squard=" + squard
				+ ", a_career=" + a_career + ", a_state=" + a_state + "]";
	}
	
}
