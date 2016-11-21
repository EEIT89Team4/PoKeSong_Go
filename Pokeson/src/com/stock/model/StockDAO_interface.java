package com.stock.model;

import java.util.List;

public interface StockDAO_interface {
	public void insert(StockVO stockVO);
	public void update(StockVO stockVO);
	public void delete(Integer stock_id);
	public StockVO findByPrimaryKey(Integer stock_id);
	public List<StockVO> getAll();
	
}
