package product;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import hibernate.util.HibernateUtil;

public class ProductDAO {

	public void insert(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(productVO);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}
	}

	public void update(ProductVO productVO) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.saveOrUpdate(productVO);
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}

	}
	public void updateQuantity(int product_no,int product_quantity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("update ProductVO set product_quantity=? where product_no=?");
			query.setParameter(0, product_quantity);
			query.setParameter(1, product_no);
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}

	}
	
	public void updateSaleaccount_quantity(int product_no,int saleaccount_quantity) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Query query = session.createQuery("update ProductVO set saleaccount_quantity=? where product_no=?");
			query.setParameter(0, saleaccount_quantity);
			query.setParameter(1, product_no);
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException ex) {
			tx.rollback();
		}

	}

	public void delete(Integer product_no) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = null;
		ProductVO productVO = null;
		try {
			tx = session.beginTransaction();
			productVO = (ProductVO) session.get(ProductVO.class, product_no);
			session.delete(productVO);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
		}
	}

	public ProductVO getProductById(Integer product_no) {
		ProductVO productVO = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			productVO = (ProductVO) session.get(ProductVO.class, product_no);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return productVO;
	}

	public List<ProductVO> getAllProduct() {
		List<ProductVO> productlist = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from ProductVO order by product_no");
			productlist = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return productlist;

	}
	public List<ProductVO> getHotProduct() {
		List<ProductVO> productlist = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from ProductVO order by saleaccount_quantity desc");
//			Query query = session.createSQLQuery("SELECT TOP (5) saleaccount_quantity FROM product order by saleaccount_quantity desc");
			
			productlist = query.list();
			
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return productlist;

	}
	public List<ProductVO> getNewProduct() {
		List<ProductVO> productlist = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from ProductVO order by product_no desc");
//			Query query = session.createSQLQuery("SELECT TOP (5) saleaccount_quantity FROM product order by saleaccount_quantity desc");
			
			productlist = query.list();
			
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return productlist;

	}

	public List<ProductVO> getSomeProduct(String searchtext) {
		List<ProductVO> productlist = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Criteria criteria = session.createCriteria(ProductVO.class);
			criteria.add(Restrictions.disjunction()
			        .add(Restrictions.like("supplier_name", "%" + searchtext + "%"))
			        .add(Restrictions.like("product_description", "%" + searchtext + "%"))
			        .add(Restrictions.like("product_name", "%" + searchtext + "%")));
			productlist = criteria.list();
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			e.printStackTrace();
		}
		return productlist;
	}

	public static void main(String args[]) {
//		 ProductVO productvo = new ProductVO();

		// UPDATE
		// ProductDAO productdao = new ProductDAO();
		// productvo=productdao.getProductById(3001);
		// productvo.setWeight(777);
		// productdao.update(productvo);
		// INSERT
		// ProductDAO productdao = new ProductDAO();
		// productvo.setClass_no(1);
		// productvo.setClassdetail_no(12);
		// productvo.setProduct_price(10000);
		// productvo.setUnit("��");
		// productvo.setSupplier_name("�굦�|");
		// productvo.setProduct_name("ps4");
		// productvo.setProduct_quantity(15);
		// productvo.setSaleaccount_quantity(8);
		// productvo.setWeight(300);
		// productvo.setDeadline(2017-01-01); //����榡�ݬd
		// productvo.setDeadlineday(3);
		// productdao.insert(productvo);

		// DELETE
//		 ProductDAO productdao = new ProductDAO();
//		 productdao.delete(3008);
		// GET_ONE
//		 ProductDAO productdao = new ProductDAO();
//		 productvo =productdao.getProductById(3053);
//		 System.out.println(productvo.getProduct_name());
//		 System.out.println(productvo.getPictrue());
		// GET_ALL
		// ProductDAO productdao = new ProductDAO();
		// List<ProductVO> productlist=productdao.getAllProduct();
		// for (ProductVO productvo1 :productlist){
		// System.out.println(productvo1.getProduct_name());
		// }

		// getSomeProduct
//	ProductDAO productdao = new ProductDAO();
//		List<ProductVO> list = productdao.getSomeProduct("麵");
//		for (ProductVO productlist : list) {
//			String abc = productlist.getSupplier_name();
//			System.out.println(abc);
//		}
		// getHotProduct
		ProductDAO productdao = new ProductDAO();
			List<ProductVO> list = productdao.getHotProduct();
			for (ProductVO productlist : list) {
				int abc = productlist.getProduct_no();
				System.out.println(abc);
			}
	}
}
