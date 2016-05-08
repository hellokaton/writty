package com.writty.model;

import java.io.Serializable;

import com.blade.jdbc.annotation.Table;

/**
 * Options对象
 */
@Table(value = "t_options", PK = "okey")
public class Options implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String okey;
	
	private String ovalue;
	
	public Options(){}
	
	public String getOkey() {
		return okey;
	}

	public void setOkey(String okey) {
		this.okey = okey;
	}
	
	public String getOvalue() {
		return ovalue;
	}

	public void setOvalue(String ovalue) {
		this.ovalue = ovalue;
	}
	
}