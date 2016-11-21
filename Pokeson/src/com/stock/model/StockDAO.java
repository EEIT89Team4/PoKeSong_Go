package com.stock.model;

import org.hibernate.*;

import com.supplier.model.SupplierVO;

import hibernate.util.HibernateUtil;
import java.util.*;


public class StockDAO implements StockDAO_interface {

	private static final String GET_ALL_STMT = "from StockVO order by stock_id";

	@Override
	public void insert(StockVO stockVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stockVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(StockVO stockVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stockVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer stock_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();

//        �i���ɦh��(�y)�i�ĥ�HQL�R���j
//			Query query = session.createQuery("delete StockVO where stock_id=?");
//			query.setParameter(0, stock_id);
//			System.out.println("�R��������=" + query.executeUpdate());

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j
			StockVO stockVO = new StockVO();
			stockVO.setStock_id(stock_id);
			session.delete(stockVO);

//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��stock.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
//		    StockVO stockVO = (StockVO) session.get(StockVO.class, stock_id);
//		    session.delete(stockVO);

		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public StockVO findByPrimaryKey(Integer stock_id) {
		StockVO stockVo = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			stockVo = (StockVO) session.get(StockVO.class, stock_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return stockVo;
	}

	@Override
	public List<StockVO> getAll() {
		List<StockVO> list = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Query query = session.createQuery(GET_ALL_STMT);
			list = query.list();
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	public static void main(String[] args) {
		
		StockDAO dao = new StockDAO();
		
		//getAll�h��@�d�� �t�����Ӹ��
		List<StockVO> list = dao.getAll();
		for (StockVO aStock : list) {
			System.out.print(aStock.getStock_id() + ",");
			System.out.print(aStock.getStock_date() + ",");
			System.out.println(aStock.getAdminster_name() + ",");
			// �H�U�T��g�k���b�h����ܤ@����
			System.out.println("--------------------------");
			System.out.print("�t�Ӹ��:(");
			System.out.print(aStock.getSupplierVO().getSupplier_id() + ",");
			System.out.print(aStock.getSupplierVO().getSupplier_name() + ",");
			System.out.print(aStock.getSupplierVO().getChargeperson());
			System.out.print(")");
			System.out.println();
			System.out.println();
		}	
	}
}
