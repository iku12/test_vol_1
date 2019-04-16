package com.hk1.gallery.dto;

public class ExhibitionDto {

	private int e_no;
	private String e_title;
	private String e_explain;
	private int a_no;
	private String a_name;
	private int e_click;
	
	public ExhibitionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getE_no() {
		return e_no;
	}

	public ExhibitionDto setE_no(int e_no) {
		this.e_no = e_no;
		return this;
	}

	public String getE_title() {
		return e_title;
	}

	public ExhibitionDto setE_title(String e_title) {
		this.e_title = e_title;
		return this;
	}

	public String getE_explain() {
		return e_explain;
	}

	public ExhibitionDto setE_explain(String e_explain) {
		this.e_explain = e_explain;
		return this;
	}

	public int getA_no() {
		return a_no;
	}

	public ExhibitionDto setA_no(int a_no) {
		this.a_no = a_no;
		return this;
	}

	public String getA_name() {
		return a_name;
	}

	public ExhibitionDto setA_name(String a_name) {
		this.a_name = a_name;
		return this;
	}

	public int getE_click() {
		return e_click;
	}

	public ExhibitionDto setE_click(int e_click) {
		this.e_click = e_click;
		return this;
	}

	@Override
	public String toString() {
		return "ExhibitionDto [e_no=" + e_no + ", e_title=" + e_title + ", e_explain=" + e_explain + ", a_no=" + a_no
				+ ", a_name=" + a_name + ", e_click=" + e_click + "]";
	}
	
}
