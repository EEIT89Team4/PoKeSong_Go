package com.stock.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.stockdetails.model.StockdetailsVO;
import com.supplier.model.SupplierVO;

public class StockVO implements java.io.Serializable {
	private Integer stock_id;
	private Date stock_date;
	private SupplierVO supplierVO;
	private String adminster_name;
	private Set<StockdetailsVO> stockdetails = new HashSet<StockdetailsVO>();
	
	public Integer getStock_id() {
		return stock_id;
	}
	
	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}
	
	public Date getStock_date() {
		return stock_date;
	}
	
	public void setStock_date(Date stock_date) {
		this.stock_date = stock_date;
	}
	
	public SupplierVO getSupplierVO() {
		return supplierVO;
	}
	
	public void setSupplierVO(SupplierVO supplierVO) {
		this.supplierVO = supplierVO;
	}
	
	public String getAdminster_name() {
		return adminster_name;
	}
	
	public void setAdminster_name(String adminster_name) {
		this.adminster_name = adminster_name;
	}

	public Set<StockdetailsVO> getStockdetails() {
		return stockdetails;
	}

	public void setStockdetails(Set<StockdetailsVO> stockdetails) {
		this.stockdetails = stockdetails;
	}
	
	
}
