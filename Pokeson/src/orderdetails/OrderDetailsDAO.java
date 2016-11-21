package orderdetails;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import product.*;
import order.*;
import orderdetails.*;
import hibernate.util.HibernateUtil;

public class OrderDetailsDAO implements OrderDetailsDAO_interface{
	private static final String GET_ALL_STMT = "from OrderDetailsVO order by orderproduct_quantity";
	@Override
	public void insert(OrderDetailsVO orderdetailsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(orderdetailsVO);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}	
	
		
	}

	@Override
	public void update(OrderDetailsVO orderdetailsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(orderdetailsVO);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}	
		
	}

	@Override
	public void delete(OrderDetailsVO orderdetailsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		OrderDetailsVO orderdetails=null;
		try {
			tx = session.beginTransaction();
			orderdetails = (OrderDetailsVO) session.get(OrderDetailsVO.class,orderdetailsVO );
			session.delete(orderdetails);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			;
		}
	}

	@Override
	public List<OrderDetailsVO> findByOrder_no(OrderDetailsVO orderdetailsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<OrderDetailsVO> list=null;
		try {
			tx = session.beginTransaction();
			Query query=session.createQuery("from OrderDetailsVO where order_no=?");
			query.setParameter(0,orderdetailsVO.getOrderlistVO().getOrder_no());
			list = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			;
		}
		return list;
	}
	

//    @Override
//	public List<OrderDetailsVO> findByPrimaryKey(OrderDetailsVO orderdetailsVO) {
//    	OrderDetailsVO orderdetails = null;
//		
//		 List<OrderDetailsVO> list;
//		 
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			orderdetails=(OrderDetailsVO)session.get(OrderDetailsVO.class, orderdetailsVO);
//			 session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return orderdetails;
//	}

	@Override
	public List<OrderDetailsVO> getAll() {
		List<OrderDetailsVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
//			Query query = session.createQuery("select orderproduct_quantity from OrderDetailsVO");
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	


	public static void main(String args[]) {
		OrderDetailsService service = new OrderDetailsService();
		//INSERT
//		service.addOrderDetails(3001,16100105,8);
		//UPDATE
//		service.updateOrderDetails(3040,16100105,6);
//		OrderDetailsDAO orderDetailsDAO = new OrderDetailsDAO();
		//DELETE
//		service.delete(3040, 16100105);
		//FindOrder_no
//		List<OrderDetailsVO> orderDetailsVO=service.getOneOrderlist(16100105);
//		for(OrderDetailsVO orderDetails:orderDetailsVO ){
//			System.out.println(orderDetails.getOrderlistVO().getOrder_no());
//			System.out.println(orderDetails.getProductVO().getProduct_no());
//			System.out.println(orderDetails.getOrderproduct_quantity());
//			
//		}
		//FindOrder_no
//		List<OrderDetailsVO> orderDetailsVO=service.getAll();
//		for(OrderDetailsVO orderDetails:orderDetailsVO ){
//			System.out.println(orderDetails.getOrderlistVO().getOrder_no());
//			System.out.println(orderDetails.getProductVO().getProduct_no());
//			System.out.println(orderDetails.getOrderproduct_quantity());
//			
//		}
	}

}
