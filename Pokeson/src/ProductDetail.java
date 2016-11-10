
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.ProductService;
import product.ProductVO;

/**
 * Servlet implementation class ProductDetail
 */
@WebServlet("/getOneProduct")
public class ProductDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer product_no = 0;
		ProductVO productvo = null;
		request.setCharacterEncoding("UTF-8");
		product_no = Integer.valueOf(request.getParameter("productid"));
		ProductService productservice = new ProductService();
		productvo = productservice.getProductById(product_no);
		request.setAttribute("oneproduct", productvo);
		RequestDispatcher rd = request.getRequestDispatcher("/SingleProduct.jsp");
		rd.forward(request, response);
        return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
