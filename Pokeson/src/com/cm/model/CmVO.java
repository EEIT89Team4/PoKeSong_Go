package com.cm.model;

public class CmVO  implements java.io.Serializable{
	private Integer cm_no;
	private byte[] picture;
	
	public Integer getCm_no() {
		return cm_no;
	}
	public void setCm_no(Integer cm_no) {
		this.cm_no = cm_no;
	}
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

}
