package com.cm.model;

/*
 Hibernate is providing a factory.getCurrentSession() method for retrieving the current session. A
 new session is opened for the first time of calling this method, and closed when the transaction is
 finished, no matter commit or rollback. But what does it mean by the ��current session��? We need to
 tell Hibernate that it should be the session bound with the current thread.

 <hibernate-configuration>
 <session-factory>
 ...
 <property name="current_session_context_class">thread</property>
 ...
 </session-factory>
 </hibernate-configuration>

 */


import org.hibernate.*;
import hibernate.util.HibernateUtil;
import java.util.*;

//import com.emp.model.EmpVO;
import com.cm.model.CmVO;

public class CmDAO implements CmDAO_interface {

	private static final String GET_ALL_STMT = "from CmVO order by cm_no";
//	private static final String GET_ALL_STMT = "select shipments_no,order_no,adminster_id,receipt_name,receipt_phone,receipt_address,receiptime from shipments";
	//private static final String DELETE_Orderlist = "delete from orderlistVO where order_no = ?";
	//private static final String DELETE_Shipments = "delete from ShipmentsVO where shipments_no = ?";	
	//private static final String GET_Shipments_ByShipmentsno_STMT = "from EmpVO as empvo where empvo.deptno=? order by empvo.empno";

	@Override
	public void insert(CmVO cmVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cmVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(CmVO cmVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(cmVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer cm_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			CmVO cmvo = (CmVO) session.get(CmVO.class, cm_no);
			session.delete(cmvo);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public CmVO findByPrimaryKey(Integer cm_no) {
		CmVO cmVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			cmVO = (CmVO) session.get(CmVO.class, cm_no);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return cmVO;
	}

	@Override
	public List<CmVO> getAllCm() {
		List<CmVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
//			Query query = session.createSQLQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}
	
//	@Override
//	public Set<EmpVO> getEmpsByDeptno(Integer deptno) {		
//		Set<EmpVO> set = null;
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Query query = session.createQuery(GET_Emps_ByDeptno_STMT);
//			query.setParameter(0, deptno);
//			List list = query.list();
//			set = new HashSet<EmpVO>(list);
//			session.getTransaction().commit();
//		} catch (RuntimeException ex) {
//			session.getTransaction().rollback();
//			throw ex;
//		}
//		return set;
//	}

	public static void main(String[] args) {

		CmDAO dao1 = new CmDAO();
		 //ShipmentsDAO aa = new ShipmentsDAO();
		 
		// �s�W
//		DeptVO deptVO1 = new DeptVO();
//		deptVO1.setDname("�s�y��");
//		deptVO1.setLoc("���ꦿ��");
//		dao.insert(deptVO1);

		// �ק�
		byte[] a = {1,2,3,4,5,6,7};
		CmVO cmVO2 = new CmVO();
		cmVO2.setPicture(a);
		dao1.insert(cmVO2);

		// �R��
//		dao.delete(30);

		// �d��
//		DeptVO deptVO3 = dao.findByPrimaryKey(10);
//		System.out.print(deptVO3.getDeptno() + ",");
//		System.out.print(deptVO3.getDname() + ",");
//		System.out.println(deptVO3.getLoc());
//		System.out.println("---------------------");

		// �d�߳���
//		List<CmVO> list = dao1.getAllCm();
//		for (CmVO aShipments : list) {
//			System.out.print(aShipments.getCm_no() + ",");
//			System.out.print(aShipments.getPicture() + ",");
//			System.out.println();
//		}
		
		// �d�߬Y���������u
//		Set<EmpVO> set = dao.getEmpsByDeptno(10);
//		for (EmpVO aEmp : set) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
//		}
	}
}
