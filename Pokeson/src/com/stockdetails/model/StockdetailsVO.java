package com.stockdetails.model;

import java.io.Serializable;


import com.stock.model.StockVO;

import product.ProductVO;

public class StockdetailsVO implements Serializable{
	private StockVO stockVO;
	private ProductVO productVO;
	private Integer product_newquantity;
	
	public StockVO getStockVO() {
		return stockVO;
	}
	public void setStockVO(StockVO stockVO) {
		this.stockVO = stockVO;
	}
	public ProductVO getProductVO() {
		return productVO;
	}
	public void setProductVO(ProductVO productVO) {
		this.productVO = productVO;
	}
	public Integer getProduct_newquantity() {
		return product_newquantity;
	}
	public void setProduct_newquantity(Integer product_newquantity) {
		this.product_newquantity = product_newquantity;
	}

}
