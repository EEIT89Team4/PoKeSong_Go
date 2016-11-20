package com.notifymember.model;

import java.util.ArrayList
;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import hibernate.util.HibernateUtil;
import member.MemberService;
import member.MemberVO;
import product.ProductService;
import product.ProductVO;


public class NotifyMemberDAO implements INotifyMemberDAO {

	// public void delete(NotifyMemberVO notifymemberVO) {
	// Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	// Transaction tx = null;
	// try {
	// tx = session.beginTransaction();
	// // NotifyMemberVO notifymember1 =
	// // (NotifyMemberVO)session.get(NotifyMemberVO.class,notifymemberVO);
	// session.delete(notifymemberVO);
	// tx.commit();
	// } catch (RuntimeException ex) {
	// tx.rollback();
	// }
	// }

	/* (non-Javadoc)
	 * @see notifymember.INotifyMemberDAO#insert(notifymember.NotifyMemberVO)
	 */
	@Override
	public void insert(NotifyMemberVO notifymembervo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(notifymembervo);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}
	}

	/* (non-Javadoc)
	 * @see notifymember.INotifyMemberDAO#deteteByProductVO(product.ProductVO)
	 */
	@Override
	public void deteteByProductVO(ProductVO productvo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		NotifyMemberVO nm = null;
		List<NotifyMemberVO> listmembers = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("from NotifyMemberVO where product_no=?");
			query.setParameter(0, productvo);
			listmembers=query.list();
			for(NotifyMemberVO notifymembervo :listmembers ){
				session.delete(notifymembervo);
			}
//			session.delete(productvo);
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}

	}


	@Override
	public List<NotifyMemberVO> getListMemberByProduct(ProductVO productvo) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		List<NotifyMemberVO> listmembers = null;
		try {
			tx = session.beginTransaction();
			listmembers = new ArrayList<NotifyMemberVO>();
			Query query = session.createQuery("from NotifyMemberVO where product_no=?");//where後面接dbtablecolumn,from接VOName
			query.setParameter(0, productvo);
			listmembers = query.list();
			tx.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			tx.rollback();
		}
		return listmembers;
	}

	/* (non-Javadoc)
	 * @see notifymember.INotifyMemberDAO#getListMemberEmail(product.ProductVO)
	 */
	@Override
	public Object[] getListMemberEmail(ProductVO productvo) {
		List<String> emaillist = new ArrayList<String>();

		List<NotifyMemberVO> notifylist = getListMemberByProduct(productvo);
		for (NotifyMemberVO notifyvo : notifylist) {
			emaillist.add(notifyvo.getMemberVO().getMember_Email());
		}

		return  emaillist.toArray();

	}
//
//	public void deteteByProductVO(Integer integer) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		Transaction tx = null;
//		NotifyMemberVO nm = null;
//		List<NotifyMemberVO> listmembers = null;
//		// Serializable id = new Long(17);
//		session.beginTransaction();
//		try {
//			Object persistentInstance = session.get(NotifyMemberVO.class, integer);
//			if (persistentInstance != null) {
//				session.delete(persistentInstance);
//			}
//			tx.commit();
//		} catch (HibernateException e) {
//			e.printStackTrace();
//			tx.rollback();
//		}
//
//	}
	public static void main(String args[]) {
		// test InsertMethod ok
		 ProductService ps = new ProductService();
		 ProductVO productvo = ps.getProductById(3004);
		 MemberService ms = new MemberService();
		 MemberVO membervo = ms.getOneById("anna");
		 NotifyMemberVO notifymember = new NotifyMemberVO();
		 notifymember.setProductVO(productvo);
		 notifymember.setMemberVO(membervo);
		 NotifyMemberDAO dao = new NotifyMemberDAO();
		 dao.insert(notifymember);
		

		// Test getListMemberByProduct ok
//		ProductService ps = new ProductService();
//		ProductVO productvo = ps.getProductById(3008);
//		NotifyMemberDAO dao = new NotifyMemberDAO();
//		 List<NotifyMemberVO> list = dao.getListMemberByProduct(productvo);
//		 for(NotifyMemberVO notifymember :list){
//			 System.out.println(notifymember.getMemberVO().getMember_Email());
//		 }
			
		// Test getListMemberEmail ok
//		ProductService ps = new ProductService();
//		ProductVO productvo = ps.getProductById(3008);
//		NotifyMemberDAO dao = new NotifyMemberDAO();
//		Object[] emilarray = dao.getListMemberEmail(productvo);
//		for(Object email :emilarray){
//			System.out.println(email);
//		}
//		
		
//		Test deteteByProductVO ok
//		ProductService ps = new ProductService();
//		ProductVO productvo = ps.getProductById(3008);
//		NotifyMemberDAO dao = new NotifyMemberDAO();
//		dao.deteteByProductVO(productvo);
	}
}
