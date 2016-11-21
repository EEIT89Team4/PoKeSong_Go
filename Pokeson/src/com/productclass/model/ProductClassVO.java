package com.productclass.model;

import java.util.HashSet;

import java.util.Set;

import com.productclassdetail.model.ProductClassdetailVO;

import product.ProductVO;


public class ProductClassVO implements java.io.Serializable {
	private Integer class_no;
	private String class_name;
	private byte[] class_pic;
	private Set<ProductVO> products = new HashSet<ProductVO>();
	private Set<ProductClassdetailVO> ProductClassdetails = new HashSet<ProductClassdetailVO>();
	
	public Integer getClass_no() {
		return class_no;
	}
	
	public void setClass_no(Integer class_no) {
		this.class_no = class_no;
	}
	
	public String getClass_name() {
		return class_name;
	}
	
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}

	public byte[] getClass_pic() {
		return class_pic;
	}

	public void setClass_pic(byte[] class_pic) {
		this.class_pic = class_pic;
	}

	public Set<ProductVO> getProducts() {
		return products;
	}

	public void setProducts(Set<ProductVO> products) {
		this.products = products;
	}

	public Set<ProductClassdetailVO> getProductClassdetails() {
		return ProductClassdetails;
	}

	public void setProductClassdetails(Set<ProductClassdetailVO> productClassdetails) {
		ProductClassdetails = productClassdetails;
	}
	
}
