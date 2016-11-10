package orderdetails;

import java.util.List;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.util.HibernateUtil;
import java.sql.*;
import order.OrderlistVO;
import orderdetails.*;
import product.ProductVO;
public class OrderDetailsService {
	private OrderDetailsDAO_interface dao;
	public OrderDetailsService(){
		dao = new OrderDetailsDAO();
	}
	public void addOrderDetails(Integer product_no,Integer order_no,Integer orderproduct_quantity) {
	 OrderDetailsVO orderdetailsVO = new OrderDetailsVO();
	 OrderlistVO orderlistVO=new OrderlistVO();
	 orderlistVO.setOrder_no(order_no);
	 ProductVO productVO = new ProductVO();
	 productVO.setProduct_no(product_no);
	         orderdetailsVO.setOrderlistVO(orderlistVO);
	         orderdetailsVO.setProductVO(productVO);
	         orderdetailsVO.setOrderproduct_quantity(orderproduct_quantity);
	 dao.insert(orderdetailsVO);
	
	}
	public void updateOrderDetails(Integer product_no,Integer order_no,Integer orderproduct_quantity) {
		OrderDetailsVO orderdetailsVO = new OrderDetailsVO();
		 OrderlistVO orderlistVO=new OrderlistVO();
		 orderlistVO.setOrder_no(order_no);
		 ProductVO productVO = new ProductVO();
		 productVO.setProduct_no(product_no);
		         orderdetailsVO.setOrderlistVO(orderlistVO);
		         orderdetailsVO.setProductVO(productVO);
		         orderdetailsVO.setOrderproduct_quantity(orderproduct_quantity);
         dao.update(orderdetailsVO);
	}

	public List<OrderDetailsVO> getOneOrderlist(Integer order_no){
		OrderDetailsVO orderdetailsVO = new OrderDetailsVO();
		 OrderlistVO orderlistVO=new OrderlistVO();
		 orderlistVO.setOrder_no(order_no);
		         orderdetailsVO.setOrderlistVO(orderlistVO);
		return dao.findByOrder_no(orderdetailsVO);
	}
	public void delete(Integer product_no,Integer order_no) {
		OrderDetailsVO orderdetailsVO = new OrderDetailsVO();
		 OrderlistVO orderlistVO=new OrderlistVO();
		 orderlistVO.setOrder_no(order_no);
		 ProductVO productVO = new ProductVO();
		 productVO.setProduct_no(product_no);
		         orderdetailsVO.setOrderlistVO(orderlistVO);
		         orderdetailsVO.setProductVO(productVO);
		 dao.delete(orderdetailsVO);
	}
	public OrderDetailsVO orderDetailsVO(Integer product_no,Integer order_no,Integer orderproduct_quantity){
		 OrderDetailsVO orderdetailsVO = new OrderDetailsVO();
		 OrderlistVO orderlistVO=new OrderlistVO();
		 orderlistVO.setOrder_no(order_no);
		 ProductVO productVO = new ProductVO();
		 productVO.setProduct_no(product_no);
		         orderdetailsVO.setOrderlistVO(orderlistVO);
		         orderdetailsVO.setProductVO(productVO);
		         orderdetailsVO.setOrderproduct_quantity(orderproduct_quantity);
		return orderdetailsVO;
	}
//	public List<OrderDetailsVO> getAll() {
//		OrderDetailsDAO ordertdetailsdao = new OrderDetailsDAO();
//		 return dao.getAll();
//	}
}
