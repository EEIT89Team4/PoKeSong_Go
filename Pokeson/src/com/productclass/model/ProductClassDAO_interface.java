package com.productclass.model;

import java.util.List;

import java.util.Set;

import com.stock.model.StockVO;

import product.ProductVO;

public interface ProductClassDAO_interface {
	public void insert(ProductClassVO productClassVO);
	public void update(ProductClassVO productClassVO);
	public void delete(Integer class_no);
	public ProductClassVO findByPrimaryKey(Integer class_no);
	public List<ProductClassVO> getAll();
	//查詢某分類的產品(一對多)(回傳 Set)
	public Set<ProductVO> getProductsByClass_no(Integer class_no);
}
