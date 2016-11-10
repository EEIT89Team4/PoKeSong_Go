package orderdetails;
import java.io.Serializable;
import java.sql.Date;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import order.*;
import product.*;


public class OrderDetailsVO implements Serializable {
	
private OrderlistVO orderlistVO;
private ProductVO productVO;
private Integer orderproduct_quantity;


public Integer getOrderproduct_quantity() {
	return orderproduct_quantity;
}
public void setOrderproduct_quantity(Integer orderproduct_quantity) {
	this.orderproduct_quantity = orderproduct_quantity;
}
public ProductVO getProductVO() {
	return productVO;
}
public void setProductVO(ProductVO productVO) {
	this.productVO = productVO;
}
public OrderlistVO getOrderlistVO() {
	return orderlistVO;
}
public void setOrderlistVO(OrderlistVO orderlistVO) {
	this.orderlistVO = orderlistVO;
}







}

