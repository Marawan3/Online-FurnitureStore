package com.westbarn.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class SubCategory implements Serializable{


	private static final long serialVersionUID = -6057997484988703410L;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int subcat_id;
	@Column
	private String subcatname;
	@Column
	private String subcatimage;
	@Column
	private String catname;
	
	public SubCategory() {
		super();
	}

	public SubCategory(String subcatname, String subcatimage, String catname) {
		super();
		this.subcatname = subcatname;
		this.subcatimage = subcatimage;
		this.catname = catname;
	}
	
	public int getSubcat_id() {
		return subcat_id;
	}
	public void setSubcat_id(int subcat_id) {
		this.subcat_id = subcat_id;
	}
	public String getSubcatname() {
		return subcatname;
	}
	public void setSubcatname(String subcatname) {
		this.subcatname = subcatname;
	}
	public String getSubcatimage() {
		return subcatimage;
	}
	public void setSubcatimage(String subcatimage) {
		this.subcatimage = subcatimage;
	}
	public String getCatname() {
		return catname;
	}
	public void setCatname(String catname) {
		this.catname = catname;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
