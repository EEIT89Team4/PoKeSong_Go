package com.stockdetails.model;

import java.util.List;

public interface StockdetailsDAO_interface  {
	public void insert(StockdetailsVO stockdetailsVO);
	public void update(StockdetailsVO stockdetailsVO);
	public void delete(Integer stock_id,int product_no);
	public List<StockdetailsVO> findByPrimaryKey(Integer stock_id,int product_no);
	public List<StockdetailsVO> getAll();
	
}
