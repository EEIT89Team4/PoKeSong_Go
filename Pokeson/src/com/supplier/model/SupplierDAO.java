package com.supplier.model;

import org.hibernate.*;

import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;
import java.util.*;

public class SupplierDAO implements SupplierDAO_interface {

	private static final String GET_ALL_STMT = "from SupplierVO order by supplier_id";

	@Override
	public void insert(SupplierVO supplierVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(supplierVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(SupplierVO supplierVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(supplierVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer supplier_id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			SupplierVO supplierVO = (SupplierVO) session.get(SupplierVO.class, supplier_id);
			session.delete(supplierVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public SupplierVO findByPrimaryKey(Integer supplier_id) {
		SupplierVO supplierVO = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			supplierVO = (SupplierVO) session.get(SupplierVO.class, supplier_id);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return supplierVO;
	}

	@Override
	public List<SupplierVO> getAll() {
		List<SupplierVO> list = null;
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
	
	@Override
	public Set<StockVO> getStocksBySupplier_id(Integer Supplier_id) {		
		Set<StockVO> set = findByPrimaryKey(Supplier_id).getStocks();
		return set;
	}

	public static void main(String[] args) {
		
		SupplierDAO dao = new SupplierDAO();	
		
		
		
		//��@�d�� �t�t�Ӷi�f����
//		SupplierVO supplierVO1 = dao.findByPrimaryKey(5014);
//		System.out.print(supplierVO1.getSupplier_id() + ",");
//		System.out.print(supplierVO1.getSupplier_name() + ",");
//		System.out.print(supplierVO1.getChargeperson() + ",");
//			System.out.println("\n------�t�Ӷi�f��C��------");
//			Set<StockVO> set1 = supplierVO1.getStocks();
//			for (StockVO aStock : set1) {
//				System.out.print(aStock.getStock_id() + ",");
//				System.out.print(aStock.getStock_date() + ",");
//				System.out.print(aStock.getAdminster_name());
//				System.out.println();
//			}
//			System.out.println();

		
		//getAll�򥻬d�� ���t�����Ӷi�f�檺���
//		List<SupplierVO> list2 = dao.getAll();
//		for (SupplierVO aSupplier : list2) {
//			System.out.print(aSupplier.getSupplier_id() + ",");
//			System.out.print(aSupplier.getSupplier_name() + ",");
//			System.out.print(aSupplier.getChargeperson() + ",");
//			System.out.println();
//		}
		
		//getAll�@��h�d�� �t�����Ӷi�f�檺���
		List<SupplierVO> list3 = dao.getAll();
		for (SupplierVO aSupplier : list3) {
			System.out.print(aSupplier.getSupplier_name() + ",");
			System.out.print(aSupplier.getChargeperson() + ",");
			System.out.println("\n------�����Ӫ��i�f��------");
			Set<StockVO> set2 = aSupplier.getStocks();
			for (StockVO aStock : set2) {
				System.out.print(aStock.getStock_id() + ",");
				System.out.print(aStock.getStock_date() + ",");
				System.out.print(aStock.getAdminster_name());
				System.out.println();
			}
			System.out.println();
		}
		
	}
}
