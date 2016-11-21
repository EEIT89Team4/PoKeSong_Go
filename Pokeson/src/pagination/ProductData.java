package pagination;


import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import com.productclass.model.ProductClassDAO;

import hibernate.util.HibernateUtil;
import product.ProductDAO;
import product.ProductVO;


@WebServlet("/productData")
public class ProductData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNumber = 0;
		int pageSize = 0;
		int firstResult = 0;
		int total = 0;
		ProductDAO dao = null;
		List<ProductVO> productslist = null;
		
		
		//商品首頁預設為第1頁，每頁顯示10筆商品
		try{
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
		}catch(Exception e){
			pageSize =10;	
		}
			
		try{
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		} 
		catch (NumberFormatException e) { 
		    pageNumber=1;
		}	
		
		//分頁重要公式 firstResult表示每一頁起始陣列位置 ，如pageSize=10，每頁抓取資料起始位置為[0,10,20,30...]
		firstResult = pageSize * (pageNumber - 1); 
		dao = new ProductDAO();
		//抓出所有產品並分頁
		productslist = dao.getFirstMaxResults(firstResult, pageSize);
		//所有產品總筆數
		total = dao.getAllProduct().size();
		
		request.setAttribute("products",productslist);
		request.setAttribute("total",total);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageSize", pageSize);
		
		RequestDispatcher rd = request.getRequestDispatcher("/productPagination.jsp");
		rd.forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
