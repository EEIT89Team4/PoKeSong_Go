package pagination;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import product.ProductDAO;
import product.ProductVO;




/**
 * Servlet implementation class GetSomeProduct
 */
@WebServlet("/allProduct")
public class ProdcutTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int pageNumber = 0;
		int pageSize = 0;
		ProductDAO productdao = null;
		List<ProductVO> list = null;		
		
		//預設商品首頁為第1頁，每頁顯示10筆商品
		try{
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		} 
		catch (NumberFormatException e) { 
		    pageNumber=1;
		}	
			
		try{
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}catch(Exception e){
			pageSize =10;	
		}
		
		request.setAttribute("pageNumber",pageNumber);
		request.setAttribute("pageSize",pageSize);
		
		RequestDispatcher rd = request.getRequestDispatcher("/productPage.jsp");
        rd.forward(request, response);
        return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
