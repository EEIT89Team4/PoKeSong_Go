package com.supplier.model;

import java.util.List;
import java.util.Set;

import com.stock.model.StockVO;

public interface SupplierDAO_interface {
	public void insert(SupplierVO supplierVO);
	public void update(SupplierVO supplierVO);
	public void delete(Integer supplier_id);
	public SupplierVO findByPrimaryKey(Integer supplier_id);
	public List<SupplierVO> getAll();
	//�d�߬Y�����Ӫ��i�f��(�@��h)(�^�� Set)
	public Set<StockVO> getStocksBySupplier_id(Integer Supplier_id);
}
