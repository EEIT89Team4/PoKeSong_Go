package order;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import orderdetails.OrderDetailsVO;

public class OrderlistVO implements Serializable {
private Integer member_no;
private Integer order_no;
private String order_name;
private String order_phone;
private String order_address;
private Date order_date;
private String receipt_name;
private String receipt_phone;
private String receipt_address;
private int subtotal;
private int usebonus;
private int total;
private int freight;
private String orderState;
private Set<OrderDetailsVO> orderdetails1= new HashSet<OrderDetailsVO>();



public Set<OrderDetailsVO> getOrderdetails1() {
	return orderdetails1;
}
public void setOrderdetails1(Set<OrderDetailsVO> orderdetails1) {
	this.orderdetails1 = orderdetails1;
}
public Integer getMember_no() {
	return member_no;
}
public void setMember_no(Integer member_no) {
	this.member_no = member_no;
}
public Integer getOrder_no() {
	return order_no;
}
public void setOrder_no(Integer order_no) {
	this.order_no = order_no;
}
public String getOrder_name() {
	return order_name;
}
public void setOrder_name(String order_name) {
	this.order_name = order_name;
}
public String getOrder_phone() {
	return order_phone;
}
public void setOrder_phone(String order_phone) {
	this.order_phone = order_phone;
}
public String getOrder_address() {
	return order_address;
}
public void setOrder_address(String order_address) {
	this.order_address = order_address;
}
public Date getOrder_date() {
	return order_date;
}
public void setOrder_date(Date order_date) {
	this.order_date = order_date;
}
public String getReceipt_name() {
	return receipt_name;
}
public void setReceipt_name(String receipt_name) {
	this.receipt_name = receipt_name;
}
public String getReceipt_phone() {
	return receipt_phone;
}
public void setReceipt_phone(String receipt_phone) {
	this.receipt_phone = receipt_phone;
}
public String getReceipt_address() {
	return receipt_address;
}
public void setReceipt_address(String receipt_address) {
	this.receipt_address = receipt_address;
}
public int getSubtotal() {
	return subtotal;
}
public void setSubtotal(int subtotal) {
	this.subtotal = subtotal;
}
public int getUsebonus() {
	return usebonus;
}
public void setUsebonus(int usebonus) {
	this.usebonus = usebonus;
}
public int getTotal() {
	return total;
}
public void setTotal(int total) {
	this.total = total;
}
public int getFreight() {
	return freight;
}
public void setFreight(int freight) {
	this.freight = freight;
}
public String getOrderState() {
	return orderState;
}
public void setOrderState(String orderState) {
	this.orderState = orderState;
}

}

	
