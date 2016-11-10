package ShoppingCart;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/deleteCartItem")
public class DeleteCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String num = request.getParameter("CartItem");
		Integer product_no=Integer.valueOf(num);
		HttpSession session = request.getSession();
		ShoppingCart cart = (ShoppingCart) session.getAttribute("mycart");
		int newcount= cart.delProduct(product_no);
		PrintWriter out = response.getWriter();
		out.print(newcount);
		if(newcount==0){
//			session.setAttribute("mycart.productMap.size()", "0");
			session.removeAttribute("mycart");
			RequestDispatcher rd = request.getRequestDispatcher("/ShowCartContent.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
