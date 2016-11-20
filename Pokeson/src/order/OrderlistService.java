package order;

import java.util.List;


import java.util.Set;

import javax.naming.NamingException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.Date;


import hibernate.util.HibernateUtil;

import order.*;
import orderdetails.OrderDetailsVO;
public class OrderlistService {
	private OrderlistDAO_interface dao;
	
	public OrderlistService(){
		dao = new OrderlistDAO();
	}

	
	public OrderlistVO addOrderlist(Integer member_no,String order_name,String order_phone,String order_address,Date today,String receipt_name,String receipt_phone,String receipt_address,
Integer subtotal,Integer usebonus,Integer total,Integer freight,String orderState) {
	 OrderlistVO orderlistVO = new OrderlistVO();
	 orderlistVO.setMember_no(member_no);
	    orderlistVO.setOrder_name(order_name);
		orderlistVO.setOrder_phone(order_phone);
		orderlistVO.setOrder_address(order_address);
		orderlistVO.setOrder_date(today);
		orderlistVO.setReceipt_name(receipt_name);
		orderlistVO.setReceipt_phone(receipt_phone);
		orderlistVO.setReceipt_address(receipt_address);
		orderlistVO.setSubtotal(subtotal);
		orderlistVO.setUsebonus(usebonus);
		orderlistVO.setTotal(total);
		orderlistVO.setFreight(freight);
		orderlistVO.setOrderState(orderState);
//		orderlistVO.setOrderdetails1(orderdetails1);
	return orderlistVO;
	}
	public OrderlistVO UpdateOrderlist(Integer member_no,Integer order_no,String order_name,String order_phone,String order_address,Date order_date,String receipt_name,String receipt_phone,String receipt_address,
			Integer subtotal,Integer usebonus,Integer total,Integer freight,String orderState) {
				 OrderlistVO orderlistVO = new OrderlistVO();
				 orderlistVO.setMember_no(member_no);
				 orderlistVO.setOrder_no(order_no);
				    orderlistVO.setOrder_name(order_name);
					orderlistVO.setOrder_phone(order_phone);
					orderlistVO.setOrder_address(order_address);
					orderlistVO.setOrder_date(order_date);
					orderlistVO.setReceipt_name(receipt_name);
					orderlistVO.setReceipt_phone(receipt_phone);
					orderlistVO.setReceipt_address(receipt_address);
					orderlistVO.setSubtotal(subtotal);
					orderlistVO.setUsebonus(usebonus);
					orderlistVO.setTotal(total);
					orderlistVO.setFreight(freight);
					orderlistVO.setOrderState(orderState);
					
				return orderlistVO;
				}
	public void insertOrderlist(OrderlistVO orderlistVO){
		dao.insert(orderlistVO);		
	}
	public void update(OrderlistVO orderlistVO){
		 dao.update(orderlistVO);
	}

	public OrderlistVO getOneOrderlist(Integer order_no){
		return dao.findByPrimaryKey(order_no);
	}
	public List<OrderlistVO> getOneOrderlistbyMember_no(Integer member_no){
		return dao.findByMember_no(member_no);
	}
	public void deleteOrderlist(Integer order_no) {
	dao.delete(order_no);
	}
	public List<OrderlistVO> getAllOrderlist() {
		OrderlistDAO ordertlistdao = new OrderlistDAO();
		 return ordertlistdao.getAllOrderlist();
	}
	public Integer findInsertOrder_no(){
		
		return dao.findInsertOrder_no();
	}
}
