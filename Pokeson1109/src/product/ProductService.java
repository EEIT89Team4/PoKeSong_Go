package product;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import hibernate.util.HibernateUtil;

public class ProductService {
	public void insert(ProductVO productVO) {
	 ProductDAO productdao = new ProductDAO();
	 productdao.insert(productVO);
	}

	public void update(ProductVO productVO) {
		 ProductDAO productdao = new ProductDAO();
		 productdao.update(productVO);

	}

	public void delete(Integer product_no) {
		ProductDAO productdao = new ProductDAO();
		 productdao.delete(product_no);

	}

	public ProductVO getProductById(Integer product_no) {
		ProductDAO productdao = new ProductDAO();
		 return productdao.getProductById(product_no);
	}

	public List<ProductVO> getAllProduct() {
		ProductDAO productdao = new ProductDAO();
		 return productdao.getAllProduct();
	}
}
