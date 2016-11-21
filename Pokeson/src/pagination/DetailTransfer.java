package pagination;


import java.io.IOException;

import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productclass.model.ProductClassDAO;
import com.productclass.model.ProductClassVO;
import com.productclassdetail.model.ProductClassdetailDAO;
import com.productclassdetail.model.ProductClassdetailVO;

@WebServlet("/detailProduct")
public class DetailTransfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//子分類編號宣告
		int classdetail_no;
		//分頁宣告
		int pageNumber = 0;
		int pageSize = 0;
		//排序宣告
		String ordertype;
		
		//抓取各種Requset--------------------------------
			//抓取該子分類編號
			classdetail_no = Integer.parseInt(request.getParameter("detailno"));
			//抓取該子分類名稱
			String detailname = new String (request.getParameter("detail").getBytes(),"UTF-8");
			//抓取分頁
			//預設商品首頁為第1頁
			try{pageNumber = Integer.parseInt(request.getParameter("pageNumber"));} 
			catch (NumberFormatException e) { pageNumber=1;}
			//預設每頁顯示10筆商品
			try{pageSize = Integer.parseInt(request.getParameter("pageSize"));}
			catch(Exception e){pageSize =10;}
			//抓取排序的點選
			ordertype = request.getParameter("order");
			
		//抓取productclassvo取得上一層分類編號、名稱
		ProductClassdetailDAO dao = new ProductClassdetailDAO();
		ProductClassdetailVO detailvo = dao.findByPrimaryKey(classdetail_no);
		
		//傳送Requset
		request.setAttribute("classdetailno",classdetail_no);
		request.setAttribute("detailvo",detailvo);
		request.setAttribute("detailname",detailname);
		request.setAttribute("pageNumber",pageNumber);
		request.setAttribute("pageSize",pageSize);
		request.setAttribute("ordertype",ordertype);
		
		RequestDispatcher rd = request.getRequestDispatcher("/detailProductPage.jsp");
        rd.forward(request, response);
		return;		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
