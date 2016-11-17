package ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import product.ProductDAO;
import product.ProductVO;

public class ShoppingCart {
	Map<Integer, List<ProductVO>> productMap = new HashMap<>();

	public Map<Integer, List<ProductVO>> getProductMap() {
		return productMap;
	}

	public void setProductMap(Map<Integer, List<ProductVO>> productMap) {
		this.productMap = productMap;
	}

//	public int addProduct(Integer product_id) throws Exception {
//		ProductDAO productdao = new ProductDAO();
//		ProductVO productvo = null;
//		productvo = productdao.getProductById(product_id);//get Product
//		int tempcount = 0;
//		try{
//		tempcount =getListSize(product_id);
//		}
//		catch (Exception e){	
//		}
//		int productCount = productvo.getProduct_quantity();
//		if((tempcount+1)>productCount){
//			throw new Exception();
//		}
//		if (productMap.containsKey(product_id)) {
//			List<ProductVO> productlist = productMap.get(product_id);
//			productlist.add(productvo);	
//		}
//		else{
//			List<ProductVO> list=new ArrayList();
//			list.add(productvo);
//			productMap.put(product_id, list);	
//		}
//		return productMap.keySet().size();
//		
//	}
    
	public int addProduct(Integer product_id,Integer addcount) throws Exception {
		ProductDAO productdao = new ProductDAO();//getdao
		ProductVO productvo = null;  // 宣告一個產品的VO
		productvo = productdao.getProductById(product_id);//get Product明細
		int tempcount = 0;    //用來表示當前單個商品選購數量
		try{
		tempcount =getListSize(product_id);//get current specified list size用List的Size來表示
		}
		catch (Exception e){	
		}
		int productCount = productvo.getProduct_quantity();//呼叫累積銷量
		if((tempcount+addcount)>productCount){        //選購數量+增加數量 如果大於庫存數量丟出例外
			throw new Exception();
		}
		if (productMap.containsKey(product_id)) {   //Map裡有相等此商品編號的KEY
			List<ProductVO> productlist = productMap.get(product_id);
			for(int i=0;i<addcount;i++){     //把商品VO加入Map裡
			productlist.add(productvo);	
			}
		}
		else{
			List<ProductVO> list=new ArrayList();//Map裡沒有List就加入一個List
			for(int i=0;i<addcount;i++){
			list.add(productvo);}
			productMap.put(product_id, list);	
		}
		return productMap.keySet().size();
		
	}

	
	public int delProduct(int product_id) { // 刪除單項物品
//		System.out.println(getCartSize());
	    this.productMap.remove(product_id);
//	    System.out.println(getCartSize());
	    return this.productMap.keySet().size();
	}
	//計算購物車內所有商品的合計金額(每項商品的單價*數量的總和)
			public int getSubtotal(){
				int subTotal = 0 ;
				Set<Integer> set = productMap.keySet();
				for(int n : set){
					int price    = productMap.get(n).iterator().next().getProduct_price();
//					double discount = productMap.get(n).iterator().next().get;
					int    qty      = productMap.get(n).size();
//					subTotal +=  price * discount * qty;
					subTotal +=  price *qty;
				}
				return subTotal;
			}

	public void clearCart() { //刪除整個購物車
		productMap.clear();
	}

	public Map<Integer, List<ProductVO>> getAllProduct() {
		return productMap;
	}
	public int getCartSize(){
		 
		return productMap.keySet().size();
	}
	public int getListSize(int productno){
		return this.productMap.get(productno).size();
	}
}
