package com.stockdetails.model;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.stock.model.StockVO;

import hibernate.util.HibernateUtil;


public class StockdetailsDAO implements StockdetailsDAO_interface {

	private static final String GET_ALL_STMT = "from StockdetailsVO order by stock_id";

	@Override
	public void insert(StockdetailsVO stockdetailsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stockdetailsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void update(StockdetailsVO stockdetailsVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(stockdetailsVO);
			session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public void delete(Integer stock_id,int product_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
//        �i���ɽƦX�D��h��(�y)�i�ĥ�HQL�R���j
			Query query = session.createQuery("delete StockdetailsVO where stock_id=? and product_no=?");
			query.setParameter(0, stock_id);
			query.setParameter(1, product_no);
			System.out.println("�R��������=" + query.executeUpdate());

//        �i�Φ��ɦh��(�])�i�ĥΥh�����p���Y��A�A�R�����覡�j

//        �i���ɦh�褣�i(���y)�ĥ�cascade�p�ŧR���j
//        �i�h��stock.hbm.xml�p�G�]�� cascade="all"�� cascade="delete"�N�|�R���Ҧ��������-�]�A���ݳ����P�P�������䥦���u�N�|�@�ֳQ�R���j
//		    StockdetailsVO stockdetailsVO = (StockdetailsVO) session.get(StockdetailsVO.class, stock_id);
//		    session.delete(stockdetailsVO);

		    session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
	}
	
	@Override
	public List<StockdetailsVO>  findByPrimaryKey(Integer stock_id,int product_no) {
		StockdetailsVO stockdetailsVO = null;
		
		 List<StockdetailsVO> list;
		 
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			 Query query = session.createQuery("From StockdetailsVO where stock_id=? and product_no=?");
			 query.setParameter(0, stock_id);
			 query.setParameter(1, product_no);
			 list = query.list();
			 session.getTransaction().commit();
		} catch (RuntimeException ex) {
			session.getTransaction().rollback();
			throw ex;
		}
		return list;
	}

	@Override
	public List<StockdetailsVO> getAll() {
		List<StockdetailsVO> list = null;
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
		
		StockdetailsDAO dao = new StockdetailsDAO();
		
		//�s�W&�ק�
			//��New�Bset���VO���D���,�A���ƦX�D��VO
//		StockVO ss = new StockVO();
//		ss.setStock_id(1514);
//		
//		ProductVO pp = new ProductVO();
//		pp.setProduct_no(3028);
//		
//		StockdetailsVO sd = new StockdetailsVO();
//		sd.setStockVO(ss);
//		sd.setProductVO(pp);
//		sd.setProduct_newquantity(500);
		
//		dao.insert(sd);
//		dao.update(sd)
		
		//�R��
//		dao.delete(1514,3031);
		
		//�D��d��
//		List<StockdetailsVO> list  = dao.findByPrimaryKey(1514,3032);
//		for(StockdetailsVO astockdetailsVO :list){
//			System.out.println("--------------------�i�f����--------------------");
//			System.out.print("�q��s��:" + astockdetailsVO.getStockVO().getStock_id() + ",");
//			System.out.print("�i�f���~�s��:" + astockdetailsVO.getProductVO().getProduct_no() + ",");
//			System.out.print(astockdetailsVO.getStockVO().getStock_date() + ",");
//			System.out.println(astockdetailsVO.getStockVO().getAdminster_name());
//			System.out.println("�������i�f����ӡ�����");
//			System.out.print(astockdetailsVO.getProductVO().getProduct_name()+ ",");
//			System.out.print(astockdetailsVO.getProduct_newquantity());
//			System.out.println(astockdetailsVO.getProductVO().getUnit());
//			System.out.println();		
//		}
		

		//getAll ���h��@�d�� �t�i�f���ơB���~���
//		List<StockdetailsVO> list = dao.getAll();
//		for (StockdetailsVO aStockdetails : list) {
//			System.out.println("----------�i�f����----------");
//			System.out.print("�q��s��:" + aStockdetails.getStockVO().getStock_id() + ",");
//			System.out.print("�i�f���~�s��:" + aStockdetails.getProductVO().getProduct_no() + ",");
//			System.out.print(aStockdetails.getStockVO().getStock_date() + ",");
//			System.out.println(aStockdetails.getStockVO().getAdminster_name());
//			System.out.println("�������i�f����ӡ�����");
//			System.out.print(aStockdetails.getProductVO().getProduct_name()+ ",");
//			System.out.print(aStockdetails.getProduct_newquantity());
//			System.out.println(aStockdetails.getProductVO().getUnit());
//			System.out.println();
//		}


	}
}
