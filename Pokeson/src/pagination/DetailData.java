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


@WebServlet("/detailData")
public class DetailData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//基本Dao宣告
		ProductDAO dao = null;
		List<ProductVO> productslist = null;
		//子分類編號宣告
		int classdetail_no;
		//分頁宣告
		int pageNumber = 0;
		int pageSize = 0;
		int firstResult = 0;
		int total = 0; //商品總數
		//排序宣告
		String ordertype;
		
		//抓取各種Request
			//抓取分類編號--------------------------------
			classdetail_no = Integer.parseInt(request.getParameter("detailno"));
			//抓取分頁-----------------------------------
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			//抓取排序------------------------------------
			ordertype = request.getParameter("order");
		
		//先抓出該分類產品總數
		dao = new ProductDAO();
		total = dao.getAllByClassDetailNo(classdetail_no).size();	
		//抓出該分類產品並分頁
		firstResult = pageSize * (pageNumber - 1);
		productslist = dao.getDetailPage(classdetail_no, firstResult, pageSize, ordertype);
		
		request.setAttribute("detailproducts",productslist);
		request.setAttribute("total",total);
		request.setAttribute("pageNumber", pageNumber);
		request.setAttribute("pageSize", pageSize);
		
		RequestDispatcher rd = request.getRequestDispatcher("/detailPagination.jsp");
		rd.forward(request, response);
		return;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
