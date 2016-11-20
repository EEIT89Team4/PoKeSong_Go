package com.productclassdetail.model;

import com.productclass.model.ProductClassVO;

public class ProductClassdetailVO {
	private Integer classdetail_no;
	private ProductClassVO productClassVO;
	private String classdetail_name;
	
	public Integer getClassdetail_no() {
		return classdetail_no;
	}
	
	public void setClassdetail_no(Integer classdetail_no) {
		this.classdetail_no = classdetail_no;
	}
	
	public ProductClassVO getProductClassVO() {
		return productClassVO;
	}
	
	public void setProductClassVO(ProductClassVO productClassVO) {
		this.productClassVO = productClassVO;
	}
	
	public String getClassdetail_name() {
		return classdetail_name;
	}
	
	public void setClassdetail_name(String classdetail_name) {
		this.classdetail_name = classdetail_name;
	}

}
