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
	public void updateQuantity(int product_no,int product_quantity) {
		 ProductDAO productdao = new ProductDAO();
		 productdao.updateQuantity(product_no,product_quantity);

	}
	public void updateSaleaccount_quantity(int product_no,int saleaccount_quantity) {
		 ProductDAO productdao = new ProductDAO();
		 productdao.updateSaleaccount_quantity(product_no,saleaccount_quantity);

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
	public List<ProductVO> getHotProduct() {
		ProductDAO productdao = new ProductDAO();
		 return productdao.getHotProduct();
	}
	public List<ProductVO> getNewProduct() {
		ProductDAO productdao = new ProductDAO();
		 return productdao.getNewProduct();
	}
	public List<ProductVO> getAllByClassNo(int class_no) {
		ProductDAO productdao = new ProductDAO();
		 return productdao.getAllByClassNo(class_no);
	}
	public List<ProductVO> getSeachAllProduct(String keyword){
		ProductDAO productdao = new ProductDAO();
		return productdao.getSeachAllProduct(keyword);
	}
	public static void main(String[] args){
		//UPDATE
//		ProductVO aa = new ProductVO();
//		aa.setProduct_no(3001);
//		aa.setProduct_quantity(50);
//		aa.setClass_no(3);
//		aa.setClassdetail_no(1);
//		ProductService productService = new ProductService();
//		productService.updateQuantity(3002, 400);;
	}
}
