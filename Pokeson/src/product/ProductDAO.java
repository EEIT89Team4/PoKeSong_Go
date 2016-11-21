package product;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
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
	
	public List<ProductVO> getSeachAllProduct(String keyword) {
		List<ProductVO> productlist = null;
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from ProductVO where product_name like ?");
			query.setParameter(0, "%"+keyword+"%");
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

	//for productPage:呈現所有產品並分頁-----------------------------------------------
		public List<ProductVO> getFirstMaxResults(int firstResult,int maxResult) {
			List<ProductVO> productlist = null;
			Transaction tx = null;
			
			try {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query query = session.createQuery("from ProductVO");
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
				productlist = query.list();
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				e.printStackTrace();
			}
			return productlist;
		}
		
		//for searchPage:呈現搜尋頁面的所有產品-----------------------------------------------
		public List<ProductVO> getAllSearch(String searchtext,String[] advs,String ordertype) {
			List<ProductVO> productlist = null;
			Transaction tx = null;
			try {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Criteria criteria = session.createCriteria(ProductVO.class);
				if(advs!=null){//判斷是否有條件
						Disjunction or = Restrictions.disjunction();
						for(String adv : advs){
							or.add(Restrictions.eq("supplier_name",adv));
						}
						criteria.add(or);
				}
				criteria.add(Restrictions.disjunction()
				        .add(Restrictions.like("supplier_name", "%" + searchtext + "%"))
				        .add(Restrictions.like("product_description", "%" + searchtext + "%"))
				        .add(Restrictions.like("product_name", "%" + searchtext + "%")));
				if(ordertype!=null){ //判斷是否有排序
					if(ordertype.equals("asc")){
						System.out.println("ordertype=由低到高");
						criteria.addOrder(Order.asc("product_price"));
					}
					if(ordertype.equals("desc")){
						System.out.println("ordertype=由高到低");
						criteria.addOrder(Order.desc("product_price"));
					}
				}
				productlist = criteria.list();
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				e.printStackTrace();
			}
			return productlist;
		}
		
		//for searchPage:呈現搜尋頁面的所有產品並分頁-----------------------------------------------
			public List<ProductVO> getSearchPage(String searchtext,int firstResult
					,int maxResult,String[] advs,String ordertype) {
				List<ProductVO> productlist = null;
				Transaction tx = null;
				try {
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					tx = session.beginTransaction();
					Criteria criteria = session.createCriteria(ProductVO.class);
					if(advs!=null){//判斷是否有條件
						Disjunction or = Restrictions.disjunction();
						for(String adv : advs){
							or.add(Restrictions.eq("supplier_name",adv));
						}
						criteria.add(or);
					}
					criteria.add(Restrictions.disjunction()
				        .add(Restrictions.like("supplier_name", "%" + searchtext + "%"))
				        .add(Restrictions.like("product_description", "%" + searchtext + "%"))
				        .add(Restrictions.like("product_name", "%" + searchtext + "%")));
					if(ordertype!=null){ //判斷是否有排序
						if(ordertype.equals("asc")){
							System.out.println("ordertype="+ordertype);
							criteria.addOrder(Order.asc("product_price"));
						}
						if(ordertype.equals("desc")){
							System.out.println("ordertype="+ordertype);
							criteria.addOrder(Order.desc("product_price"));
						}
					}
					//將最後的資料做分頁
					criteria.setFirstResult(firstResult);
					criteria.setMaxResults(maxResult);
					productlist = criteria.list();
					tx.commit();
				} catch (RuntimeException e) {
					tx.rollback();
					e.printStackTrace();
				}
				return productlist;
			}

		//for classProductPage:呈現該分類的所有產品-----------------------------------------------
		public List<ProductVO> getAllByClassNo(int class_no) {
			List<ProductVO> productlist = null;
			Transaction tx = null;
			try {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query query = session.createQuery("from ProductVO where productClassVO.class_no="+class_no);
				productlist = query.list();
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				e.printStackTrace();
			}
			return productlist;
		}

		//for classProductPage:呈現該分類的所有產品並分頁------------------------------------------
		public List<ProductVO> getClassPage(int class_no,int firstResult,int maxResult,String ordertype) {
			List<ProductVO> productlist = null;
			Transaction tx = null;
			Query query=null;
			try {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
					if(ordertype.equals("asc")){
						System.out.println("ordertype="+ordertype);
						query = session.createQuery
								("from ProductVO where productClassVO.class_no="+class_no +
										"order by product_price ASC");
					}
					else if(ordertype.equals("desc")){
						System.out.println("ordertype="+ordertype);
						query = session.createQuery
								("from ProductVO where productClassVO.class_no="+class_no +
										"order by product_price DESC");
					}else{
						query = session.createQuery("from ProductVO where productClassVO.class_no="+class_no);
					}
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
				productlist = query.list();
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				e.printStackTrace();
			}
			return productlist;
		}
		
		//for detailProductPage:呈現該細部分類的所有產品-----------------------------------------------
		public List<ProductVO> getAllByClassDetailNo(int classdetail_no) {
			List<ProductVO> productlist = null;
			Transaction tx = null;
			try {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
				Query query = session.createQuery("from ProductVO where classdetail_no="+classdetail_no);
				productlist = query.list();
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				e.printStackTrace();
			}
			return productlist;
		}
		
		//for detailProductPage:呈現該細部分類的所有產品並分頁-------------------------------------------
		public List<ProductVO> getDetailPage(int classdetail_no,int firstResult,int maxResult,String ordertype) {
			List<ProductVO> productlist = null;
			Transaction tx = null;
			Query query=null;
			try {
				Session session = HibernateUtil.getSessionFactory().getCurrentSession();
				tx = session.beginTransaction();
					if(ordertype.equals("asc")){
						System.out.println("ordertype="+ordertype);
						query = session.createQuery
								("from ProductVO where classdetail_no="+classdetail_no +
										"order by product_price ASC");
					}
					else if(ordertype.equals("desc")){
						System.out.println("ordertype="+ordertype);
						query = session.createQuery
								("from ProductVO where classdetail_no="+classdetail_no +
										"order by product_price DESC");
					}else{
						query = session.createQuery("from ProductVO where classdetail_no="+classdetail_no);
					}
				query.setFirstResult(firstResult);
				query.setMaxResults(maxResult);
				productlist = query.list();
				tx.commit();
			} catch (RuntimeException e) {
				tx.rollback();
				e.printStackTrace();
			}
				return productlist;
			}
		
		//for brandProductPage:呈現該細部分類的品牌產品並分頁-------------------------------------------
				public List<ProductVO> getBrandProduct(int classdetail_no,String ordertype,String brand) {
					List<ProductVO> productlist = null;
					Transaction tx = null;
					Query query=null;
					try {
						Session session = HibernateUtil.getSessionFactory().getCurrentSession();
						tx = session.beginTransaction();
						if(ordertype.equals("asc")){
							System.out.println("ordertype="+ordertype);
							query = session.createQuery
									("from ProductVO where classdetail_no="+classdetail_no + 
											"and supplier_name='"+ brand + "'" +
											"order by product_price ASC");
						}
						else if(ordertype.equals("desc")){
							System.out.println("ordertype="+ordertype);
							query = session.createQuery
									("from ProductVO where classdetail_no="+classdetail_no + 
											"and supplier_name='"+ brand + "'" +
											"order by product_price DESC");
						}else{
							query = session.createQuery("from ProductVO where classdetail_no="+classdetail_no + 
									"and supplier_name='"+ brand + "'");
						}
						productlist = query.list();
						tx.commit();
					} catch (RuntimeException e) {
						tx.rollback();
						e.printStackTrace();
					}
						return productlist;
					}
		
		
		//for brandProductPage:呈現該細部分類的品牌產品並分頁-------------------------------------------
			public List<ProductVO> getBrandPage(int classdetail_no,int firstResult,int maxResult,String ordertype,String brand) {
				List<ProductVO> productlist = null;
				Transaction tx = null;
				Query query=null;
				try {
					Session session = HibernateUtil.getSessionFactory().getCurrentSession();
					tx = session.beginTransaction();
						if(ordertype.equals("asc")){
							System.out.println("ordertype="+ordertype);
							query = session.createQuery
									("from ProductVO where classdetail_no="+classdetail_no + 
											"and supplier_name='"+ brand + "'" +
											"order by product_price ASC");
						}
						else if(ordertype.equals("desc")){
							System.out.println("ordertype="+ordertype);
							query = session.createQuery
									("from ProductVO where classdetail_no="+classdetail_no + 
											"and supplier_name='"+ brand + "'" +
											"order by product_price DESC");
						}else{
							query = session.createQuery("from ProductVO where classdetail_no="+classdetail_no + 
									"and supplier_name='"+ brand + "'");
						}
						query.setFirstResult(firstResult);
						query.setMaxResults(maxResult);
					productlist = query.list();
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
//		ProductDAO productdao = new ProductDAO();
//			List<ProductVO> list = productdao.getHotProduct();
//			for (ProductVO productlist : list) {
//				int abc = productlist.getProduct_no();
//				System.out.println(abc);
//			}
		ProductDAO productdao = new ProductDAO();
		String  aa="統一";
		List<ProductVO> list=productdao.getSeachAllProduct(aa);
		for (ProductVO productlist : list) {
			String abc = productlist.getProduct_name();
			System.out.println(abc);
		}
		
	}
}
