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


@WebServlet("/classData")
public class ClassData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//基本Dao宣告
		ProductDAO dao = null;
		List<ProductVO> productslist = null;
		//分類編號宣告
		int class_no;
		//分頁宣告
		int pageNumber = 0;
		int pageSize = 0;
		int firstResult = 0;
		int total = 0; //商品總數
		//排序宣告
		String ordertype;
		
		//抓取各種Request
			//抓取分類編號--------------------------------
			class_no = Integer.parseInt(request.getParameter("classno"));
			//抓取分頁-----------------------------------
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			//抓取排序------------------------------------
			ordertype = request.getParameter("order");
		
		//先抓出該分類產品總數
			dao = new ProductDAO();
			total = dao.getAllByClassNo(class_no).size();	
		//抓出該分類產品並分頁
			firstResult = pageSize * (pageNumber - 1);
			productslist = dao.getClassPage(class_no,firstResult,pageSize,ordertype);
		
		request.setAttribute("classproducts",productslist);
		request.setAttribute("total",total);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageSize", pageSize);
		
		RequestDispatcher rd = request.getRequestDispatcher("/classPagination.jsp");
		rd.forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
