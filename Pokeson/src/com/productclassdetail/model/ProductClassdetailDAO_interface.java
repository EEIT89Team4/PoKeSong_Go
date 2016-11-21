package com.productclassdetail.model;

import java.util.List;

public interface ProductClassdetailDAO_interface {
	public void insert(ProductClassdetailVO productClassdetailsVO);
	public void update(ProductClassdetailVO productClassdetailsVO);
	public void delete(Integer classdetail_no);
	public ProductClassdetailVO findByPrimaryKey(Integer classdetail_no);
	public List<ProductClassdetailVO> getAll();
	
}
