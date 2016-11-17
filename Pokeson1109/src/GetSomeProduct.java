
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import product.ProductDAO;
import product.ProductVO;

/**
 * Servlet implementation class GetSomeProduct
 */
@WebServlet("/getSomeProduct")
public class GetSomeProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductDAO productdao=null;
		List<ProductVO> list = null;
		String searchtext = request.getParameter("search");
		searchtext=new String(searchtext.getBytes("ISO-8859-1"), "UTF-8");;
		 productdao = new ProductDAO();
		list = productdao.getSomeProduct(searchtext);
		request.setAttribute("products",list);
		RequestDispatcher rd = request.getRequestDispatcher("/GetSomeProduct.jsp");
        rd.forward(request, response);
        return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
