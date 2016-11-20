

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ShoppingCart.ShoppingCart;
import product.ProductDAO;
import product.ProductVO;

/**
 * Servlet implementation class GetCartNumber
 */
@WebServlet("/getCartNumber")
public class GetCartNumber extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int product_no=0;
		int cartsize=0;
		PrintWriter out = response.getWriter();
		ProductVO  productvo=null;		
		ProductDAO productdao = new ProductDAO();
		String tempproductno= request.getParameter("productno");
		String count = request.getParameter("count");
		int addcount = Integer.valueOf(count);  //addcount
		product_no = Integer.valueOf(tempproductno);//3001
		HttpSession session = request.getSession();
		if(session.getAttribute("mycart")==null){  //first time add this product to cart
			ShoppingCart cart = new ShoppingCart();
			try {
				cartsize=cart.addProduct(product_no,addcount);
			} catch (Exception e) {
				out.print("0");
				return;
			}		
			out.print(cartsize);
			session.setAttribute("mycart",cart);
			return;                                   
		}
		else{                                      //second time add product to cart
			ShoppingCart cart=(ShoppingCart)session.getAttribute("mycart");
			try {                                                   
				cartsize=cart.addProduct(product_no,addcount);
			} catch (Exception e) {
				out.print("0");
				return;
			}	
		    out = response.getWriter();
			out.print(cartsize);
			session.setAttribute("mycart",cart);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
	}

}
