package order;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;



import order.OrderlistVO;
import orderdetails.OrderDetailsVO;
import member.*;

import hibernate.util.HibernateUtil;
import order.OrderlistDAO;


public class OrderlistDAO implements OrderlistDAO_interface  {
private static final String GET_ALL_STMT = "from OrderlistVO order by order_no";
	@Override
	public void insert(OrderlistVO orderlistVO){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(orderlistVO);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}
		//----------------------------------jdbc-------------------------------------------------
//		String sqlOrder = "Insert Into orderlist "
//				+ " (member_no, order_name, order_phone, order_address, order_date, receipt_name,receipt_phone,receipt_address,subtotal,usebonus,total,freight,orderState) "
//				+ " values(?, ?, ?, ?, ?, ?, ? , ? , ? , ? , ? , ? , ? ) ";
//		Connection conn = null;
//		PreparedStatement pStmt = null;
//		ResultSet generatedKeys = null;
//		PreparedStatement pStmt2 = null;
//		try {
//			Context ctx = new InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB");
//			conn=ds.getConnection();
//			conn.setAutoCommit(false);  // 開啟JDBC Transaction
//			pStmt = conn.prepareStatement(sqlOrder,
//					Statement.RETURN_GENERATED_KEYS);
//			pStmt.setInt(1, orderlistVO.getMember_no());
//			pStmt.setString(2, orderlistVO.getOrder_name());
//			pStmt.setString(3, orderlistVO.getOrder_phone());
//			pStmt.setString(4, orderlistVO.getOrder_address());
////			pStmt.setDate(5, (Date) orderlistVO.getOrder_date());
//			pStmt.setString(6, orderlistVO.getReceipt_name());
//			pStmt.setString(7, orderlistVO.getReceipt_phone());
//			pStmt.setString(8, orderlistVO.getReceipt_address());
//			pStmt.setInt(9, orderlistVO.getSubtotal());
//			pStmt.setInt(10, orderlistVO.getUsebonus());
//			pStmt.setInt(11, orderlistVO.getTotal());
//			pStmt.setInt(12, orderlistVO.getFreight());
//			pStmt.setString(13, orderlistVO.getOrderState());
//			Timestamp ts = new Timestamp(orderlistVO.getOrder_date().getTime());
//			pStmt.setTimestamp(5, ts);
//			pStmt.executeUpdate();
//			int id = 0;
//			generatedKeys = pStmt.getGeneratedKeys();
//			if (generatedKeys.next()) {
//				id = generatedKeys.getInt(1);
//			} else {
//				throw new SQLException(
//						"Creating user failed, no generated key obtained.");
//			}
//			String sqlItem = "Insert Into orderdetails (product_no, order_no, orderproduct_quantity) "
//					+ " values(?, ?, ?) ";
//			Set<OrderDetailsVO> items = orderlistVO.getOrderdetails1();
//			pStmt2 = conn.prepareStatement(sqlItem);
//			int n=0;
//			for (OrderDetailsVO oib : items) {
////              下列四個敘述為交易測試而編寫	
////				n++;
////				if (n > 2) {
////					System.out.println("發生例外 n>2");
////					throw new SQLException("JDBC交易測試用");
////				}
//				pStmt2.setInt(1, oib.getProductVO().getProduct_no());
//				pStmt2.setInt(2, id);
//				pStmt2.setInt(3, items.size());
//				int count = pStmt2.executeUpdate();
//				pStmt2.clearParameters();
//			}
//			conn.commit();  
//		} catch(SQLException ex){
//			System.out.println("資料還原");
//			if(conn!=null) conn.rollback();
//		} finally {
//			
//			if (pStmt != null) {
//				pStmt.close();
//			}
//			if (pStmt2 != null) {
//				pStmt2.close();
//			}
//		
//			if (conn != null) {
//				conn.setAutoCommit(true);
//				conn.close();
//			
//		}
//	}
	}
		
	

	@Override
	public void update(OrderlistVO orderlistVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(orderlistVO);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}

	}

	@Override
	public void delete(Integer order_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		OrderlistVO orderlistVO = null;
		try {
			tx = session.beginTransaction();
			orderlistVO = (OrderlistVO) session.get(OrderlistVO.class, order_no);
//			orderlistVO=new OrderlistVO();
//			orderlistVO.setOrder_no(order_no);
			session.delete(orderlistVO);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		}
	
	}
		
	

	@Override
	public OrderlistVO findByPrimaryKey(Integer order_no) {
		OrderlistVO orderlistVO = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			orderlistVO = (OrderlistVO) session.get(OrderlistVO.class, order_no);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return orderlistVO;
	}
	@Override
	public List<OrderlistVO> findByMember_no(Integer member_no) {
		OrderlistVO orderlistVO = null;
		Transaction tx = null;
		List<OrderlistVO> list=null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from OrderlistVO where member_no=?");
			query.setParameter(0, member_no);
			 list=query.list();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return list;
	}

	public List<OrderlistVO> getAllOrderlist() {
		List<OrderlistVO> TotalOrderlist = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from OrderlistVO order by order_no");
			TotalOrderlist= query.list();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return TotalOrderlist;

	}
	
	@Override
	public Integer findInsertOrder_no() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		Integer order_no=null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("select Max(order_no) from OrderlistVO");
			order_no=(Integer)(query.list().get(0));
//			System.out.println(order_no);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}
		return order_no;
	}



	public static void main(String[] args) {
		//UPDATE
//		OrderlistService service = new OrderlistService();
//		OrderlistVO orderlistVO = service.UpdateOrderlist(125,16100105, "小黑", "0928123456", "taipei", java.sql.Date.valueOf("2005-01-01"), "aaaa", "00000", "11111", 50000, 50000, 50000, 50000, "50000");
//		service.update(orderlistVO);
		//DELETE
//		OrderlistService service = new OrderlistService();
//		service.deleteOrderlist(16100106);
		
		//INSERT----------------------------------------------------------
//		OrderlistService service = new OrderlistService();
//		OrderlistVO orderlistVO = service.addOrderlist(125,"小黑", "0928123456", "taipei", java.sql.Date.valueOf("2005-01-01"), "aaaa", "00000", "11111", 50000, 50000, 50000, 50000, "50000");
//		service.insertOrderlist(orderlistVO);
		//GET_ONE by order_no--------------------------------------------
//		OrderlistService service = new OrderlistService();
//		OrderlistVO orderlist1 = service.getOneOrderlist(16100101);
//		System.out.println(orderlist1.getMember_no());
//		 System.out.println(orderlist1.getOrder_name());
//		 System.out.println(orderlist1.getOrder_phone());
//		 System.out.println(orderlist1.getOrder_address());
//		 System.out.println(orderlist1.getOrder_date());
//		 System.out.println(orderlist1.getReceipt_name());
//		 System.out.println(orderlist1.getReceipt_phone());
//		 System.out.println(orderlist1.getReceipt_address());
//		 System.out.println(orderlist1.getSubtotal());
//		 System.out.println(orderlist1.getUsebonus());
//		 System.out.println(orderlist1.getTotal());
//		 System.out.println(orderlist1.getFreight());
//		 System.out.println(orderlist1.getOrderState());
		//GET_ONE by member_no---------------------------------------------------
//		OrderlistService service = new OrderlistService();
//		List<OrderlistVO>  list = service.getOneOrderlistbyMember_no(121);
//		for(OrderlistVO orderlist1:list){
//		System.out.println(orderlist1.getMember_no());
//		 System.out.println(orderlist1.getOrder_name());
//		 System.out.println(orderlist1.getOrder_phone());
//		 System.out.println(orderlist1.getOrder_address());
//		 System.out.println(orderlist1.getOrder_date());
//		 System.out.println(orderlist1.getReceipt_name());
//		 System.out.println(orderlist1.getReceipt_phone());
//		 System.out.println(orderlist1.getReceipt_address());
//		 System.out.println(orderlist1.getSubtotal());
//		 System.out.println(orderlist1.getUsebonus());
//		 System.out.println(orderlist1.getTotal());
//		 System.out.println(orderlist1.getFreight());
//		 System.out.println(orderlist1.getOrderState());
//		 System.out.println(orderlist1.getOrderdetails1().size());
//		}
	//GET_ALL
//		 OrderlistDAO orderlistdao = new OrderlistDAO();
//		 List<OrderlistVO> orderlists=orderlistdao.getAllOrderlist();
//		 for (OrderlistVO orderlist1 :orderlists){
//		 System.out.println(orderlist1.getMember_no());
//		 System.out.println(orderlist1.getOrder_name());
//		 System.out.println(orderlist1.getOrder_phone());
//		 System.out.println(orderlist1.getOrder_address());
//		 System.out.println(orderlist1.getOrder_date());
//		 System.out.println(orderlist1.getReceipt_name());
//		 System.out.println(orderlist1.getReceipt_phone());
//		 System.out.println(orderlist1.getReceipt_address());
//		 System.out.println(orderlist1.getSubtotal());
//		 System.out.println(orderlist1.getUsebonus());
//		 System.out.println(orderlist1.getTotal());
//		 System.out.println(orderlist1.getFreight());
//		 System.out.println(orderlist1.getOrderState());
//		 }
		
		 
		 
	}

	



}
